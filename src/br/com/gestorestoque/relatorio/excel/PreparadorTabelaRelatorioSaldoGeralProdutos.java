
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
public class PreparadorTabelaRelatorioSaldoGeralProdutos implements PreparadorTabela {

    @Override
    public JTable prepararTabela(Relatorio relatorio, String codigosConsulta) throws SQLException {

       
        List<AtributosRelatorioSaldoGeralProdutos> lista = new ArrayList<>();
        AtributosRelatorioSaldoGeralProdutos atributosRelatorio = new AtributosRelatorioSaldoGeralProdutos();
        ResultSet rs = new DAORelatorios().executarConsultaRelatorio(relatorio, codigosConsulta);
        while (rs.next()) {
 
            atributosRelatorio.setProduto(rs.getString("PRODUTO"));
            atributosRelatorio.setSaldo(rs.getString("SALDO"));
            atributosRelatorio.setUnidadeMedida(rs.getString("UM"));
            atributosRelatorio.setArmazem(rs.getString("ARMAZEM"));
            atributosRelatorio.setControladoPorLote(rs.getString("CONTROLADO_POR_LOTE"));
            atributosRelatorio.setPreco(""+rs.getDouble("PRECO"));
            
            lista.add(atributosRelatorio);
            
            atributosRelatorio = new AtributosRelatorioSaldoGeralProdutos();
            
        }

        JTable tabelaDados = new JTable();
        tabelaDados.setModel(new TableModelRelatorioSaldoGeralProdutos(lista));
        
        return tabelaDados;
    }

}
