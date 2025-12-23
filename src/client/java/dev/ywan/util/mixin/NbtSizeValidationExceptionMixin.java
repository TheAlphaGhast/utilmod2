package dev.ywan.util.mixin;

import dev.ywan.util.ModClient;
import net.minecraft.nbt.NbtSizeValidationException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NbtSizeValidationException.class)
public class NbtSizeValidationExceptionMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(String string, CallbackInfo ci) {
        NbtSizeValidationException e = (NbtSizeValidationException)(Object) this;
        ModClient.LOGGER.error("Nbt error created!", e);
    }
}
