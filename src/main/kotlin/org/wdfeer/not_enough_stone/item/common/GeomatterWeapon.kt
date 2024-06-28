package org.wdfeer.not_enough_stone.item.common

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import org.wdfeer.not_enough_stone.item.Geomatter
import kotlin.math.roundToInt

interface GeomatterWeapon : GeomatterTool {
    fun getDamageIncrease(stones: Int): Float = getFlatBonus(stones)

    private fun getDamageBuffTooltipColor(buff: Float): Formatting {
        if (buff < 2f)
            return Formatting.GRAY
        else if (buff < 4f)
            return Formatting.WHITE
        else if (buff < 6f)
            return Formatting.GOLD

        return Formatting.AQUA
    }

    fun getDamageIncreaseTooltip(damageIncrease: Float): Text =
        Text.translatable("not_enough_stone.weapon_damage_increase_tooltip")
            .formatted(Formatting.GRAY)
            .append(
                Text.literal("+${((damageIncrease * 100f).roundToInt() / 100f)}")
                    .formatted(getDamageBuffTooltipColor(damageIncrease))
            )

    private fun getAttribute(idName: String, damageIncrease: Float): Pair<EntityAttribute, EntityAttributeModifier> {
        return Pair(EntityAttributes.GENERIC_ATTACK_DAMAGE,
        EntityAttributeModifier(
        idName + "damage_attribute",
            damageIncrease.toDouble(),
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
            val attribute = getAttribute(idName, getDamageIncrease(stack.orCreateNbt.getInt(Geomatter.STONES_COMBINED_NBT)))
            map.put(attribute.first, attribute.second)
        }

        return map
    }
}
