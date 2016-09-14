
package br.com.gestorestoque.banco;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Matheus
 */
public class CRUD {
    
    
  
    /**
     * Recebe uma query e e troca todas as ocorrências de 'null' por null
     * @param query
     * @return query String 
     */
    
    public static String verificaNull(String query)
    {
        return query.replaceAll("'null'", "null");
    }
      /**
     * Monta um script de inserção, deve-se passar por parâmetro a tabela, as colunas e os valores a serem inseridos.
     * @param tabela
     * @param colunas
     * @param valores
     * @throws SQLException 
     */
    public static void insert(String tabela, String colunas, String valores) throws SQLException, MySQLIntegrityConstraintViolationException{
        valores = verificaNull(valores);
        String sql = "INSERT into "+ tabela +" ("+colunas+") values("+valores+")";
        executeSQL(sql);        
    }
    
    /**
     * Monta um script de inserção, deve-se passar por parâmetro a tabela e os valores a serem inseridos.
     * @param tabela
     * @param valores
     * @throws SQLException 
     */
    public static void insert(String tabela,String valores) throws SQLException , MySQLIntegrityConstraintViolationException{       
        valores = verificaNull(valores);
        String sql = "INSERT into "+ tabela + " values ("+valores+")";
        executeSQL(sql);        
    }
    
    /**
     * Monta um script de atualização, deve-se passar por parâmetro a tabela, uma coluna para especificar o novo valor, o novo valor, a coluna para a condição where e o valor para a condição where.
     * 
     * @param tabela
     * @param colunasEValores 
     * @param colunaWhere
     * @param valores
     * @param valorWhere
     * @throws SQLException 
     */ 
    public static void update(String tabela, String colunasEValores, String colunaWhere, String valorWhere) throws SQLException{       
        colunasEValores = verificaNull(colunasEValores);
        String sql = "UPDATE "+ tabela + " SET "+colunasEValores+" WHERE "+colunaWhere +" = "+valorWhere ;
        executeSQL(sql);        
    }
    
    
   /**
    * Monta um script de atualização, deve-se passar por parâmetro a tabela, duas colunas e os dois novos valores para essas colunas, a coluna para a condição where e o valor para a condição where.
    * @param tabela String
    * @param coluna1 String
    * @param coluna2 String
    * @param colunaWhere String
    * @param valores String
    * @param valores2 String
    * @param valorWhere String
    * @throws SQLException 
    */
    public static void update(String tabela,String coluna1,String coluna2, String colunaWhere,String valores,String valores2, String valorWhere) throws SQLException{       
        valores = verificaNull(valores);
        valores2 = verificaNull(valores2);
        String sql = "UPDATE "+ tabela + " SET "+coluna1 +" = "+valores+", "+coluna2+" = "+valores2+" WHERE "+colunaWhere +" = "+valorWhere ;
        executeSQL(sql);        
    }
    
    
    /**
     * Monta um script de exclusão de registros, deve-se passar a tabela, a coluna para a condição where e o valor para a condição where.
     * @param tabela String
     * @param coluna String
     * @param valores String
     * @throws SQLException 
     */
    public static void delete(String tabela,String coluna,String valores) throws SQLException{       
        String sql = "DELETE FROM "+ tabela + " where "+coluna+" = "+valores;
        executeSQL(sql);        
    }
 
    /**
     * Monta um script para selecionar todos os registros da tabela passada por parâmetro.
     * @param tabela
     * @return
     * @throws SQLException 
     */
    public static ResultSet   select(String tabela)throws SQLException{      
       String sql = "SELECT * FROM " +tabela; 
       return executeSelect(sql);
    }
    
    
    /**
     * Monta um script para selecionar todos os registros da tabela passada por parâmetro, de acordo com os filtros passados n parametro where.
     * @param tabela
     * @return
     * @throws SQLException 
     */
    public static ResultSet  select(String tabela, String where)throws SQLException{      
       String sql = "SELECT * FROM " +tabela+" "+where; 
       return executeSelect(sql);
    }
    
    /**
     * Monta um script para selecionar os registros da tabela passada por parâmetro, de acordo com o id, que também é passado por parâmetro.
     * @param tabela
     * @param coluna 
     * @param id 
     * @return
     * @throws SQLException 
     */
    public static ResultSet  select(String tabela,String coluna,int id)throws SQLException{      
       String sql = "SELECT * FROM " +tabela+ " where "+coluna+" = "+id; 
       return executeSelect(sql);
    }
    
    
    /**
     * Monta um script para selecionar os registros da tabela passada por parâmetro, de acordo com o id, que também é passado por parâmetro. 
     * @param query
     * @return
     * @throws SQLException 
     */
    public static ResultSet  queryRelatorio(String query)throws SQLException{      
        if(!query.equalsIgnoreCase("")){
            return executeSelect(query);
        }
       
       return null;
    }
    
    /**
     * Executa scripts como insert, update e delete, deve-se passar por parâmetro o script pronto para a execução.
     * @param script
     * @throws SQLException 
     */
    private static void executeSQL(String script) throws SQLException{
        System.out.println(script);
       Connection con = Conexao.AbrirConexao();
       Statement st = con.createStatement();
       st.execute(script);
       
       Conexao.FecharConexao();
    }
    
    /**
     * Executa comandos como por exemplo (select), deve-se passar por parâmetro o script pronto para a execução.
     * @param script
     * @return ResultSet
     * @throws SQLException 
     */
    private static  ResultSet executeSelect(String script) throws SQLException{
       System.out.println(script);
       Connection con = Conexao.AbrirConexao();
       Statement st = con.createStatement();
       ResultSet rs = st.executeQuery(script);
       //Conexao.FecharConexao();
       return rs;
  
    }
    
}
