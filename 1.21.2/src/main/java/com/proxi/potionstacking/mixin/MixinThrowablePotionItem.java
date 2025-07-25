package com.proxi.potionstacking.mixin;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import com.proxi.potionstacking.ModConfig;
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
    
	// I don't really need to access the config like this I could just raw dog it but it's easier to identify with the identical class over in MixinPotionItem
	public class ConfigAccess {
		public static int getThrowCooldown() {
			return AutoConfig.getConfigHolder(ModConfig.class).getConfig().throwCooldown;
		}
	}
	
	int throwCooldown = ConfigAccess.getThrowCooldown();
	
    @Inject(method = "method_7836", at = @At("RETURN"))
    private void addCooldown(World world, PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (!world.isClient) {
            // Get the ItemStack the player is holding
            ItemStack stack = player.getStackInHand(hand);
            
            // Apply cooldown on the specific ItemStack
            player.getItemCooldownManager().set(stack, throwCooldown*20); // 20 ticks = 1 second
        }
    }
}
