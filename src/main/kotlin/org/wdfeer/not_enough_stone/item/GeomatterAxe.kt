package org.wdfeer.not_enough_stone.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.BlockState
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.AxeItem
import net.minecraft.item.ItemStack
import net.minecraft.item.PickaxeItem
import net.minecraft.text.Text
import net.minecraft.world.World
import org.wdfeer.not_enough_stone.material.GeomatterMaterial
import org.wdfeer.not_enough_stone.tooltip.StonesCombinedTooltip

class GeomatterAxe : AxeItem(GeomatterMaterial.INSTANCE, 3f, 1f, FabricItemSettings()), GeomatterMiningTool, Identifiable {
    override fun getIdName(): String = "geomatter_axe"

    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        StonesCombinedTooltip.appendTooltip(stack, tooltip)

        if (tooltip != null){
            val miningSpeed: Float = getMiningSpeedMultiplier(stack, null)
            tooltip.add(
                getMiningSpeedTooltip(miningSpeed)
            )
        }
    }

    override fun getMiningSpeedMultiplier(stack: ItemStack?, state: BlockState?): Float {
        var multiplier = 1f

        if (stack != null && (state == null || isSuitableFor(state)) && stack.orCreateNbt.contains(Geomatter.STONES_COMBINED_NBT)) {
            multiplier = getMiningSpeed(stack.orCreateNbt.getInt(Geomatter.STONES_COMBINED_NBT))
        }

        return (if (state == null) 1f else super.getMiningSpeedMultiplier(stack, state)) * multiplier
    }
}