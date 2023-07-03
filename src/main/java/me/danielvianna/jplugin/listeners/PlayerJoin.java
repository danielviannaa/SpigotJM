package me.danielvianna.jplugin.listeners;

import me.danielvianna.jplugin.Main;
import me.danielvianna.jplugin.managers.mysql.MySQLDatabaseCreator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.*;
import java.util.logging.Level;

public class PlayerJoin implements Listener {

    public String host = Main.getInstance().host;
    public String port = Main.getInstance().port;
    public String username = Main.getInstance().username;
    public String database = Main.getInstance().database;
    public String password = Main.getInstance().password;
    public String url = Main.getInstance().url;
    public String tableName = "Players";

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        String playerName = player.getName();
        if (!player.hasPlayedBefore()) {
            return;
        }

        String[] columnNames = {
                "nick"
        };
        String[] columnTypes = {
                "VARCHAR(255)"
        };

        MySQLDatabaseCreator.createDatabase(url, username, password, database);

        MySQLDatabaseCreator.createTable(url, username, password, database, tableName, columnNames, columnTypes);

        if (isPlayerNameExists(playerName)) {
            Main.getInstance().getLogger().info("O jogador já existe no banco de dados: " + playerName);
            return;
        }

        Object[] values = {
                playerName
        };
        MySQLDatabaseCreator.insertData(url, username, password, database, tableName, values);
    }

    private boolean isPlayerNameExists(String playerName) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM " + tableName + " WHERE nick = '" + playerName + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet.next();
        } catch (SQLException e) {
            Main.getInstance().getLogger().log(Level.SEVERE, "Erro ao verificar a existência do jogador no banco de dados: " + e.getMessage());
            return false;
        }
    }

}
