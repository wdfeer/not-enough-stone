package org.wdfeer.not_enough_stone.item

import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

class ModItems {
    companion object{
        val GEOMATTER = Geomatter()
        private val GEOMATTER_PICKAXE = GeomatterPickaxe()
        private val GEOMATTER_AXE = GeomatterAxe()
        val items: Array<Item> = arrayOf(
            GEOMATTER,
            GEOMATTER_PICKAXE,
            GEOMATTER_AXE
        )

        fun initialize() {
            for (item: Item in items){
                Registry.register(Registries.ITEM, (item as Identifiable).getId(), item)
            }
        }
    }
}