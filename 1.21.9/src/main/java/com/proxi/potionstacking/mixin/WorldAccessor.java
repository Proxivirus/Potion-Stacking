// had to add this because isClient became a private variable (classic Mojunk)
package com.proxi.potionstacking.mixin;

import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(World.class)
public interface WorldAccessor {
    @Accessor("isClient")
    boolean isClient();
}
