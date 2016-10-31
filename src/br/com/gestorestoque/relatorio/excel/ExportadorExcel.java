package br.com.gestorestoque.relatorio.excel;

import br.com.gestorestoque.util.RelatorioUtil;
import br.com.gestorestoque.view.enumerado.Relatorio;
import jxl.write.Label;
import jxl.write.Number;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Formula;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 *
 * @author DG
 */
public class ExportadorExcel {

    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputArquivo;

    public ExportadorExcel(JFrame frame, JTable tabela, Relatorio relatorio, boolean mail) throws WriteException, IOException {

        if (!mail) {
            JFileChooser fc = new JFileChooser(new File(RelatorioUtil.retornarNomeRelatorio(relatorio) + ".xls"));
            fc.showSaveDialog(frame);

            this.inputArquivo = fc.getSelectedFile().toString();
        }else{
            
            this.inputArquivo = "C:/Users/Public/"+RelatorioUtil.retornarNomeRelatorio(relatorio)+".xls";
        }
        
        insere(relatorio, tabela);

        System.out.println("Terminado");
    }

    public void insere(Relatorio relatorio, JTable tabela) throws IOException, WriteException {
        // Cria um novo arquivo
        File arquivo = new File(inputArquivo);
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("pt", "BR"));

        WritableWorkbook workbook = Workbook.createWorkbook(arquivo, wbSettings);
// Define um nome para a planilha
        workbook.createSheet(RelatorioUtil.retornarNomeRelatorio(relatorio), 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        criaLabel(excelSheet, tabela);
        defineConteudo(excelSheet, tabela);

        workbook.write();
        workbook.close();
    }

// Método responsável pela definição das labels
    private void criaLabel(WritableSheet sheet, JTable tabela) throws WriteException {
// Cria o tipo de fonte como TIMES e tamanho
        WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);

// Define o formato da célula
        times = new WritableCellFormat(times10pt);

// Efetua a quebra automática das células
        times.setWrap(true);

// Cria a fonte em negrito com underlines
        WritableFont times10ptBoldUnderline = new WritableFont(
                WritableFont.ARIAL, 10, WritableFont.BOLD, false);
//UnderlineStyle.SINGLE);
        timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);

// Efetua a quebra automática das células
        timesBoldUnderline.setWrap(true);

        CellView cv = new CellView();
        cv.setFormat(times);//Bom pessoal, é isso ai, qualquer dúvida é só avisar.
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);

        for (int c = 0; c < tabela.getModel().getColumnCount(); c++) {
            addCaption(sheet, c, 0, tabela.getModel().getColumnName(c));
            System.out.println(tabela.getModel().getColumnName(c));

        }
    }

    private void defineConteudo(WritableSheet sheet, JTable tabela) throws WriteException, RowsExceededException {

        for (int c = 0; c < tabela.getModel().getColumnCount(); c++) {
            for (int l = 1; l < tabela.getModel().getColumnCount(); l++) {
                Label label;
                label = new Label(c, l, "" + tabela.getModel().getValueAt(l, c));
                System.out.println(tabela.getModel().getValueAt(l, c));
                sheet.addCell(label);

            }
        }

    }

    // Adiciona cabecalho
    private void addCaption(WritableSheet planilha, int coluna, int linha, String s)
            throws RowsExceededException, WriteException {
        Label label;
        label = new Label(coluna, linha, s, timesBoldUnderline);
        planilha.addCell(label);
    }

}
