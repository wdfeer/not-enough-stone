package org.wdfeer.not_enough_stone.item.tool

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents
import net.minecraft.block.BlockState
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import org.wdfeer.not_enough_stone.material.GeomatterMaterial

open class GeomatterMegaPickaxe(damage: Int = 4, attackSpeed: Float = 1.2f, material: ToolMaterial = GeomatterMaterial.INSTANCE) : GeomatterPickaxe(damage, attackSpeed) {
    override fun getIdName(): String = "geomatter_mega_pickaxe"

    override fun getMiningSpeedTooltip(miningSpeed: Float): Text {
        return super.getMiningSpeedTooltip(miningSpeed / 9f)
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

    private fun beforeMine(
        world: World,
        player: PlayerEntity,
        pos: BlockPos,
    ): Boolean {
        val stack = player.mainHandStack
        if (stack.isOf(this) && canAreaMine(stack)) {
            val direction = getDirection(player)
            breakSquare(direction, world, pos, player)
        }

        return true
    }

    protected open fun canAreaMine(stack: ItemStack): Boolean = true

    private fun getDirection(miner: LivingEntity): Direction {
        val hitResult = miner.raycast(5.0, 0.0f, false) as? BlockHitResult ?: return Direction.NORTH
        return hitResult.side
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