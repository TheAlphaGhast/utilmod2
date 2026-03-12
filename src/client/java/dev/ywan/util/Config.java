package dev.ywan.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class Config {
    private static final Map<String, Boolean> configMap = new HashMap<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final File CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir().resolve("utilmod.json").toFile();

    static {
        try {
            FileReader reader = new FileReader(CONFIG_PATH);
            //noinspection unchecked
            Map<String, Boolean> parsedConfig = GSON.fromJson(reader, Map.class);
            configMap.putAll(parsedConfig);
        } catch (FileNotFoundException e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

            // default values
            configMap.putIfAbsent("boatrotate", false);
            configMap.putIfAbsent("largenbt", false);
    }

    private Config() {
        throw new IllegalStateException("Config should not be instantiated");
    }

    public static void setEnabled(String name, boolean value) {
        configMap.put(name, value);
        saveConfig();
    }

    public static boolean getEnabled(String name) {
        // Ensure boatrotate is not being used on servers
        if ("boatrotate".equals(name) && ModClient.client.getCurrentServerEntry() != null) return false;
        return configMap.get(name);
    }

    public static void toggle(String name) {
        setEnabled(name, !getEnabled(name));
    }


    public static void saveConfig() {
        try (FileWriter writer = new FileWriter(CONFIG_PATH)) {
            GSON.toJson(configMap, writer);
        } catch (IOException e) {
            ModClient.LOGGER.error("Error while saving config", e);
        }
    }

    public static void printConfig() {
        for (Map.Entry<String, Boolean> entry : configMap.entrySet()) {
            if ("boatrotate".equals(entry.getKey()) && ModClient.client.getCurrentServerEntry() != null) {
                Util.informPlayer("boatrotate: false");
                continue;
            }
            Util.informPlayer(entry.getKey() + ": " + entry.getValue());
        }
    }
}
