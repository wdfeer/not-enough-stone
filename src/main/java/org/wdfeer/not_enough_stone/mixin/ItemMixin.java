package org.wdfeer.not_enough_stone.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "appendTooltip", at = @At("HEAD"))
    void AppendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci){
        if (stack.hasNbt() && stack.getOrCreateNbt().contains("stones_combined")){
            int stones = stack.getOrCreateNbt().getInt("stones_combined");
            tooltip.add(Text.translatable("org.wdfeer.not_enough_stone.tooltip").append(Integer.toString(stones)));
        }
    }
}