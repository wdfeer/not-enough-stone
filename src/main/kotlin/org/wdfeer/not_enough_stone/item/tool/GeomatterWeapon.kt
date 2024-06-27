package org.wdfeer.not_enough_stone.item.tool

import net.minecraft.text.Text
import net.minecraft.util.Formatting
import kotlin.math.log
import kotlin.math.roundToInt

interface GeomatterWeapon : GeomatterTool {
    fun getMinStones(): Int

    fun getDamageIncrease(stones: Int): Float {
        val logarithm: Float = log(stones + 1f - getMinStones(), 9f)

        return (if (logarithm.isNaN()) 0f else logarithm) * stones / 100f + 1f
    }

    private fun getDamageIncreaseTooltipColor(damageIncrease: Float): Formatting {
        if (damageIncrease < 1.2f)
            return Formatting.GRAY
        else if (damageIncrease < 1.5f)
            return Formatting.WHITE
        else if (damageIncrease < 2f)
            return Formatting.GOLD

        return Formatting.AQUA
    }

    fun getDamageIncreaseTooltip(damageIncrease: Float): Text =
        Text.translatable("not_enough_stone.weapon_damage_increase_tooltip")
            .formatted(Formatting.GRAY)
            .append(
                Text.literal(((damageIncrease * 100f).roundToInt() / 100f).toString())
                    .formatted(getDamageIncreaseTooltipColor(damageIncrease))
            )
}