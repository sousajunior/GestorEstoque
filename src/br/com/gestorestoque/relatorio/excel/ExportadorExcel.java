package br.com.gestorestoque.relatorio.excel;

import br.com.gestorestoque.util.RelatorioUtil;
import br.com.gestorestoque.view.enumerado.Relatorio;
import com.sun.security.auth.module.NTSystem;
import jxl.write.Label;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
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
public final class ExportadorExcel {

    private String diretorioArquivo;

    /**
     *
     * @param frame
     * @param tabela
     * @param relatorio
     * @param mail
     * @throws WriteException
     * @throws IOException
     */
    public ExportadorExcel(JFrame frame, JTable tabela, Relatorio relatorio, boolean mail) throws WriteException, IOException {

        exportarExcel(frame, tabela, relatorio, mail);
    }

    /**
     * Exporta dados de JTable, para um arquivo excel.
     *
     * @param frame
     * @param tabela
     * @param relatorio
     * @param mail
     * @throws WriteException
     * @throws IOException
     */
    public void exportarExcel(JFrame frame, JTable tabela, Relatorio relatorio, boolean mail) throws WriteException, IOException {

        if (!mail) {

            //Pegar informaçõs do sistema operacional, para salvar na area de trabalho do usuário logado no momento.
            NTSystem infoSystem = new NTSystem();
            //Setar a area de trabalho do usuário logado no sistema operacional, como diretório inicial,
            //ao abrir o JFileChooser.
            JFileChooser chooser = new JFileChooser("C:\\Users\\" + infoSystem.getName() + "\\Desktop");
            //Adicionar filtro ao JFileChooser, para vrificar apenas a existencia de arquivos com extensão .xls
            //não deixando salvar o arquivo como nenhum outro a nao ser 'Planilhas do excel'
            chooser.setFileFilter(new FileNameExtensionFilter("Planilhas do excel", "xls"));
            //Não deixar excolher a opção 'All Files'
            chooser.setAcceptAllFileFilterUsed(false);
            //Mudar o título da janela
            chooser.setDialogTitle("Salvar");
            //Setar o campo de texto o JFileChooser com o diretório/arquivo pertinente (nome do relatório)
            File arquivoVerificado = new File("C:\\Users\\" + infoSystem.getName() + "\\Desktop\\" + RelatorioUtil.retornarNomeRelatorio(relatorio));
            arquivoVerificado = new File(criarNomeArquivoValido(arquivoVerificado));
            chooser.setSelectedFile(arquivoVerificado);
            //Verificar se existem arquivos com o mesmo nome e corrigir o que foi setado no JFileChooser
            //chooser.setSelectedFile(new File(criarNomeArquivoValido(chooser, RelatorioUtil.retornarNomeRelatorio(relatorio))));

            //Aguarda o click do botão de salvar.
            if (chooser.showSaveDialog(frame) != JFileChooser.APPROVE_OPTION) {
                return;
            }

            
            //Ao salvar, valida novamente o diretório.
            this.diretorioArquivo = criarNomeArquivoValido(arquivoVerificado);

        } else {

            this.diretorioArquivo = "C:/Users/Public/" + RelatorioUtil.retornarNomeRelatorio(relatorio) + ".xls";
        }

        popularPlanilhas(relatorio, tabela);

        JOptionPane.showMessageDialog(frame, "Relatório exportado com sucesso!", "Relatório exportado", JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Cria um nome válido para o arquivo no diretório atual.
     *
     * @param file
     * @return diretório+arquivo String
     *
     */
    public String criarNomeArquivoValido(File file) {

        if (!file.getAbsolutePath().endsWith(".xls")) {
            file = new File(file.getAbsolutePath() + ".xls");
        }

        boolean exists = file.exists();
        if (!exists) {

            return file.getAbsolutePath();

        } else {

            int contador = 1;
            String caminhoArquivo = file.getAbsolutePath();
            caminhoArquivo = caminhoArquivo.substring(0, caminhoArquivo.length() - 4);
            File file2;

            while (exists) {
                file2 = new File(caminhoArquivo + "(" + contador + ").xls");

                if (file2.exists()) {
                    contador++;
                } else {
                    exists = false;
                    return file2.getAbsolutePath();

                }
            }

        }

        return "";
    }

    /**
     * Preenche planilhas, com dados do JTable passado por parâmetro.
     *
     * @param relatorio
     * @param tabela
     * @throws IOException
     * @throws WriteException
     */
    private void popularPlanilhas(Relatorio relatorio, JTable tabela) throws IOException, WriteException {

        // Cria um novo arquivo
        File arquivo = new File(diretorioArquivo);
        WorkbookSettings wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("pt", "BR"));

        WritableWorkbook workbook = Workbook.createWorkbook(arquivo, wbSettings);
        // Define um nome para a planilha
        workbook.createSheet(RelatorioUtil.retornarNomeRelatorio(relatorio), 0);
        WritableSheet excelSheet = workbook.getSheet(0);
        definirTitulos(excelSheet, tabela);
        definirConteudo(excelSheet, tabela);

        workbook.write();
        workbook.close();
    }

    /**
     * Define os nomes das colunas no excel, de acordo com os nomes de coluna no
     * JTable.
     *
     * @param sheet
     * @param tabela
     * @throws WriteException
     */
    private void definirTitulos(WritableSheet sheet, JTable tabela) throws WriteException {

        //Define os títulos das colunas no excel, com os nomes de coluna no JTable
        for (int c = 0; c < tabela.getModel().getColumnCount(); c++) {
            adicionarTitulo(sheet, c, 0, tabela.getModel().getColumnName(c));

        }
    }

    /**
     * Define o conteúdo das planilhas, de acordo com o conteúdo do JTable.
     *
     * @param sheet
     * @param tabela
     * @throws WriteException
     * @throws RowsExceededException
     */
    private void definirConteudo(WritableSheet sheet,
            JTable tabela) throws WriteException, RowsExceededException {

        for (int c = 0; c < tabela.getModel().getColumnCount(); c++) {
            for (int l = 1; l < tabela.getModel().getColumnCount(); l++) {
                Label label;
                label = new Label(c, l, "" + tabela.getModel().getValueAt(l, c));
                sheet.addCell(label);

            }
        }

    }

    /**
     * Adiciona cabeçalho nas planilhas.
     *
     * @param planilha
     * @param coluna
     * @param linha
     * @param conteudo
     * @throws RowsExceededException
     * @throws WriteException
     */
    private void adicionarTitulo(WritableSheet planilha,
            int coluna,
            int linha,
            String conteudo) throws RowsExceededException, WriteException {

        WritableCellFormat timesBoldUnderline;
        WritableCellFormat times;

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
        cv.setFormat(times);
        cv.setFormat(timesBoldUnderline);
        cv.setAutosize(true);

        Label label;
        label = new Label(coluna, linha, conteudo, timesBoldUnderline);
        planilha.addCell(label);
    }

}
