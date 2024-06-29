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

    fun getMultBonus(stones: Int): Float{
        var logarithm: Float = max(log(stones.toFloat(), 10f), 0f)
        if (logarithm.isNaN())
            logarithm = 0f

        return logarithm * min(stones * stones / 1e7f, 1f) + min(stones / 300f, 0.5f)
    }

    fun getFlatBonus(stones: Int): Float{
        var logarithm: Float = max(log(stones.toFloat(), 10f), 0f)

        if (logarithm.isNaN())
            logarithm = 0f

        return logarithm * min(stones * stones / 1e7f, 1f) + min(stones / 100f, 2f)
    }
}