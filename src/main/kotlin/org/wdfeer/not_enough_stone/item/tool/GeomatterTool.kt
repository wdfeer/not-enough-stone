package org.wdfeer.not_enough_stone.item.tool

import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.registry.RegistryKey
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
        if (logarithm.isNaN())
            logarithm = 0f

        return logarithm * min(stones * stones / 1e7f, 1f)
    }

    private fun getLinearSummand(stones: Int, multiplier: Float = 1f): Float {
        return multiplier * stones / 1e5f
    }

    fun getMultBonus(stones: Int): Float{
        return getLogarithmicSummand(stones) + min(stones / 300f, 0.5f) + getLinearSummand(stones)
    }

    fun getFlatBonus(stones: Int): Float{
        return getLogarithmicSummand(stones) + min(stones / 100f, 2f) + getLinearSummand(stones, 3f)
    }
}