package org.wdfeer.not_enough_stone.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item

class Geomatter : Item(FabricItemSettings()), Identifiable {
    override fun getIdName(): String = "geomatter"
}