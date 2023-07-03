package me.danielvianna.jplugin.managers.mysql;

import me.danielvianna.jplugin.Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

public class MySQLConnectionManager {

    private Connection connection;

    public void connect(String host, String port, String database, String username, String password) {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

        try {
            connection = DriverManager.getConnection(url, username, password);
            Main.getInstance().getLogger().info("Conexão com o MySQL estabelecida!");
        } catch (SQLException e) {
            Main.getInstance().getLogger().log(Level.SEVERE, "Erro ao conectar-se ao MySQL: " + e.getMessage());
            Main.getInstance().getServer().getPluginManager().disablePlugin(Main.getInstance());
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                Main.getInstance().getLogger().info("Conexão com o MySQL fechada!");
            }
        } catch (SQLException e) {
            Main.getInstance().getLogger().log(Level.SEVERE, "Erro ao fechar a conexão com o MySQL: " + e.getMessage());
        }
    }

}
