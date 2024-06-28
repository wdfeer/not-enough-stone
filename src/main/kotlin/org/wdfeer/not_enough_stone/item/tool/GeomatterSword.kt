package org.wdfeer.not_enough_stone.item.tool

import com.google.common.collect.Multimap
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.ItemStack
import net.minecraft.item.SwordItem
import net.minecraft.text.Text
import net.minecraft.world.World
import org.wdfeer.not_enough_stone.item.Geomatter
import org.wdfeer.not_enough_stone.item.Identifiable
import org.wdfeer.not_enough_stone.material.GeomatterMaterial
import org.wdfeer.not_enough_stone.tooltip.StonesCombinedTooltip

class GeomatterSword : SwordItem(GeomatterMaterial.INSTANCE, 2, 1.6f - 4f, FabricItemSettings()), GeomatterWeapon,
    Identifiable {
    override fun getIdName(): String = "geomatter_sword"

    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        StonesCombinedTooltip.appendTooltip(stack, tooltip)
        if (stack != null) {
            tooltip?.add(getDamageIncreaseTooltip(getDamageIncrease(stack.orCreateNbt.getInt(Geomatter.STONES_COMBINED_NBT))))
        }
    }

    override fun getAttributeModifiers(
        stack: ItemStack?,
        slot: EquipmentSlot?
    ): Multimap<EntityAttribute, EntityAttributeModifier> {
        if (stack == null || slot == null) return getAttributeModifiers(stack, slot)

        return getAttributeModifiers(getIdName(), stack, slot, getAttributeModifiers(slot))
    }
}