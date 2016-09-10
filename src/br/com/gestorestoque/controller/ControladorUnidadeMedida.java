/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller;

import br.com.gestorestoque.banco.CRUD;
import br.com.gestorestoque.model.UnidadeMedida;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DG
 */
public class ControladorUnidadeMedida {


    
    
    /**
     * Executa um método que seleciona todos as unidades de medida cadastrados na base de dados.
     * Método select da classe CRUD.
     * @return List< UnidadeMedida >
     * @throws SQLException 
     */
    public  static List<UnidadeMedida> selecionarTodasUnidadesMedida() throws SQLException {

        List<UnidadeMedida> unidadesMedida = new ArrayList<>();

        ResultSet rs = CRUD.select("unidademedida");

        while (rs.next()) {
            UnidadeMedida unidadeMedida = new UnidadeMedida(rs.getInt("idunidadeMedida"), rs.getString("nome"),rs.getString("abreviacao"));
            unidadesMedida.add(unidadeMedida);
        }

        return unidadesMedida;

    }
    
    /**
     * Executa um método que seleciona todos as unidades de medida cadastrados na base de dados.
     * Método select da classe CRUD.
     * @return UnidadeMedida 
     * @throws SQLException 
     */
    public  static UnidadeMedida selecionarUnidadesMedida(int id) throws SQLException {

        UnidadeMedida unidadeMedida = new UnidadeMedida();

        ResultSet rs = CRUD.select("unidademedida","idunidadeMedida",id);

        if (rs.next()) {
             unidadeMedida = new UnidadeMedida(rs.getInt("idunidadeMedida"), rs.getString("nome"),rs.getString("abreviacao"));
            
        }

        return unidadeMedida;

    }
    
    
    /**
     * Executa o método insert da classe CRUD, passando tabela unidademedida e os valores necessários para, inserir uma unidade de medida na base de dados.
     * É necessário passar por parâmetro uma unidade de medida.
     * @param UnidadeMedida
     * @throws SQLException 
     */
    public static void inserirUnidadeMedida(UnidadeMedida unidadeMedida) throws SQLException ,MySQLIntegrityConstraintViolationException{
        
        CRUD.insert("unidademedida", "null,'"+unidadeMedida.getNome()+"','"+unidadeMedida.getAbreviacao()+"'");
        
    }
    
    /**
     * Executa o método update da classe CRUD, passando a tabela de unidademedida, e os valores necessários para atualizar uma unidade de medida que já está cadastrado na base de dados.
     * é necessário informar via parâmetro a coluna a ser alterada, o valor para esta coluna, e a coluna e valor para a clausula where.
     * @param valor
     * @param valorWhere
     * @throws SQLException 
     */
    public static void updateUnidadeMedidaPorCodigo(String valor,String valorWhere) throws SQLException{
        
        CRUD.update("unidademedida", "nome = '"+valor+"'","idunidadeMedida",valorWhere);
        
    }
    
    /**
     * Executa o método update da classe CRUD, passando a tabela de unidademedida, e os valores necessários para atualizar uma unidade de medida que já está cadastrado na base de dados.
     * é necessário informar via parâmetro a coluna a ser alterada, o valor para esta coluna, e a coluna e valor para a clausula where.
     * @param valores
     * @param valorWhere
     * @throws SQLException 
     */
    public static void updateUnidadeMedidaPorCodigo(String valor1,String valor2,String valorWhere) throws SQLException{
        
        CRUD.update("unidademedida", "nome","abreviacao","idunidadeMedida","'"+valor1+"'","'"+valor2+"'",valorWhere);
        
    }
    
    /**
     * Executa o metodo delete da classe CRUD, passando por parâmetro a tabela, a coluna e valor para a clausula where.
     * É necessário passar uma unidade de medida por parâmetro, ao invocar este método.
     * @param unidadeMedida  UnidadeMedida
     * @throws SQLException 
     */
    public static void deletarUnidadeMedida(UnidadeMedida unidadeMedida) throws SQLException{
        
        CRUD.delete("unidademedida","idunidadeMedida" ,unidadeMedida.getCodigo().toString());
        
    }
    

}
