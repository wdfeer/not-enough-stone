package org.wdfeer.not_enough_stone

import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.FabricLoader
import org.slf4j.LoggerFactory
import org.wdfeer.not_enough_stone.item.ModItems

object NotEnoughStone : ModInitializer {
	const val MOD_ID: String = "not_enough_stone"
	private val logger = LoggerFactory.getLogger("not_enough_stone")

	override fun onInitialize() {
		ModItems.initialize()

		logger.info("Mod $MOD_ID initialized!")
	}
}