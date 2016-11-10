
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
public class PreparadorTabelaRelatorioProdutos implements PreparadorTabela {

    @Override
    public JTable prepararTabela(Relatorio relatorio, String codigosConsulta) throws SQLException {

       
        List<AtributosRelatorioProdutos> lista = new ArrayList<>();
        AtributosRelatorioProdutos atributosRelatorio = new AtributosRelatorioProdutos();
        ResultSet rs = new DAORelatorios().executarConsultaRelatorio(relatorio, codigosConsulta);
        while (rs.next()) {
 
            atributosRelatorio.setProduto(rs.getString("PRODUTO"));
            atributosRelatorio.setUnidadeMedida(rs.getString("UM"));
            atributosRelatorio.setControladoPorLote(rs.getString("CONTROLADO_POR_LOTE"));
            atributosRelatorio.setPreco(""+rs.getDouble("PRECO"));
            atributosRelatorio.setQtdMinima(""+rs.getDouble("qtdMinima"));
            
            lista.add(atributosRelatorio);
            atributosRelatorio = new AtributosRelatorioProdutos();
            
        }

        JTable tabelaDados = new JTable();
        tabelaDados.setModel(new TableModelRelatorioProdutos(lista));
        
        return tabelaDados;
    }

}
