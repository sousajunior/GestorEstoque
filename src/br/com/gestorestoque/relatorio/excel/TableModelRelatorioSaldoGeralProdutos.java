
package br.com.gestorestoque.relatorio.excel;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DG
 */
public class TableModelRelatorioSaldoGeralProdutos extends AbstractTableModel {

    public final List<AtributosRelatorioSaldoGeralProdutos> registrosRelatorio;

    public TableModelRelatorioSaldoGeralProdutos(List<AtributosRelatorioSaldoGeralProdutos> registrosRelatorio) {
        this.registrosRelatorio = registrosRelatorio;

    }
    
    

    /**
     * Retorna a quantidade de linhas existentes na TableModel
     *
     * @return
     */
    @Override
    public int getRowCount() {
        return registrosRelatorio.size();
    }

    /**
     * Retorna a quantidade de colunas existentes na TableModel
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return 6;
    }

    /**
     * Retorna o valor de uma célula da TableModel, sendo necessário informar
     * por parâmetro a linha e a coluna específicas a qual se deseja obter o
     * valor.
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (rowIndex < 0 | rowIndex >= registrosRelatorio.size()) {
            return null;

        }
        
        AtributosRelatorioSaldoGeralProdutos atributosRelatorio = registrosRelatorio.get(rowIndex);

        
        if (columnIndex == 0) {
            
            return atributosRelatorio.getProduto();
        }

        if (columnIndex == 1) {
            return atributosRelatorio.getSaldo();
        }

        if (columnIndex == 2) {
            return atributosRelatorio.getUnidadeMedida();
        }

        if (columnIndex == 3) {

            return atributosRelatorio.getArmazem();
        }

        if (columnIndex == 4) {
            return atributosRelatorio.getControladoPorLote();
        }

        if (columnIndex == 5) {

            return atributosRelatorio.getPreco();
        }

        return null;
    }

    /**
     * Retorna o nome da coluna da TableModel, sendo necessário informar o
     * número da coluna desejada. OBS. Sempre irá iniciar em zero.
     *
     * @param column
     * @return
     */
    @Override
    public String getColumnName(int column) {

        if (column == 0) {
            return "Produto";
        }

        if (column == 1) {

            return "Saldo";
        }

        if (column == 2) {
            return "U.M";
        }

        if (column == 3) {
            return "Armazém";

        }

        if (column == 4) {

            return "Controlado por lote";
        }

        if (column == 5) {

            return "Preço";
        }

        return null;
    }
    
    
}
