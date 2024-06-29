package org.wdfeer.not_enough_stone.item.tool

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.MiningToolItem
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World

interface AreaMiner{
    fun getRadius(stack: ItemStack): Int
    fun canAreaMine(stack: ItemStack): Boolean

    fun beforeMine(
        world: World,
        player: PlayerEntity,
        pos: BlockPos,
    ): Boolean {
        val stack = player.mainHandStack
        if (stack.isOf(this as MiningToolItem) && canAreaMine(stack)) {
            val direction = getDirection(player)
            breakSquare(direction, world, pos, player, stack)
        }

        return true
    }

    private fun getDirection(miner: LivingEntity): Direction {
        val hitResult = miner.raycast(5.0, 0.0f, false) as? BlockHitResult ?: return Direction.NORTH
        return hitResult.side
    }

    private fun breakSquare(direction: Direction, world: World, pos: BlockPos, miner: LivingEntity?, stack: ItemStack) {
        val radius = getRadius(stack)
        // Loop through a 3x3 area centered around the mined block in the correct plane
        for (dx in -radius..radius) {
            for (dy in -radius..radius) {
                val targetPos = when (direction) {
                    Direction.UP, Direction.DOWN -> pos.add(dx, 0, dy) // XY plane
                    Direction.NORTH, Direction.SOUTH -> pos.add(dx, dy, 0) // XZ plane
                    Direction.EAST, Direction.WEST -> pos.add(0, dy, dx) // YZ plane
                    else -> pos.add(dx, 0, dy) // Default to XY plane
                }

                if (targetPos == pos) continue

                // Break the block if it can be mined by the pickaxe
                val targetState = world.getBlockState(targetPos)
                if (targetState.getHardness(world, targetPos) != -1.0f && (this as MiningToolItem).isSuitableFor(targetState)) {
                    world.breakBlock(targetPos, true, miner)
                }
            }
        }
    }
}