
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
public class PreparadorTabelaRelatorioSaldoEstoque implements PreparadorTabela {

    @Override
    public JTable prepararTabela(Relatorio relatorio, String codigosConsulta) throws SQLException {

       
        List<AtributosRelatorioSaldoEstoque> lista = new ArrayList<>();
        AtributosRelatorioSaldoEstoque atributosRelatorio = new AtributosRelatorioSaldoEstoque();
        ResultSet rs = new DAORelatorios().executarConsultaRelatorio(relatorio, codigosConsulta);
        while (rs.next()) {
 
            atributosRelatorio.setProduto(rs.getString("PRODUTO"));
            atributosRelatorio.setLote(rs.getString("LOTE"));
            atributosRelatorio.setFornecedor(rs.getString("FORNECEDOR"));
            atributosRelatorio.setSaldo(""+rs.getDouble("SALDO"));
            atributosRelatorio.setUnidadeMedida(rs.getString("UM"));
            atributosRelatorio.setArmazem(rs.getString("ARMAZEM"));
            atributosRelatorio.setControladoPorLote(rs.getString("CONTROLADO_POR_LOTE"));
            atributosRelatorio.setNotaFiscal(rs.getString("NOTA_FISCAL"));
            atributosRelatorio.setPreco(""+rs.getDouble("PRECO"));
            
            lista.add(atributosRelatorio);
            atributosRelatorio = new AtributosRelatorioSaldoEstoque();
            
        }

        JTable tabelaDados = new JTable();
        tabelaDados.setModel(new TableModelRelatorioSaldoEstoque(lista));
        
        return tabelaDados;
    }

}
