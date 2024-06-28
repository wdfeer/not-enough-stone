package org.wdfeer.not_enough_stone.item.tool

import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import kotlin.math.roundToInt

interface GeomatterWeapon : GeomatterTool {
    fun getDamageIncrease(stones: Int): Float {
        return getBonus(stones)
    }

    private fun getDamageIncreaseTooltipColor(damageIncrease: Float): Formatting {
        if (damageIncrease < 1.5f)
            return Formatting.GRAY
        else if (damageIncrease < 2f)
            return Formatting.WHITE
        else if (damageIncrease < 3f)
            return Formatting.GOLD

        return Formatting.AQUA
    }

    fun getDamageIncreaseTooltip(damageIncrease: Float): Text =
        Text.translatable("not_enough_stone.weapon_damage_increase_tooltip")
            .formatted(Formatting.GRAY)
            .append(
                Text.literal("+${((damageIncrease * 100f).roundToInt())}")
                    .formatted(getDamageIncreaseTooltipColor(damageIncrease))
            )

    fun getAttribute(idName: String, damageMult: Float): Pair<EntityAttribute, EntityAttributeModifier> {
        return Pair(EntityAttributes.GENERIC_ATTACK_DAMAGE,
        EntityAttributeModifier(
        idName + "damage_attribute",
            damageMult.toDouble(),
            EntityAttributeModifier.Operation.ADDITION
        ))
    }
}
