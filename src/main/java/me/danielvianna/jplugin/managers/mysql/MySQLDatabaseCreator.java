package me.danielvianna.jplugin.managers.mysql;

import me.danielvianna.jplugin.Main;

import java.sql.*;
import java.util.logging.Level;

public class MySQLDatabaseCreator {

    public static boolean isDatabaseExists(String url, String username, String password, String databaseName) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            String sql = "SHOW DATABASES LIKE '" + databaseName + "'";
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet.next();
        } catch (SQLException e) {
            Main.getInstance().getLogger().log(Level.SEVERE, "Erro ao verificar a existência do banco de dados: " + e.getMessage());
            return false;
        }
    }

    public static void createDatabase(String url, String username, String password, String databaseName) {
        if (!isDatabaseExists(url, username, password, databaseName)) {
            try (Connection connection = DriverManager.getConnection(url, username, password)) {
                Statement statement = connection.createStatement();
                String sql = "CREATE DATABASE " + databaseName;
                statement.executeUpdate(sql);
                Main.getInstance().getLogger().info("Banco de dados criado com sucesso");
            } catch (SQLException e) {
                Main.getInstance().getLogger().log(Level.SEVERE, "Erro ao criar o banco de dados: " + e.getMessage());
            }
        } else {
            Main.getInstance().getLogger().info("O banco de dados já existe");
        }
    }

    public static void createTable(String url, String username, String password, String databaseName, String tableName, String[] columnNames, String[] columnTypes) {
        if (columnNames.length != columnTypes.length) {
            throw new IllegalArgumentException("O número de nomes de coluna e tipos de coluna deve ser o mesmo");
        }

        try (Connection connection = DriverManager.getConnection(url, username, password)) {

            Statement statement = connection.createStatement();

            // Verificar se a tabela já existe
            String checkIfExistsQuery = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '" + tableName + "'";
            ResultSet resultSet = statement.executeQuery(checkIfExistsQuery);
            if (resultSet.next()) {
                Main.getInstance().getLogger().info("A tabela '" + tableName + "' já existe");
                return;
            }

            // Criar a tabela
            StringBuilder createTableQuery = new StringBuilder("CREATE TABLE " + tableName + " (");
            for (int i = 0; i < columnNames.length; i++) {
                createTableQuery.append(columnNames[i]).append(" ").append(columnTypes[i]);
                if (i < columnNames.length - 1) {
                    createTableQuery.append(",");
                }
            }
            createTableQuery.append(")");
            statement.executeUpdate(createTableQuery.toString());

            Main.getInstance().getLogger().info("Tabela criada com sucesso");
        } catch (SQLException e) {
            Main.getInstance().getLogger().log(Level.SEVERE, "Erro ao criar a tabela: " + e.getMessage());
        }
    }

    public static void insertData(String url, String username, String password, String databaseName, String tableName, Object[] values) {

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            // Verificar se a tabela existe
            String checkIfExistsQuery = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '" + tableName + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(checkIfExistsQuery);
            if (!resultSet.next()) {
                // Se a tabela não existe, criar a tabela com uma coluna
                String createTableQuery = "CREATE TABLE " + tableName + " VALUES (?)";
                statement.executeUpdate(createTableQuery);
                Main.getInstance().getLogger().info("Tabela criada: " + tableName);
            }

            // Inserir os dados na tabela
            String insertQuery = "INSERT INTO " + tableName + " VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setObject(1, values[0]);
            preparedStatement.executeUpdate();

            Main.getInstance().getLogger().info("Dados inseridos na tabela: " + tableName);
        } catch (SQLException e) {
            Main.getInstance().getLogger().log(Level.SEVERE, "Erro ao inserir dados na tabela: " + e.getMessage());
        }
    }

}
