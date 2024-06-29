package org.wdfeer.not_enough_stone.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroups
import net.minecraft.item.ItemStack
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import net.minecraft.world.World
import org.wdfeer.not_enough_stone.tooltip.StonesCombinedTooltip

class Geomatter : Item(FabricItemSettings()), Identifiable, Groupable, StoneCombiner {
    companion object {
        const val STONES_COMBINED_NBT: String = "stone_combined"
    }

    override fun getIdName(): String = "geomatter"

    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        StonesCombinedTooltip.appendTooltip(stack, tooltip)
    }

    override fun getGroup(): RegistryKey<ItemGroup>? {
        return ItemGroups.INGREDIENTS
    }
}