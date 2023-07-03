package me.danielvianna.jplugin.managers.utils;


import me.danielvianna.jplugin.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class Messages {

    FileConfiguration config = Main.getInstance().getConfig();

    public String noPermission() {
        return config.getString("Mensagens.SemPermissao").replace("&", "§");
    }

    public String hasCooldown() {
        return config.getString("Mensagens.AguardeOCooldown").replace("&", "§");
    }

    public String inCooldown() {
        return config.getString("Mensagens.ColocadoNoCooldown").replace("&", "§");
    }

    public String flyOn() {
        return config.getString("Mensagens.FlyLigado").replace("&", "§");
    }

    public String flyOff() {
        return config.getString("Mensagens.FlyDesligado").replace("&", "§");
    }

    public String noArgs() {
        return config.getString("Mensagens.SemArgumentos").replace("&", "§");
    }

    public String speedChanged() {
        return config.getString("Mensagens.VelocidadeAlterada").replace("&", "§");
    }

    public String nonexistentPlayer() {
        return config.getString("Mensagens.PlayerInexistente").replace("&", "§");
    }

}
