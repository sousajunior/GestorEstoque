package br.com.gestorestoque.relatorio.excel;

import br.com.gestorestoque.DAO.DAORelatorios;
import br.com.gestorestoque.view.enumerado.Relatorio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author DG
 */
public class PreparadorTabelaRelatorioMovimentacaoEstoque implements PreparadorTabela {

    @Override
    public JTable prepararTabela(Relatorio relatorio, String codigosConsulta) throws SQLException {

        List<AtributosRelatorioMovimentacaoEstoque> lista = new ArrayList<>();
        AtributosRelatorioMovimentacaoEstoque atributosRelatorio = new AtributosRelatorioMovimentacaoEstoque();
        ResultSet rs = new DAORelatorios().executarConsultaRelatorio(relatorio, codigosConsulta);
        while (rs.next()) {

            atributosRelatorio.setProduto(rs.getString("PRODUTO"));
            atributosRelatorio.setLote(rs.getString("LOTE"));
            atributosRelatorio.setQuantidade(rs.getString("QUANTIDADE"));
            atributosRelatorio.setNotaFiscal(rs.getString("NOTA_FISCAL"));
            atributosRelatorio.setTipoMovimentacao(rs.getString("TIPO"));
            atributosRelatorio.setData(rs.getString("DATA"));
            atributosRelatorio.setArmazem(rs.getString("ARMAZEM"));

            lista.add(atributosRelatorio);
            atributosRelatorio = new AtributosRelatorioMovimentacaoEstoque();

        }

        JTable tabelaDados = new JTable();
        tabelaDados.setModel(new TableModelRelatorioMovimentacaoEstoque(lista));

        return tabelaDados;
    }

}
