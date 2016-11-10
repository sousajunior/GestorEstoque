
package br.com.gestorestoque.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Matheus
 */
public class Conexao {

    static final String URL = "jdbc:mysql://localhost/estoquedb";
    static final String USER = "root";
    static final String PASSWORD = "";
    static Connection con;
    public static String status = "Sem conexão";

    public void Conexao() {

    }

    public static Connection AbrirConexao() throws SQLException {
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        con = DriverManager.getConnection(URL, USER, PASSWORD);
        DriverManager.setLoginTimeout(1);
        return con;
        
    }

    public static String getStatus() {
        if (con != null) {
            status = "Conectado";
        } else {
            status = "Sem conexão";
        }
        return status;
    }

    public static void FecharConexao() throws SQLException {
        con.close();
    }

    public static Connection ReiniciarConexao() throws SQLException {
        FecharConexao();
        return Conexao.AbrirConexao();
    }

    public static boolean testarConexao() throws SQLException {

        Conexao.AbrirConexao();

        if (Conexao.getStatus().equalsIgnoreCase("Sem conexão")) {
            Conexao.FecharConexao();
            return false;
        }

        Conexao.FecharConexao();
        return true;
    }
}
