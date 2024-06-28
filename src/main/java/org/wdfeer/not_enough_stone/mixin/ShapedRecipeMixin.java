package org.wdfeer.not_enough_stone.mixin;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.wdfeer.not_enough_stone.item.Geomatter;
import org.wdfeer.not_enough_stone.item.ModItems;
import org.wdfeer.not_enough_stone.tag.ModTags;

import java.util.Arrays;

@Mixin(ShapedRecipe.class)
public class ShapedRecipeMixin {
    @Inject(method = "craft(Lnet/minecraft/inventory/Inventory;Lnet/minecraft/registry/DynamicRegistryManager;)Lnet/minecraft/item/ItemStack;", at = @At("RETURN"), cancellable = true)
    void injectCraft(Inventory inventory, DynamicRegistryManager registryManager, CallbackInfoReturnable<ItemStack> cir){
        ItemStack result = cir.getReturnValue();
        if (Arrays.stream(ModItems.Companion.getItems()).anyMatch(item -> item == result.getItem())){
            result.getOrCreateNbt().putInt(Geomatter.STONES_COMBINED_NBT, getStonesCombined(inventory));
            cir.setReturnValue(result);
        }
    }

    @Unique
    int getStonesCombined(Inventory input) {
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getStack(i);
            if (stack == null) continue;
            if (stack.getNbt() != null && stack.getNbt().contains(Geomatter.STONES_COMBINED_NBT)){
                sum += stack.getNbt().getInt(Geomatter.STONES_COMBINED_NBT);
            } else if (stack.streamTags().anyMatch(tagKey -> ModTags.Companion.getStoneCombinables() == tagKey)) {
                sum += 1;
            }
        }
        return sum;
    }
}
