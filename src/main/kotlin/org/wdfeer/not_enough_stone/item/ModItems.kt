package org.wdfeer.not_enough_stone.item

import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.wdfeer.not_enough_stone.item.tool.*

class ModItems {
    companion object{
        val GEOMATTER = Geomatter()
        val items: Array<Item> = arrayOf(
            GEOMATTER,
            GeomatterPickaxe(),
            GeomatterAxe(),
            GeomatterSword(),
            GeomatterShovel(),
            GeomatterHoe()
        )

        fun initialize() {
            for (item: Item in items){
                Registry.register(Registries.ITEM, (item as Identifiable).getId(), item)
            }
        }
    }
}