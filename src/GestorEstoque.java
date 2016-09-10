
import br.com.gestorestoque.banco.CRUD;
import br.com.gestorestoque.view.SplashScreen;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Matheus
 */
public class GestorEstoque {

    public static void main(String args[]) throws SQLException {
        new SplashScreen();
       /* ResultSet rs = CRUD.select("unidademedida");
        while (rs.next()) {
            System.out.println(rs.getString("idunidademedida"));
            System.out.println(rs.getString("nome"));
            System.out.println(rs.getString("abreviacao"));
        }*/
    }

}
