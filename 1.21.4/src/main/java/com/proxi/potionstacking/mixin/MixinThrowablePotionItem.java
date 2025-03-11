package com.proxi.potionstacking.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ThrowablePotionItem.class)
public abstract class MixinThrowablePotionItem {
    
    @Inject(method = "method_7836", at = @At("RETURN"))
    private void addCooldown(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!world.isClient) {
            // Get the ItemStack the player is holding
            ItemStack stack = player.getStackInHand(hand);
            
            // Apply cooldown on the specific ItemStack
            player.getItemCooldownManager().set(stack, 20); // 20 ticks = 1 second
        }
    }
}
