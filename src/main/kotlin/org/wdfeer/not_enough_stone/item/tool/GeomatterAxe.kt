package org.wdfeer.not_enough_stone.item.tool

import com.google.common.collect.Multimap
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.BlockState
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.AxeItem
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.RegistryKey
import net.minecraft.text.Text
import net.minecraft.world.World
import org.wdfeer.not_enough_stone.item.Geomatter
import org.wdfeer.not_enough_stone.item.Identifiable
import org.wdfeer.not_enough_stone.material.GeomatterMaterial
import org.wdfeer.not_enough_stone.tooltip.StonesCombinedTooltip

class GeomatterAxe : AxeItem(GeomatterMaterial.INSTANCE, 6f, 0.9f - 4f, FabricItemSettings()), GeomatterMiningTool,
    GeomatterWeapon, Identifiable {
    override fun getIdName(): String = "geomatter_axe"

    override fun getGroup(): RegistryKey<ItemGroup>? {
        return super<GeomatterMiningTool>.getGroup()
    }

    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        StonesCombinedTooltip.appendTooltip(stack, tooltip)

        if (tooltip != null){
            val miningSpeed: Float = getMiningSpeedMultiplier(stack, null)
            tooltip.add(
                getMiningSpeedTooltip(miningSpeed)
            )

            if (stack != null) {
                tooltip.add(getDamageIncreaseTooltip(getDamageIncrease(stack.orCreateNbt.getInt(Geomatter.STONES_COMBINED_NBT))))
            }
        }

        Items.STONE_SWORD
    }

    override fun getMiningSpeedMultiplier(stack: ItemStack?, state: BlockState?): Float {
        var multiplier = 1f

        if (stack != null && (state == null || isSuitableFor(state)) && stack.orCreateNbt.contains(Geomatter.STONES_COMBINED_NBT)) {
            multiplier = getMiningSpeed(stack.orCreateNbt.getInt(Geomatter.STONES_COMBINED_NBT))
        }

        return (if (state == null) 1f else super.getMiningSpeedMultiplier(stack, state)) * multiplier
    }

    override fun getAttributeModifiers(
        stack: ItemStack?,
        slot: EquipmentSlot?
    ): Multimap<EntityAttribute, EntityAttributeModifier> {
        if (stack == null || slot == null) return getAttributeModifiers(stack, slot)

        return getAttributeModifiers(getIdName(), stack, slot, getAttributeModifiers(slot))
    }

    override fun getDamageIncrease(stones: Int): Float {
        return super.getDamageIncrease(stones) * 1.5f
    }
}