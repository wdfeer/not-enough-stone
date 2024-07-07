package org.wdfeer.not_enough_stone.tag

import net.minecraft.item.Item
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import org.wdfeer.not_enough_stone.NotEnoughStone

object ModTags {
    val stoneCombinables: TagKey<Item> = TagKey.of(RegistryKeys.ITEM, Identifier(NotEnoughStone.MOD_ID, "stone_combinable"))
}