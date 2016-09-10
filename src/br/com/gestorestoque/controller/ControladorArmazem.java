/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.controller;

import br.com.gestorestoque.banco.CRUD;
import br.com.gestorestoque.model.Armazem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DG
 */
public class ControladorArmazem {


    /**
     * Executa um método que seleciona todos os armazéns cadastrados na base de dados.
     * Método select da classe CRUD.
     * @return List< Armazem >
     * @throws SQLException 
     */
    public  static List<Armazem> selecionarTodosArmazens() throws SQLException {

        List<Armazem> armazens = new ArrayList<>();

        ResultSet rs = CRUD.select("armazem");

        while (rs.next()) {
            Armazem armazem = new Armazem(rs.getInt("codigoArmazem"), rs.getString("descricao"));
            armazens.add(armazem);
        }

        return armazens;

    }
    
    /**
     * Executa o método insert da classe CRUD, passando tabela armazem e os valores necessários para, inserir um armazém na base de dados.
     * É necessário passar por parâmetro um armazem.
     * @param armazem
     * @throws SQLException 
     */
    public static void inserirArmazem(Armazem armazem) throws SQLException{
        
        CRUD.insert("armazem", "null,'"+armazem.getDescricao()+"'");
        
    }
    
    /**
     * Executa o método update da classe CRUD, passando a tabela de armazem, e os valores necessários para atualizar um armazém que já está cadastrado na base de dados.
     * é necessário informar via parâmetro a coluna a ser alterada, o valor para esta coluna, e a coluna e valor para a clausula where.
     * @param valores
     * @param valorWhere
     * @throws SQLException 
     */
    public static void updateArmazemPorCodigo(String valores,String valorWhere) throws SQLException{
        
        CRUD.update("armazem", "descricao = '"+valores+"'","codigoArmazem",valorWhere);
        
    }
    
    /**
     * Executa o metodo delete da classe CRUD, passando por parâmetro a tabela, a coluna e valor para a clausula where.
     * É necessário passar um armazém por parâmetro, ao invocar este método.
     * @param armazem
     * @throws SQLException 
     */
    public static void deletarArmazem(Armazem armazem) throws SQLException{
        
        CRUD.delete("armazem","descricao" ,"'"+armazem.getDescricao()+"'");
        
    }
    

}
