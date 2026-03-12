package dev.ywan.util.mixin;

import dev.ywan.util.ModClient;
import dev.ywan.util.Util;
import io.netty.channel.ChannelFutureListener;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {

    @SuppressWarnings("StringBufferReplaceableByString")
    @Inject(method = "sendImmediately", at = @At("HEAD"))
    public void sendImmediately(Packet<?> packet, @Nullable ChannelFutureListener listener, boolean flush, CallbackInfo ci) {
        if (ModClient.alwaysFalse()) return;

        if (packet instanceof PlayerActionC2SPacket actionPacket) {
            StringBuilder stringBuilder = new StringBuilder("action=");
            stringBuilder.append(actionPacket.getAction());
            stringBuilder.append(" pos=");
            stringBuilder.append(actionPacket.getPos());
            stringBuilder.append(" direction=");
            stringBuilder.append(actionPacket.getDirection());
            stringBuilder.append(" sequence=");
            stringBuilder.append(actionPacket.getSequence());

            Util.informPlayer(stringBuilder.toString());
        } else if (packet instanceof PlayerInteractItemC2SPacket interactItemPacket) {
            StringBuilder stringBuilder = new StringBuilder("hand= ");
            stringBuilder.append(interactItemPacket.getHand());
            stringBuilder.append(" sequence=");
            stringBuilder.append(interactItemPacket.getSequence());
            stringBuilder.append(" yaw=");
            stringBuilder.append(interactItemPacket.getYaw());
            stringBuilder.append(" pitch=");
            stringBuilder.append(interactItemPacket.getPitch());

            Util.informPlayer(stringBuilder.toString());
        }
    }

}
