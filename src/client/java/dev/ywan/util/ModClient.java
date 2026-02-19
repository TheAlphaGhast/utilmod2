package dev.ywan.util;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModClient implements ClientModInitializer {
    public static final String MOD_ID = "util";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final MinecraftClient client = MinecraftClient.getInstance();

    @Override
    public void onInitializeClient() {
        //ClientTickEvents.START_CLIENT_TICK.register(ModClient::tick);
        LOGGER.info("UtilMod loaded!");
    }

    /*private static void tick(MinecraftClient tickClient) {
    }*/
}