package me.danielvianna.jplugin.commands.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class SpeedCommandTC implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> completions = new ArrayList<>();
        completions.add("0.1");
        completions.add("0.2");
        completions.add("0.3");
        completions.add("0.4");
        completions.add("0.5");
        completions.add("0.6");
        completions.add("0.7");
        completions.add("0.8");
        completions.add("0.9");
        completions.add("1.0");
        completions.add("1.1");
        completions.add("1.2");
        completions.add("1.3");
        completions.add("1.4");
        completions.add("1.5");
        completions.add("1.6");
        completions.add("1.7");
        completions.add("1.8");
        completions.add("1.9");
        completions.add("2.0");
        completions.add("2.1");
        completions.add("2.2");
        completions.add("2.3");
        completions.add("2.4");
        completions.add("2.5");
        completions.add("2.6");
        completions.add("2.7");
        completions.add("2.8");
        completions.add("2.9");
        completions.add("3.0");
        completions.add("3.1");
        completions.add("3.2");
        completions.add("3.3");
        completions.add("3.4");
        completions.add("3.5");
        completions.add("3.6");
        completions.add("3.7");
        completions.add("3.8");
        completions.add("3.9");
        completions.add("4.0");
        completions.add("4.1");
        completions.add("4.2");
        completions.add("4.3");
        completions.add("4.4");
        completions.add("4.5");
        completions.add("4.6");
        completions.add("4.7");
        completions.add("4.8");
        completions.add("4.9");
        completions.add("5.0");
        completions.add("5.1");
        completions.add("5.2");
        completions.add("5.3");
        completions.add("5.4");
        completions.add("5.5");
        completions.add("5.6");
        completions.add("5.7");
        completions.add("5.8");
        completions.add("5.9");
        completions.add("6.0");
        completions.add("6.1");
        completions.add("6.2");
        completions.add("6.3");
        completions.add("6.4");
        completions.add("6.5");
        completions.add("6.6");
        completions.add("6.7");
        completions.add("6.8");
        completions.add("6.9");
        completions.add("7.0");
        completions.add("7.1");
        completions.add("7.2");
        completions.add("7.3");
        completions.add("7.4");
        completions.add("7.5");
        completions.add("7.6");
        completions.add("7.7");
        completions.add("7.8");
        completions.add("7.9");
        completions.add("8.0");
        completions.add("8.1");
        completions.add("8.2");
        completions.add("8.3");
        completions.add("8.4");
        completions.add("8.5");
        completions.add("8.6");
        completions.add("8.7");
        completions.add("8.8");
        completions.add("8.9");
        completions.add("9.0");
        completions.add("9.1");
        completions.add("9.2");
        completions.add("9.3");
        completions.add("9.4");
        completions.add("9.5");
        completions.add("9.6");
        completions.add("9.7");
        completions.add("9.8");
        completions.add("9.9");
        completions.add("10.0");

        return completions;
    }

}
