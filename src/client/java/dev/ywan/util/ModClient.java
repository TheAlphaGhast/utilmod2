package dev.ywan.util;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModClient implements ClientModInitializer {
	public static String MOD_ID = "boatrotate";
	public static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		LOGGER.info("BoatRotate loaded!");
	}
}