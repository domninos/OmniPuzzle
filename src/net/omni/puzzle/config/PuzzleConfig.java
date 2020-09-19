package net.omni.puzzle.config;

import net.omni.puzzle.PuzzlePlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PuzzleConfig {
    private final File file;
    private FileConfiguration config;

    public PuzzleConfig(PuzzlePlugin plugin, String fileName) {
        if (!fileName.endsWith(".yml"))
            fileName = fileName + ".yml";

        this.file = new File(plugin.getDataFolder(), fileName);

        if (!(file.exists())) {
            plugin.saveResource(fileName, false);
            System.out.println("Created " + fileName);
        }

        reload();
    }

    public void set(String path, Object object) {
        setNoSave(path, object);
        save();
    }

    public void setNoSave(String path, Object object) {
        config.set(path, object);
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getString(String path) {
        return config.getString(path);
    }

    public int getInt(String path) {
        return config.getInt(path);
    }
}
