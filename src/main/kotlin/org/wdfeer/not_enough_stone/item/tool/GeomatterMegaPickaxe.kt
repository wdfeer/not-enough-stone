package org.wdfeer.not_enough_stone.item.tool

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.block.BlockState
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World

class GeomatterMegaPickaxe : GeomatterPickaxe(4, 1f), AreaMiner {
    override fun getIdName(): String = "geomatter_mega_pickaxe"

    override fun getRadius(stack: ItemStack): Int {
        return 1
    }

    override fun canAreaMine(stack: ItemStack): Boolean {
        return true
    }

    override fun getMiningSpeedTooltipColor(miningSpeed: Float): Formatting {
        return super.getMiningSpeedTooltipColor(miningSpeed * 9)
    }

    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        super.appendTooltip(stack, world, tooltip, context)
        tooltip?.add(Text.translatable("not_enough_stone.pickaxe_3x3_tooltip").formatted(Formatting.GRAY))
    }

    override fun getMiningSpeedMultiplier(stack: ItemStack?, state: BlockState?): Float {
        return super.getMiningSpeedMultiplier(stack, state) / 9f
    }

    init {
        PlayerBlockBreakEvents.BEFORE.register(PlayerBlockBreakEvents.Before { world, playerEntity, blockPos, _, _ ->
            beforeMine(
                world,
                playerEntity,
                blockPos
            )
        })
    }


}