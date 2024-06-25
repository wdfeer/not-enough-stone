package org.wdfeer.not_enough_stone.recipe

import net.minecraft.inventory.RecipeInputInventory
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.PacketByteBuf
import net.minecraft.recipe.CraftingRecipe
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.RecipeType
import net.minecraft.recipe.book.CraftingRecipeCategory
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.util.Identifier
import net.minecraft.world.World
import org.wdfeer.not_enough_stone.NotEnoughStone
import org.wdfeer.not_enough_stone.item.ModItems

class GeomatterPickaxeRecipe : CraftingRecipe {
    companion object {
        val ID: Identifier = Identifier(NotEnoughStone.MOD_ID, "geomatter_pickaxe")
    }

    override fun matches(inventory: RecipeInputInventory, world: World): Boolean {
        return true
    }

    override fun craft(inventory: RecipeInputInventory, registryManager: DynamicRegistryManager?): ItemStack {
        var totalCombined = 0

        for (i in 0 until inventory.size()) {
            val stack = inventory.getStack(i)
            val nbt = stack.nbt
            totalCombined += nbt!!.getInt("stones_combined")
        }

        val resultStack = ItemStack(ModItems.GEOMATTER_PICKAXE)
        val resultNbt = NbtCompound()
        resultNbt.putInt("stones_combined", totalCombined)
        resultStack.nbt = resultNbt
        return resultStack
    }

    override fun fits(width: Int, height: Int): Boolean {
        return width == 3 && height == 3
    }

    override fun getOutput(registryManager: DynamicRegistryManager?): ItemStack {
        val resultStack = ItemStack(ModItems.GEOMATTER_PICKAXE)
        val resultNbt = NbtCompound()
        resultNbt.putInt("stones_combined", 27)
        resultStack.nbt = resultNbt
        return resultStack
    }

    override fun getId(): Identifier {
        return ID
    }

    override fun getSerializer(): RecipeSerializer<*> {
        return Serializer
    }

    override fun getCategory(): CraftingRecipeCategory {
        return CraftingRecipeCategory.EQUIPMENT
    }

    object Serializer : RecipeSerializer<GeomatterPickaxeRecipe> {
        override fun read(id: Identifier?, json: com.google.gson.JsonObject?): GeomatterPickaxeRecipe {
            return GeomatterPickaxeRecipe()
        }

        override fun read(id: Identifier, buf: PacketByteBuf): GeomatterPickaxeRecipe {
            return GeomatterPickaxeRecipe()
        }

        override fun write(buf: PacketByteBuf, recipe: GeomatterPickaxeRecipe) {
            // No additional data to write
        }
    }

    object Type : RecipeType<GeomatterPickaxeRecipe> {
        override fun toString(): String {
            return ID.toString()
        }
    }
}