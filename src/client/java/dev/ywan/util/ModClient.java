package dev.ywan.util;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class ModClient implements ClientModInitializer {
	public static String MOD_ID = "util";
	public static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static MinecraftClient client = MinecraftClient.getInstance();

	@Override
	public void onInitializeClient() {
		LOGGER.info("UtilMod loaded!");
		(MixinInfo)
	}
}