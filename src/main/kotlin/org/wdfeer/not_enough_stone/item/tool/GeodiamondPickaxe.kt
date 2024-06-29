package org.wdfeer.not_enough_stone.item.tool

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World
import org.wdfeer.not_enough_stone.material.GeodiamondMaterial

class GeodiamondPickaxe : GeomatterMegaPickaxe(6, material = GeodiamondMaterial.INSTANCE) {
    override fun getIdName(): String = "geodiamond_pickaxe"

    private val areaMineNbt: String = "3x3_mode"

    override fun canAreaMine(stack: ItemStack): Boolean {
        return stack.orCreateNbt.getBoolean(areaMineNbt)
    }


    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        if (user != null && user.isSneaking) {
            val stack = user.getStackInHand(hand)
            stack.orCreateNbt.putBoolean(areaMineNbt, !stack.orCreateNbt.getBoolean(areaMineNbt))
        }
            return super.use(world, user, hand)
    }

    override fun getMaxUseTime(stack: ItemStack?): Int {
        return 20
    }
}