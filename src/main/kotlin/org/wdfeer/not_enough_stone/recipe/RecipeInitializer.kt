package org.wdfeer.not_enough_stone.recipe

import net.minecraft.registry.Registries
import net.minecraft.registry.Registry

class RecipeInitializer {
    companion object {
        fun initialize(): Unit {
            Registry.register(Registries.RECIPE_TYPE, GeomatterRecipe.ID, GeomatterRecipe.Type)
            Registry.register(Registries.RECIPE_SERIALIZER, GeomatterRecipe.ID, GeomatterRecipe.Serializer)

            Registry.register(Registries.RECIPE_TYPE, GeomatterPickaxeRecipe.ID, GeomatterPickaxeRecipe.Type)
            Registry.register(Registries.RECIPE_SERIALIZER, GeomatterPickaxeRecipe.ID, GeomatterPickaxeRecipe.Serializer)
        }
    }
}