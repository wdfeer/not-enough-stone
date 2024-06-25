package org.wdfeer.not_enough_stone

import net.fabricmc.api.ModInitializer
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.slf4j.LoggerFactory
import org.wdfeer.not_enough_stone.recipe.StoneRecipe

object NotEnoughStone : ModInitializer {
	const val MOD_ID: String = "not_enough_stone"
	private val logger = LoggerFactory.getLogger("not_enough_stone")

	override fun onInitialize() {
		Registry.register(Registries.RECIPE_TYPE, StoneRecipe.id, StoneRecipe.Type)
		Registry.register(Registries.RECIPE_SERIALIZER, StoneRecipe.id, StoneRecipe.Serializer)
		logger.info("NotEnoughStone Mod Initialized!")
	}
}