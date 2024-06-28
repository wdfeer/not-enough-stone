package org.wdfeer.not_enough_stone.item.weapon.common

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtHelper
import net.minecraft.registry.Registries
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Identifier
import org.wdfeer.not_enough_stone.item.Geomatter
import org.wdfeer.not_enough_stone.item.common.GeomatterTool
import kotlin.math.roundToInt

interface GeomatterWeapon : GeomatterTool {
    fun getBuffType(): WeaponBuffType

    fun getStatIncrease(stones: Int): Float = getFlatBonus(stones)

    private fun getBuffTooltipColor(buff: Float): Formatting {
        if (buff < 2f)
            return Formatting.GRAY
        else if (buff < 4f)
            return Formatting.WHITE
        else if (buff < 6f)
            return Formatting.GOLD

        return Formatting.AQUA
    }

    fun getDamageIncreaseTooltip(buff: Float): Text =
        Text.translatable("not_enough_stone.weapon_damage_increase_tooltip")
            .formatted(Formatting.GRAY)
            .append(
                Text.literal("+${((buff * 100f).roundToInt() / 100f)}")
                    .formatted(getBuffTooltipColor(buff))
            )

    fun getReachIncreaseTooltip(buff: Float): Text =
        Text.translatable("not_enough_stone.weapon_reach_increase_tooltip")
            .formatted(Formatting.GRAY)
            .append(
                Text.literal("+${((buff * 100f).roundToInt() / 100f)}")
                    .formatted(getBuffTooltipColor(buff))
            )

    private fun getAttribute(idName: String, buff: Float): Pair<EntityAttribute, EntityAttributeModifier> {
        return if (getBuffType() == WeaponBuffType.Damage)
            Pair(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                EntityAttributeModifier(
                    idName + "_damage_attribute",
                    buff.toDouble(),
                    EntityAttributeModifier.Operation.ADDITION
                ))
        else
            Pair(
                Registries.ATTRIBUTE.get(Identifier("player.entity_interaction_range"))!!,
                EntityAttributeModifier(
                    idName + "_reach_attribute",
                    buff.toDouble(),
                    EntityAttributeModifier.Operation.ADDITION
                ))
    }

    fun getAttributeModifiers(
        idName: String,
        stack: ItemStack,
        slot: EquipmentSlot,
        baseModifiers: Multimap<EntityAttribute, EntityAttributeModifier>
    ): Multimap<EntityAttribute, EntityAttributeModifier> {
        val map = ArrayListMultimap.create(baseModifiers)

        if (slot == EquipmentSlot.MAINHAND) {
            val attribute = getAttribute(idName, getStatIncrease(stack.orCreateNbt.getInt(Geomatter.STONES_COMBINED_NBT)))
            map.put(attribute.first, attribute.second)
        }

        return map
    }
}
