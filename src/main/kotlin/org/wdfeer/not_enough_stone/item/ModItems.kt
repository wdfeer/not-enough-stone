package org.wdfeer.not_enough_stone.item

import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import org.wdfeer.not_enough_stone.NotEnoughStone

class ModItems {
    companion object{
        public val GEOMATTER = Geomatter()
        private val items: Array<ModItem> = arrayOf(
            GEOMATTER
        )

        fun initialize(): Unit {
            for (item: ModItem in items){
                Registry.register(Registries.ITEM, item.getId(), item)
            }
        }
    }
}