package me.danielvianna.jplugin.managers.utils;

import me.danielvianna.jplugin.Main;
import me.danielvianna.jplugin.managers.mysql.MySQLConnectionManager;

public class MySQLManager {

    public String host = Main.getInstance().host;
    public String port = Main.getInstance().port;
    public String username = Main.getInstance().username;
    public String database = Main.getInstance().database;
    public String password = Main.getInstance().password;
    public String url = Main.getInstance().url;

    public void registerAllMySQL() {
        MySQLConnectionManager connectionManager = new MySQLConnectionManager();
        connectionManager = new MySQLConnectionManager();
        connectionManager.connect(host, port, database, username, password);

    }

}
