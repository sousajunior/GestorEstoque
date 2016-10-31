
package br.com.gestorestoque.relatorio.excel;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DG
 */
public class TableModelRelatorioMovimentacaoEstoque extends AbstractTableModel {

    public final List<AtributosRelatorioMovimentacaoEstoque> registrosRelatorio;

    public TableModelRelatorioMovimentacaoEstoque(List<AtributosRelatorioMovimentacaoEstoque> registrosRelatorio) {
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
        return 7;
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
        
        AtributosRelatorioMovimentacaoEstoque atributosRelatorio = registrosRelatorio.get(rowIndex);

        
        if (columnIndex == 0) {
            
            return atributosRelatorio.getProduto();
        }

        if (columnIndex == 1) {
            return atributosRelatorio.getLote();
        }

        if (columnIndex == 2) {
            return atributosRelatorio.getQuantidade();
        }

        if (columnIndex == 3) {

            return atributosRelatorio.getData();
        }

        if (columnIndex == 4) {
            return atributosRelatorio.getNotaFiscal();
        }

        if (columnIndex == 5) {

            return atributosRelatorio.getTipoMovimentacao();
        }

        if (columnIndex == 6) {

            return atributosRelatorio.getArmazem();

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

            return "Lote";
        }

        if (column == 2) {
            return "Quantidade";
        }

        if (column == 3) {
            return "Data";

        }

        if (column == 4) {

            return "Nota Fiscal";
        }

        if (column == 5) {

            return "Tipo Movimentação";
        }
        if (column == 6) {

            return "Armazém";
        }

        return null;
    }
    
    
}
