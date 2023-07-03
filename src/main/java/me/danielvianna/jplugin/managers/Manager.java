package me.danielvianna.jplugin.managers;

import me.danielvianna.jplugin.Main;
import me.danielvianna.jplugin.commands.CooldownCommand;
import me.danielvianna.jplugin.commands.FlyCommand;
import me.danielvianna.jplugin.commands.SpeedCommand;
import me.danielvianna.jplugin.commands.tabcompleters.SpeedCommandTC;
import me.danielvianna.jplugin.listeners.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Manager {
    public Map<UUID, Long> cooldowns = new HashMap<>();
    public Long cooldownTime = Main.getInstance().getConfig().getLong("CooldownTime");

    public void registerAll() {
        // Commands:
        registerCommand("speed", new SpeedCommand());
        registerCommand("cooldown", new CooldownCommand());
        registerCommand("fly", new FlyCommand());

        // TabCompleters:
        registerTabCompleter("speed", new SpeedCommandTC());

        // Listeners:
        registerEvents(new PlayerJoin());

        // Others:
        saveConfiguration();
    }

    private void registerCommand(String commandName, CommandExecutor className) {
        Objects.requireNonNull(Main.getInstance().getCommand(commandName)).setExecutor(className);
    }

    public void registerTabCompleter(String commandName, TabCompleter className) {
        Objects.requireNonNull(Main.getInstance().getCommand(commandName)).setTabCompleter(className);
    }

    private void registerEvents(Listener className) {
        Bukkit.getPluginManager().registerEvents(className, Main.getInstance());
    }

    private void saveConfiguration() {
        Main.getInstance().getConfig().options().copyDefaults(false);
        Main.getInstance().saveDefaultConfig();
        Main.getInstance().reloadConfig();
    }

    public boolean hasCooldown(Player player) {
        return cooldowns.containsKey(player.getUniqueId()) && System.currentTimeMillis() - cooldowns.get(player.getUniqueId()) < cooldownTime * 1000;
    }

    public void setCooldown(Player player) {
        cooldowns.put(player.getUniqueId(), System.currentTimeMillis());
    }

}
