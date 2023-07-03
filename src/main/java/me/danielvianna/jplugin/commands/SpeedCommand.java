package me.danielvianna.jplugin.commands;

import me.danielvianna.jplugin.managers.utils.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeedCommand implements CommandExecutor {

    private Messages messages;

    public SpeedCommand() {
        messages = new Messages();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(messages.noPermission());
            return true;
        }

        Player player = (Player) sender;

        if (player.hasPermission("sjm.speed")) {
            if (args.length == 0) {
                player.sendMessage(messages.noArgs());
                return true;
            }

            double speedArgs;

            try {
                speedArgs = Double.parseDouble(args[0]);
            } catch (NumberFormatException e) {
                speedArgs = -1; // Valor inválido
            }

            if (speedArgs < 0.1 || speedArgs > 10.0) {
                player.sendMessage(messages.noArgs());
                return true;
            }

            if (player.isFlying()) {
                player.setFlySpeed((float) (speedArgs / 10));
                player.sendMessage(messages.speedChanged());
            } else {
                player.setWalkSpeed((float) (speedArgs / 10));
                player.sendMessage(messages.speedChanged());
                return true;
            }

        }

        player.sendMessage(messages.noPermission());

        return false;
    }
}
