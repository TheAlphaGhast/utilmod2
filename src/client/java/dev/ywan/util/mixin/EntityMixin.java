package dev.ywan.util.mixin;

import dev.ywan.util.Config;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(method = "setYaw", at = @At("HEAD"))
    private void setYaw(float yaw, CallbackInfo ci) {
        //noinspection ConstantValue
        if (Config.getEnabled("boatrotate") && (Object) this instanceof ClientPlayerEntity player) {
            Entity vehicle = player.getControllingVehicle();
            if (vehicle instanceof AbstractBoatEntity) vehicle.setYaw(yaw);
        }
    }
}
