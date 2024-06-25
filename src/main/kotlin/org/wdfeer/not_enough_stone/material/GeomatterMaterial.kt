package org.wdfeer.not_enough_stone.material

import net.minecraft.item.ToolMaterial
import net.minecraft.item.ToolMaterials
import net.minecraft.recipe.Ingredient
import org.wdfeer.not_enough_stone.item.ModItems

class GeomatterMaterial : ToolMaterial {
    companion object {
        val INSTANCE: GeomatterMaterial = GeomatterMaterial()
    }

    override fun getDurability(): Int = ToolMaterials.STONE.durability * 9

    override fun getMiningSpeedMultiplier(): Float = ToolMaterials.STONE.miningSpeedMultiplier

    override fun getAttackDamage(): Float = ToolMaterials.STONE.attackDamage

    override fun getMiningLevel(): Int = ToolMaterials.IRON.miningLevel

    override fun getEnchantability(): Int = ToolMaterials.STONE.enchantability

    override fun getRepairIngredient(): Ingredient = Ingredient.ofItems(ModItems.GEOMATTER)
}