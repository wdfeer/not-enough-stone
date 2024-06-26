package org.wdfeer.not_enough_stone.tooltip

import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import org.wdfeer.not_enough_stone.item.Geomatter

class StonesCombinedTooltip {
    companion object {
        fun appendTooltip(stack: ItemStack?, tooltip: MutableList<Text>?) {
            if (stack != null && tooltip != null && stack.hasNbt() && stack.getOrCreateNbt().contains(Geomatter.STONES_COMBINED_NBT)) {
                val stones = stack.getOrCreateNbt().getInt(Geomatter.STONES_COMBINED_NBT)
                tooltip.add(
                    Text.translatable("not_enough_stone.stones_combined_tooltip").append(stones.toString()).formatted(
                        Formatting.GRAY
                    )
                )
            }
        }
    }
}