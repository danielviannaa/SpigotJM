package me.danielvianna.jplugin.managers.utils;

import me.danielvianna.jplugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class CustomConfiguration {

    private final String name;
    private final File file;
    private final FileConfiguration config;

    public CustomConfiguration(String name, File file, FileConfiguration config) throws IOException {
        this.name = name;
        this.file = new File(Main.getInstance().getDataFolder(), name.endsWith(".yml") ? name : name + ".yml");
        this.config = YamlConfiguration.loadConfiguration(file);

        if(!file.exists()) {
            try {
                Main.getInstance().saveResource(name.endsWith(".yml") ? name : name + ".yml", false);
            } catch (IllegalArgumentException e) {
                try {
                    file.createNewFile();
                }  catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            Main.getInstance().getLogger().log(Level.SEVERE, "Ocorreu um erro ao salvar o arquivo " + name);
        }
    }
}
