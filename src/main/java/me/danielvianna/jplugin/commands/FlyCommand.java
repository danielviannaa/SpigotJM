package me.danielvianna.jplugin.commands;

import me.danielvianna.jplugin.managers.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    private Messages messages;

    public FlyCommand() {
        messages = new Messages();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(messages.noPermission());
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("sjm.fly")) {
            player.sendMessage(messages.noPermission());
            return true;
        }

        if (args.length == 0) {
            giveFly(player);
            return true;
        }

        Player targetPlayer = Bukkit.getPlayerExact(args[0]);

        if (targetPlayer == null || !targetPlayer.isOnline() && targetPlayer != player) {
            player.sendMessage(messages.nonexistentPlayer());
            return true;
        }

        if (args[0].equalsIgnoreCase(targetPlayer.getName())) {
            giveFly(targetPlayer);
        }

        return true;
    }

    private void giveFly(Player player) {
        if (player.getAllowFlight()) {
            player.setAllowFlight(true);
            player.setFlying(true);
            player.sendMessage(messages.flyOn());
        } else {
            player.setAllowFlight(false);
            player.setFlying(false);
            player.sendMessage(messages.flyOff());
        }
    }
}
