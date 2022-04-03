package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConexao() throws SQLException {
        try {
            //Definição do driver de conexão com o banco de dados MySQL
            Class.forName("com.mysql.jdbc.Driver");
            //definição dos parâmetros de conexão
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/TESTE", "root", "root1234");
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
    
    public static void main(String[] args){
        try{
            getConexao();
            System.out.println("Conexão realizada com sucesso.");
        }catch (SQLException e){
            System.out.println("Conexão não realizada, verificar log.");
            e.printStackTrace();
        }
    }
}
