package org.wdfeer.not_enough_stone.item.tool

import net.minecraft.block.BlockState
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World

class GeomatterMegaPickaxe : GeomatterPickaxe(4, 1f) {
    override fun getIdName(): String = "geomatter_mega_pickaxe"

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

    override fun postMine(
        stack: ItemStack?,
        world: World?,
        state: BlockState?,
        pos: BlockPos?,
        miner: LivingEntity?
    ): Boolean {
        if (world == null || pos == null || !isSuitableFor(state)) return super.postMine(stack, world, state, pos, miner)

        val direction: Direction = getDirection(world, state, pos, miner)

        breakSquare(direction, world, pos, miner)

        return super.postMine(stack, world, state, pos, miner)
    }

    private fun getDirection(world: World, state: BlockState?, pos: BlockPos, miner: LivingEntity?): Direction {
        val pitch = miner?.pitch ?: 0f
        val facing = miner?.horizontalFacing ?: Direction.NORTH

        return when {
            pitch > 45 -> Direction.DOWN // Looking up
            pitch < -45 -> Direction.UP // Looking down
            else -> facing // Looking horizontally
        }
    }

    private fun breakSquare(direction: Direction, world: World, pos: BlockPos, miner: LivingEntity?) {
        // Loop through a 3x3 area centered around the mined block in the correct plane
        for (dx in -1..1) {
            for (dy in -1..1) {
                val targetPos = when (direction) {
                    Direction.UP, Direction.DOWN -> pos.add(dx, 0, dy) // XY plane
                    Direction.NORTH, Direction.SOUTH -> pos.add(dx, dy, 0) // XZ plane
                    Direction.EAST, Direction.WEST -> pos.add(0, dy, dx) // YZ plane
                    else -> pos.add(dx, 0, dy) // Default to XY plane
                }

                // Skip the original block
                if (targetPos == pos) continue

                // Break the block if it can be mined by the pickaxe
                val targetState = world.getBlockState(targetPos)
                if (targetState.getHardness(world, targetPos) != -1.0f && isSuitableFor(targetState)) {
                    world.breakBlock(targetPos, true, miner)
                }
            }
        }
    }
}