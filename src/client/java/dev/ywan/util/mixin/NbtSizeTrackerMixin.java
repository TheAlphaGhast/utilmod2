package dev.ywan.util.mixin;

import dev.ywan.util.Config;
//import dev.ywan.util.ModClient;
import net.minecraft.nbt.NbtSizeTracker;
//import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NbtSizeTracker.class)
public class NbtSizeTrackerMixin {
    @Shadow
    private long allocatedBytes;
    //@Shadow
    //@Final
    //private long maxBytes;

    @Inject(method = "add(J)V", at = @At("HEAD"), cancellable = true)
    public void add(long bytes, CallbackInfo ci) {
        if (!Config.getEnabled("largenbt")) return;
        if (bytes < 0L) {
            throw new IllegalArgumentException("Tried to account NBT tag with negative size: " + bytes);
        } //else if (this.allocatedBytes + bytes > this.maxBytes) {
            //ModClient.LOGGER.warn("Tried to read NBT tag that was too big; tried to allocate: " + this.allocatedBytes + " + " + bytes + " bytes where max allowed: " + this.maxBytes);
        //}
        this.allocatedBytes += bytes;
        //ModClient.LOGGER.debug("New size: {}", this.allocatedBytes);
        ci.cancel();
    }
}
