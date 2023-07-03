package me.danielvianna.jplugin.commands;

import me.danielvianna.jplugin.Main;
import me.danielvianna.jplugin.managers.Manager;
import me.danielvianna.jplugin.managers.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class CooldownCommand implements CommandExecutor {
    private final Messages messages;
    private final Manager manager;

    public CooldownCommand() {
        messages = new Messages();
        manager = new Manager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(messages.noPermission());
            return true;
        }

        Player player = (Player) sender;

        if (manager.hasCooldown(player)) {
            long secondsLeft = ((manager.cooldowns.get(player.getUniqueId()) / 1000) + manager.cooldownTime) - (System.currentTimeMillis() / 1000);
            if (secondsLeft > 0) {
                player.sendMessage(messages.hasCooldown().replace("%rt%", String.valueOf(secondsLeft)));
                return true;
            }
        }


        // Executar a ação do comando aqui:

        // Definir cooldown
        manager.setCooldown(player);

        // Agendar remoção do cooldown após o tempo:

        new BukkitRunnable() {
            @Override
            public void run() {
                manager.cooldowns.remove(player.getUniqueId());
                player.sendMessage(messages.inCooldown());
            }
        }.runTaskLater(Main.getInstance(), manager.cooldownTime * 20);

        return true;

    }
}
