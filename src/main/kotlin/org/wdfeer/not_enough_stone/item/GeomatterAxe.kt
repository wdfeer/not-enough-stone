package org.wdfeer.not_enough_stone.item

import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.BlockState
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.item.AxeItem
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.PickaxeItem
import net.minecraft.text.Text
import net.minecraft.world.World
import org.wdfeer.not_enough_stone.material.GeomatterMaterial
import org.wdfeer.not_enough_stone.tooltip.StonesCombinedTooltip

class GeomatterAxe : AxeItem(GeomatterMaterial.INSTANCE, 3f, 1f, FabricItemSettings()), GeomatterMiningTool, GeomatterWeapon, Identifiable {
    override fun getIdName(): String = "geomatter_axe"

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
                tooltip.add(getAttackDamageMultTooltip(getDamageMult(stack.orCreateNbt.getInt(Geomatter.STONES_COMBINED_NBT))))
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
        val map = HashMultimap.create(getAttributeModifiers(slot))
        if (stack != null && slot == EquipmentSlot.MAINHAND) {
            map.put(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                EntityAttributeModifier(
                    getIdName() + "damage_attribute",
                    getDamageMult(stack.orCreateNbt.getInt(Geomatter.STONES_COMBINED_NBT)).toDouble(),
                    EntityAttributeModifier.Operation.MULTIPLY_TOTAL
                )
            )
        }
        return map
    }

    override fun getMinStones(): Int {
        return 26
    }
}