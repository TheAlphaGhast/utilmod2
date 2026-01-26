package dev.ywan.util;

import net.minecraft.text.Text;

public class Util {
    public static void toggleable(String[] parts) {
        if (parts.length == 2) {
            informPlayer("Toggling " + parts[1]);
            Config.toggle(parts[1]);
            informPlayer("New state: " + Config.getEnabled(parts[1]));
        } else if (parts.length == 3) {
            String state = parts[1].toLowerCase();
            boolean value;
            if ("0".equals(state) || "false".equals(state)) value = false;
            else if ("1".equals(state) || "true".equals(state)) value = true;
            else {
                informPlayer("Invalid syntax.");
                return;
            }

            if (value) {
                informPlayer("Enabling " + parts[0] + ".");
            } else {
                informPlayer("Disabling " + parts[0] + ".");
            }

            Config.setEnabled(parts[0], value);
        } else {
            informPlayer("Invalid syntax.");
        }
    }

    public static void informPlayer(String message) {
        if (ModClient.client.player != null) {
            ModClient.client.player.sendMessage(Text.of(message), false);
        } else {
            ModClient.LOGGER.warn("Player is null during ModClient.informPlayer");
        }
    }
}
