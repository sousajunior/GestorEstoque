
package br.com.gestorestoque.controller;

import br.com.gestorestoque.banco.CRUD;
import br.com.gestorestoque.model.UnidadeMedida;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DG
 */
public class ControladorUnidadeMedida implements Controlador<UnidadeMedida> {

    private final String nomeTabela, codigo;

    public ControladorUnidadeMedida() {
        this.nomeTabela = "unidademedida";
        this.codigo = "idunidadeMedida";
    }

    @Override
    public void inserir(UnidadeMedida unidadeMedida) throws SQLException {
        CRUD.insert(nomeTabela, "null,'" + unidadeMedida.getNome() + "','" + unidadeMedida.getAbreviacao() + "'");
    }

    @Override
    public void atualizarPorCodigo(UnidadeMedida unidadeMedida) throws SQLException {
        CRUD.update(nomeTabela,
                "nome = '" + unidadeMedida.getNome()
                + "',abreviacao = '" + unidadeMedida.getAbreviacao() + "'",
                codigo, unidadeMedida.getCodigo().toString());
    }

    @Override
    public List<UnidadeMedida> selecionarTodos() throws SQLException {
        List<UnidadeMedida> unidadesMedida = new ArrayList<>();

        ResultSet rs = CRUD.select(nomeTabela);

        while (rs.next()) {
            UnidadeMedida unidadeMedida = new UnidadeMedida(rs.getInt(codigo), rs.getString("nome"), rs.getString("abreviacao"));
            unidadesMedida.add(unidadeMedida);
        }

        return unidadesMedida;
    }

    @Override
    public UnidadeMedida selecionarPorCodigo(int id) throws SQLException {
        UnidadeMedida unidadeMedida = new UnidadeMedida();

        ResultSet rs = CRUD.select(nomeTabela, codigo, id);

        if (rs.next()) {
            unidadeMedida = new UnidadeMedida(rs.getInt(codigo), rs.getString("nome"), rs.getString("abreviacao"));

        }

        return unidadeMedida;

    }

    @Override
    public void deletar(UnidadeMedida unidadeMedida) throws SQLException {
        CRUD.delete(nomeTabela, codigo, unidadeMedida.getCodigo().toString());
    }

}
