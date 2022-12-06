package br.com.mesttra.carro.connectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() {
        try {

            return DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/carros", "postgres", "161003");

        } catch (SQLException e) {
            System.err.println("Erro ao estabelecer conexão");
            return null;
        }
    }

}
