package org.wdfeer.not_enough_stone.item

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.wdfeer.not_enough_stone.item.tool.*

class ModItems {
    companion object{
        val GEOMATTER = Geomatter()
        private val items: Array<Item> = arrayOf(
            GEOMATTER,
            GeomatterPickaxe(),
            GeomatterAxe(),
            GeomatterSword(),
            GeomatterShovel(),
            GeomatterHoe(),
            GeomatterMegaPickaxe()
        )

        fun initialize() {
            for (item: Item in items){
                Registry.register(Registries.ITEM, (item as Identifiable).getId(), item)
                if (item is Groupable)
                    ItemGroupEvents.modifyEntriesEvent(item.getGroup()).register { content -> content.add(item) }
            }
        }
    }
}