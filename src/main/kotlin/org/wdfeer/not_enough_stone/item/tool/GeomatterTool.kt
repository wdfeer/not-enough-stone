package org.wdfeer.not_enough_stone.item.tool

import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.registry.RegistryKey
import org.wdfeer.not_enough_stone.config.NotEnoughStoneConfig
import org.wdfeer.not_enough_stone.item.Groupable
import org.wdfeer.not_enough_stone.item.StoneCombiner
import kotlin.math.log
import kotlin.math.max
import kotlin.math.min

interface GeomatterTool : StoneCombiner, Groupable {
    override fun getGroup(): RegistryKey<ItemGroup>? {
        return ItemGroups.TOOLS
    }

    private fun getLogarithmicSummand(stones: Int): Float {
        var logarithm: Float = max(log(stones.toFloat(), 10f), 0f)
        if (logarithm.isNaN() || logarithm < 0f)
            logarithm = 0f

        return logarithm * min(stones / 1e7f * stones, 1f) * NotEnoughStoneConfig.getLogarithmicMultiplier()
    }

    private fun getSmallLinearSummand(stones: Int, divisor: Float, max: Float): Float {
        return min(stones / divisor, max) * NotEnoughStoneConfig.getSmallLinearMultiplier()
    }

    private fun getBigLinearSummand(stones: Int, multiplier: Float = 1f): Float {
        return multiplier * stones / 1e5f  * NotEnoughStoneConfig.getBigLinearMultiplier()
    }

    fun getMultBonus(stones: Int): Float{
        return getLogarithmicSummand(stones) + getSmallLinearSummand(stones, 300f, 0.5f) + getBigLinearSummand(stones)
    }

    fun getFlatBonus(stones: Int): Float{
        return getLogarithmicSummand(stones) + getSmallLinearSummand(stones, 100f, 2f) + getBigLinearSummand(stones, 3f)
    }
}