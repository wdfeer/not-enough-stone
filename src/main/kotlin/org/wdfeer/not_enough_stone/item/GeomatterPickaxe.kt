package org.wdfeer.not_enough_stone.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.BlockState
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ItemStack
import net.minecraft.item.PickaxeItem
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.world.World
import org.wdfeer.not_enough_stone.material.GeomatterMaterial
import org.wdfeer.not_enough_stone.tooltip.StonesCombinedTooltip
import kotlin.math.log
import kotlin.math.roundToInt

class GeomatterPickaxe : PickaxeItem(GeomatterMaterial.INSTANCE, 3, 1.2f, FabricItemSettings()), Identifiable {
    override fun getIdName(): String = "geomatter_pickaxe"

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
                Text.translatable("not_enough_stone.pickaxe_mining_speed_tooltip")
                    .formatted(Formatting.GRAY)
                    .append(Text.literal(((miningSpeed * 100f).roundToInt() / 100f).toString()).formatted(getMiningSpeedTooltipColor(miningSpeed))))
        }
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

    override fun getMiningSpeedMultiplier(stack: ItemStack?, state: BlockState?): Float {
        var mult = 1f

        if (stack != null) {
            val logarithm: Float = log((stack.orCreateNbt.getInt(Geomatter.STONES_COMBINED_NBT) - 26f), 9f)

            mult = (if (logarithm.isNaN()) 0f else logarithm) + 1f
        }

        return (if (state == null) 1f else super.getMiningSpeedMultiplier(stack, state)) * mult
    }

    override fun canRepair(stack: ItemStack?, ingredient: ItemStack?): Boolean {
        return false
    }
}