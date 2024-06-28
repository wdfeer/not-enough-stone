package org.wdfeer.not_enough_stone.item.common

import kotlin.math.log
import kotlin.math.max
import kotlin.math.min

interface GeomatterTool : StoneCombiner {
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