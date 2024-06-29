package org.wdfeer.not_enough_stone.item

import net.minecraft.item.ItemGroup
import net.minecraft.registry.RegistryKey

interface Groupable {
    fun getGroup(): RegistryKey<ItemGroup>?
}