package org.wdfeer.not_enough_stone

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory

object NotEnoughStone : ModInitializer {
    private val logger = LoggerFactory.getLogger("not_enough_stone")

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		logger.info("Hello Fabric world!")
	}
}