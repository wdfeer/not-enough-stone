package org.wdfeer.not_enough_stone

import eu.midnightdust.lib.config.MidnightConfig
import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory
import org.wdfeer.not_enough_stone.config.NotEnoughStoneConfig
import org.wdfeer.not_enough_stone.item.ModItems

object NotEnoughStone : ModInitializer {
	const val MOD_ID: String = "not_enough_stone"
	private val logger = LoggerFactory.getLogger("not_enough_stone")

	override fun onInitialize() {
		ModItems.initialize()

		MidnightConfig.init(MOD_ID, NotEnoughStoneConfig::class.java)

		logger.info("Mod $MOD_ID initialized!")
	}
}