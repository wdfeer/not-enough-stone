package org.wdfeer.not_enough_stone.item

import net.minecraft.item.Item
import net.minecraft.util.Identifier
import org.wdfeer.not_enough_stone.NotEnoughStone

abstract class ModItem (settings: Settings) : Item(settings) {
    abstract fun getIdName(): String;

    fun getId(): Identifier = Identifier(NotEnoughStone.MOD_ID, getIdName())
}