package org.wdfeer.not_enough_stone.recipe

import net.minecraft.inventory.RecipeInputInventory
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

class StoneRecipe() : CraftingRecipe {
    companion object {
        val id: Identifier = Identifier(NotEnoughStone.MOD_ID, "stone_combination")
    }

    override fun matches(inventory: RecipeInputInventory, world: World): Boolean {
        if (inventory.width != 3 || inventory.height != 3) return false

        for (i in 0 until inventory.size()) {
            val stack = inventory.getStack(i)
            if (stack.item != Items.STONE) return false
        }
        return true
    }

    override fun craft(inventory: RecipeInputInventory, registryManager: DynamicRegistryManager?): ItemStack {
        var totalCombined = 0

        for (i in 0 until inventory.size()) {
            val stack = inventory.getStack(i)
            val nbt = stack.nbt
            totalCombined += nbt?.getInt("stones_combined") ?: 1
        }

        val resultStack = ItemStack(Items.STONE)
        val resultNbt = NbtCompound()
        resultNbt.putInt("stones_combined", totalCombined)
        resultStack.nbt = resultNbt
        return resultStack
    }

    override fun fits(width: Int, height: Int): Boolean {
        return width == 3 && height == 3
    }

    override fun getOutput(registryManager: DynamicRegistryManager?): ItemStack {
        val resultStack = ItemStack(Items.STONE)
        val resultNbt = NbtCompound()
        resultNbt.putInt("stones_combined", 9)
        resultStack.nbt = resultNbt
        return resultStack
    }

    override fun getId(): Identifier {
        return id
    }

    override fun getSerializer(): RecipeSerializer<*> {
        return Serializer
    }

    override fun getCategory(): CraftingRecipeCategory {
        return CraftingRecipeCategory.MISC
    }

    object Serializer : RecipeSerializer<StoneRecipe> {
        override fun read(id: Identifier?, json: com.google.gson.JsonObject?): StoneRecipe {
            return StoneRecipe()
        }

        override fun read(id: Identifier, buf: PacketByteBuf): StoneRecipe {
            return StoneRecipe()
        }

        override fun write(buf: PacketByteBuf, recipe: StoneRecipe) {
            // No additional data to write
        }
    }

    object Type : RecipeType<StoneRecipe> {
        override fun toString(): String {
            return id.toString()
        }
    }
}
