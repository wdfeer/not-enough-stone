package org.wdfeer.not_enough_stone.item.tool

import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.text.Text
import net.minecraft.util.Formatting
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
                Text.literal("+${((damageIncrease * 100f).roundToInt())}")
                    .formatted(getDamageBuffTooltipColor(damageIncrease))
            )

    fun getAttribute(idName: String, damageIncreaseMultiplicative: Float): Pair<EntityAttribute, EntityAttributeModifier> {
        return Pair(EntityAttributes.GENERIC_ATTACK_DAMAGE,
        EntityAttributeModifier(
        idName + "damage_attribute",
            damageIncreaseMultiplicative.toDouble(),
            EntityAttributeModifier.Operation.ADDITION
        ))
    }
}
