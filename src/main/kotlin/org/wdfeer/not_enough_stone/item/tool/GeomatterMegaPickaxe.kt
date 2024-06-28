package org.wdfeer.not_enough_stone.item.tool

import net.minecraft.block.BlockState
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
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
        tooltip?.add(Text.translatable("not_enough_stone.pickaxe_3x3_tooltip"))
    }

    override fun getMiningSpeed(stones: Int): Float {
        return super.getMiningSpeed(stones) / 9f
    }

    override fun postMine(
        stack: ItemStack?,
        world: World?,
        state: BlockState?,
        pos: BlockPos?,
        miner: LivingEntity?
    ): Boolean {
        // Ensure world and pos are not null
        if (world == null || pos == null) return super.postMine(stack, world, state, pos, miner)

        // Get the direction the player is facing to determine the plane of mining
        val facing = miner?.horizontalFacing ?: Direction.NORTH

        // Loop through a 3x3 area centered around the mined block
        for (dx in -1..1) {
            for (dy in -1..1) {
                for (dz in -1..1) {
                    // Calculate the position of the neighboring block
                    val targetPos = when (facing) {
                        Direction.NORTH, Direction.SOUTH -> pos.add(dx, dy, 0) // Horizontal plane (XZ)
                        Direction.EAST, Direction.WEST -> pos.add(0, dy, dz) // Horizontal plane (YZ)
                        else -> pos.add(dx, 0, dz) // Vertical plane (XY)
                    }

                    // Skip the original block
                    if (targetPos == pos) continue

                    // Break the block if it can be mined by the pickaxe
                    val targetState = world.getBlockState(targetPos)
                    if (targetState.getHardness(world, targetPos) != -1.0f) {
                        world.breakBlock(targetPos, true, miner)
                    }
                }
            }
        }

        return super.postMine(stack, world, state, pos, miner)
    }
}