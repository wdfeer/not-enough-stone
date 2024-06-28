package org.wdfeer.not_enough_stone.item.tool

import net.minecraft.text.Text
import net.minecraft.util.Formatting
import kotlin.math.roundToInt

interface GeomatterMiningTool : GeomatterTool {
    fun getMiningSpeed(stones: Int): Float {
        return getBonus(stones) + 1f
    }

    private fun getMiningSpeedTooltipColor(miningSpeed: Float): Formatting {
        if (miningSpeed < 2)
            return Formatting.GRAY
        else if (miningSpeed < 3)
            return Formatting.WHITE
        else if (miningSpeed < 4)
            return Formatting.GOLD

        return Formatting.AQUA
    }

    fun getMiningSpeedTooltip(miningSpeed: Float): Text =
        Text.translatable("not_enough_stone.pickaxe_mining_speed_tooltip")
            .formatted(Formatting.GRAY)
            .append(
                Text.literal(((miningSpeed * 100f).roundToInt() / 100f).toString())
                    .formatted(getMiningSpeedTooltipColor(miningSpeed))
            )
}