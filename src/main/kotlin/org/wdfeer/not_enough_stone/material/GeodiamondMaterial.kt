package org.wdfeer.not_enough_stone.material

import net.minecraft.item.ToolMaterials
import net.minecraft.recipe.Ingredient

class GeodiamondMaterial : GeomatterMaterial() {
    companion object {
        val INSTANCE: GeodiamondMaterial = GeodiamondMaterial()
    }
    override fun getDurability(): Int = super.getDurability() * 3

    override fun getMiningSpeedMultiplier(): Float = ToolMaterials.IRON.miningSpeedMultiplier

    override fun getAttackDamage(): Float = ToolMaterials.IRON.attackDamage

    override fun getMiningLevel(): Int = ToolMaterials.IRON.miningLevel

    override fun getEnchantability(): Int = ToolMaterials.IRON.enchantability

    override fun getRepairIngredient(): Ingredient = Ingredient.EMPTY
}