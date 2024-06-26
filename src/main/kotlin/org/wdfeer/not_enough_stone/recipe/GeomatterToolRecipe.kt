package org.wdfeer.not_enough_stone.recipe

import kotlinx.serialization.json.JsonObject
import net.minecraft.inventory.RecipeInputInventory
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketByteBuf
import net.minecraft.recipe.Ingredient
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.RecipeType
import net.minecraft.recipe.ShapedRecipe
import net.minecraft.recipe.book.CraftingRecipeCategory
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.util.Identifier
import net.minecraft.util.collection.DefaultedList
import org.wdfeer.not_enough_stone.NotEnoughStone
import org.wdfeer.not_enough_stone.item.Geomatter
import org.wdfeer.not_enough_stone.item.ModItems

class GeomatterToolRecipe(
    id: Identifier?,
    group: String?,
    input: DefaultedList<Ingredient>?,
    output: ItemStack?
) : ShapedRecipe(id, group, CraftingRecipeCategory.EQUIPMENT, 3, 3, input, output) {
    companion object {
        val ID = Identifier(NotEnoughStone.MOD_ID, "geomatter_tool_recipe")
    }

    override fun craft(
        inventory: RecipeInputInventory?,
        dynamicRegistryManager: DynamicRegistryManager?
    ): ItemStack {
        val result = super.craft(inventory, dynamicRegistryManager)

        fun getStonesConsumedTotal(inventory: RecipeInputInventory): Int =
            inventory.inputStacks.sumOf { x -> x.orCreateNbt.getInt(Geomatter.STONES_COMBINED_NBT) }
        result.orCreateNbt.putInt(Geomatter.STONES_COMBINED_NBT, inventory?.let { getStonesConsumedTotal(it) } ?: 0)

        return result
    }

    override fun getSerializer(): RecipeSerializer<GeomatterToolRecipe> {
        return Serializer
    }

    override fun getType(): RecipeType<GeomatterToolRecipe> {
        return Type
    }

    object Serializer : RecipeSerializer<GeomatterToolRecipe> {
        override fun read(id: Identifier?, json: com.google.gson.JsonObject?): GeomatterToolRecipe {
            val shapedRecipe: ShapedRecipe = ShapedRecipe.Serializer().read(id, json)
            return GeomatterToolRecipe(id, shapedRecipe.group, shapedRecipe.ingredients, shapedRecipe.getOutput(
                DynamicRegistryManager.EMPTY))
        }

        override fun read(id: Identifier, buf: PacketByteBuf): GeomatterToolRecipe {
            val shapedRecipe: ShapedRecipe = ShapedRecipe.Serializer().read(id, buf)
            return GeomatterToolRecipe(id, shapedRecipe.group, shapedRecipe.ingredients, shapedRecipe.getOutput(
                DynamicRegistryManager.EMPTY))
        }

        override fun write(buf: PacketByteBuf?, recipe: GeomatterToolRecipe?) {
            ShapedRecipe.Serializer().write(buf, recipe)
        }
    }

    object Type : RecipeType<GeomatterToolRecipe> {
        override fun toString(): String {
            return ID.toString()
        }
    }
}