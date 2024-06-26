package org.wdfeer.not_enough_stone.item

import net.minecraft.text.Text
import net.minecraft.util.Formatting
import kotlin.math.log
import kotlin.math.roundToInt

interface GeomatterWeapon : GeomatterTool {
    fun getDamageMult(stones: Int): Float {
        val logarithm: Float = log(stones - 26f, 9f)

        return (if (logarithm.isNaN()) 0f else logarithm) * stones / 100f + 1f
    }

    private fun getAttackDamageMultTooltipColor(damageIncrease: Float): Formatting {
        if (damageIncrease < 1.2f)
            return Formatting.GRAY
        else if (damageIncrease < 1.5f)
            return Formatting.WHITE
        else if (damageIncrease < 2f)
            return Formatting.GOLD

        return Formatting.AQUA
    }

    fun getAttackDamageMultTooltip(damageIncrease: Float): Text =
        Text.translatable("not_enough_stone.pickaxe_mining_speed_tooltip")
            .formatted(Formatting.GRAY)
            .append(
                Text.literal(((damageIncrease * 100f).roundToInt() / 100f).toString())
                    .formatted(getAttackDamageMultTooltipColor(damageIncrease))
            )
}