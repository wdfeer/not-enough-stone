package org.wdfeer.not_enough_stone.item.common

import net.minecraft.util.Identifier
import org.wdfeer.not_enough_stone.NotEnoughStone

interface Identifiable {
    fun getIdName(): String;

    fun getId(): Identifier = Identifier(NotEnoughStone.MOD_ID, getIdName())
}