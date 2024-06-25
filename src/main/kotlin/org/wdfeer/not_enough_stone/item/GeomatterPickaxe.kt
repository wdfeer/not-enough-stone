package org.wdfeer.not_enough_stone.item

import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.PickaxeItem
import org.wdfeer.not_enough_stone.material.GeomatterMaterial

class GeomatterPickaxe : PickaxeItem(GeomatterMaterial.INSTANCE, 3, 1.2f, FabricItemSettings()), Identifiable {
    override fun getIdName(): String = "geomatter_pickaxe"
}