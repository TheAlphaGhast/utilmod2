package dev.ywan.util.mixin;

import dev.ywan.util.Config;
import dev.ywan.util.ModClient;
import dev.ywan.util.Util;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {
    @Inject(method = "sendMessage", at = @At("HEAD"), cancellable = true)
    public void sendMessage(String chatText, boolean addToHistory, CallbackInfo ci) {
        if (chatText.startsWith("%util")) {
            try {
                if (chatText.length() == 5) {
                    Util.informPlayer("UtilMod is loaded.");
                    Config.printConfig();
                    return;
                }
                String raw = chatText.substring(5);
                String[] parts = raw.split(" ");
                if (parts.length > 1) {
                    String op = parts[1];

                    ModClient.LOGGER.info(op);

                    switch (op) {
                        case "boatrotate":
                            Util.toggleable(parts);
                            break;
                        default:
                            Util.informPlayer("Invalid module.");
                    }
                } else {
                    Util.informPlayer("Invalid syntax.");
                }
            } finally {
                ci.cancel();
                ModClient.client.setScreen(null);
                ModClient.client.inGameHud.getChatHud().addToMessageHistory(chatText);
            }
        }
    }
}
