package dev.ywan.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.nio.file.Path;


public class Config {
    private static Map<String, Boolean> config = new HashMap<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir().resolve("utilmod.json");

    static {
        // default values
        setEnabled("boatrotate", false);
        setEnabled("largenbt", false);
    }

    public static void setEnabled(String name, boolean value) {
        config.put(name, value);
        saveConfig();
    }

    public static boolean getEnabled(String name) {
        return config.get(name);
    }

    public static void toggle(String name) {
        setEnabled(name, !getEnabled(name));
    }


    public static void saveConfig() {
        try (FileWriter writer = new FileWriter(CONFIG_PATH.toFile())) {
            GSON.toJson(config, writer);
        } catch (IOException e) {
            ModClient.LOGGER.error("Error while saving config", e);
        }
    }

    public static void printConfig() {
        for (Map.Entry<String, Boolean> entry : config.entrySet()) {
            Util.informPlayer(entry.getKey() + ": " + entry.getValue());
        }
    }
}
