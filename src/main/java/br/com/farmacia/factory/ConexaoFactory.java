package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    private static final String USUARIO = "root";
    private static final String SENHA = "12345";
    private static final String URL = "jdbc:mysql://localhost:3306/sistema?useTimezone=true&serverTimezone=UTC";

    public static Connection conectar() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

        if (conexao != null) {
            System.out.println("Conectado com sucesso");
        } else {
            System.out.println("Erro ao conectar");
        }
        return conexao;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Connection connection = ConexaoFactory.conectar();

        } catch (SQLException e) {
            System.out.println("Conexao falhou!!!" + e);
        }
    }
}
