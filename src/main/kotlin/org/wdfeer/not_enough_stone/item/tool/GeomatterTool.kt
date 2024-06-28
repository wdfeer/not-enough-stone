package org.wdfeer.not_enough_stone.item.tool

import kotlin.math.log
import kotlin.math.max
import kotlin.math.sqrt

interface GeomatterTool {
    fun getBonus(stones: Int): Float{
        var logarithm: Float = max(log(stones.toFloat(), 9f), 0f)
        if (logarithm.isNaN())
            logarithm = 0f

        return logarithm * sqrt(stones.toFloat()) / 100f
    }
}