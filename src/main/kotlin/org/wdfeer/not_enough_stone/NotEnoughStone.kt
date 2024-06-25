package org.wdfeer.not_enough_stone

import net.fabricmc.api.ModInitializer
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.slf4j.LoggerFactory
import org.wdfeer.not_enough_stone.item.ModItems
import org.wdfeer.not_enough_stone.recipe.GeomatterRecipe

object NotEnoughStone : ModInitializer {
	const val MOD_ID: String = "not_enough_stone"
	private val logger = LoggerFactory.getLogger("not_enough_stone")

	override fun onInitialize() {
		ModItems.initialize()

		Registry.register(Registries.RECIPE_TYPE, GeomatterRecipe.ID, GeomatterRecipe.Type)
		Registry.register(Registries.RECIPE_SERIALIZER, GeomatterRecipe.ID, GeomatterRecipe.Serializer)

		logger.info("Not Enough Stone initialized!")
	}
}