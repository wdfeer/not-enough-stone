package org.wdfeer.not_enough_stone.item

import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.wdfeer.not_enough_stone.item.common.Identifiable
import org.wdfeer.not_enough_stone.item.tool.*
import org.wdfeer.not_enough_stone.item.weapon.GeomatterSpear
import org.wdfeer.not_enough_stone.item.weapon.GeomatterSword

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
            GeomatterMegaPickaxe(),
            GeomatterSpear()
        )

        fun initialize() {
            for (item: Item in items){
                Registry.register(Registries.ITEM, (item as Identifiable).getId(), item)
            }
        }
    }
}