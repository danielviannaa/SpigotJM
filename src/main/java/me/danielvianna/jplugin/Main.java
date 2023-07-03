package me.danielvianna.jplugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.danielvianna.jplugin.managers.Manager;
import me.danielvianna.jplugin.managers.mysql.MySQLConnectionManager;
import me.danielvianna.jplugin.managers.mysql.MySQLDatabaseCreator;
import me.danielvianna.jplugin.managers.utils.MySQLManager;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    // Plugin settings:
    private static Main instance;
    private ProtocolManager protocolManager;
    public PluginManager pm;


    // Database settings:

    private MySQLConnectionManager connectionManager;

    public String host = getConfig().getString("MySQL.Host");
    public String port = getConfig().getString("MySQL.Port");
    public String username = getConfig().getString("MySQL.Username");
    public String database = getConfig().getString("MySQL.Database_name");
    public String password = getConfig().getString("MySQL.Password");
    public String url = "jdbc:mysql://" + host + ":" + port + "/" + database;



    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Manager manager = new Manager();
        MySQLManager sqlManager = new MySQLManager();

        sqlManager.registerAllMySQL();
        manager.registerAll();

        getLogger().info("Plugin iniciado.");
    }

    @Override
    public void onLoad() {
        protocolManager = ProtocolLibrary.getProtocolManager();
    }

    @Override
    public void onDisable() {
        connectionManager.close();
    }
}