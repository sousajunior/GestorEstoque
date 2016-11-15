package br.com.gestorestoque.geradorRelatorio;

import br.com.gestorestoque.relatorio.excel.ExportadorExcel;
import br.com.gestorestoque.relatorio.excel.PreparadorTabelaRelatorioMovimentacaoEstoque;
import br.com.gestorestoque.relatorio.excel.PreparadorTabelaRelatorioProdutos;
import br.com.gestorestoque.relatorio.excel.PreparadorTabelaRelatorioSaldoEstoque;
import br.com.gestorestoque.relatorio.excel.PreparadorTabelaRelatorioSaldoGeralProdutos;
import br.com.gestorestoque.relatorio.mail.SendMailAttach;
import br.com.gestorestoque.util.FRMUtil;
import br.com.gestorestoque.view.enumerado.Relatorio;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import jxl.write.WriteException;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author DG
 */
public class GeradorRelatorioExcelAdapter implements GeradorRelatorioService {

    JFrame jFrame = new JFrame();
    JToolBar barraFerramentas = new JToolBar("Opções");
    JButton btExportarExcel = new JButton();
    JButton btEnviarEmail = new JButton();
    JScrollPane scrollPane;
    JTable tabelaDados;

    @Override
    public JFrame vizualizarRelatorio(Relatorio relatorio, String codigosConsulta) throws JRException, SQLException {

        return prepararFrame(relatorio, codigosConsulta);
    }

    private JFrame prepararFrame(Relatorio relatorio, String codigosConsulta) throws SQLException {

        new FRMUtil().setarIcone(jFrame, null);
        btExportarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/exportar_excel -32.png")));

        btExportarExcel.addActionListener((ActionEvent e) -> {
            try {
                btnExportarExcelClicado(relatorio);
                
            } catch (WriteException | IOException ex) {
                Logger.getLogger(GeradorRelatorioExcelAdapter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        btEnviarEmail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/send_mail-32.png")));

        btEnviarEmail.addActionListener((e) -> {
            btnEnviarEmailClicado(relatorio);
        }
        );

        barraFerramentas.add(btExportarExcel);
        barraFerramentas.add(btEnviarEmail);

        tabelaDados = prepararTable(relatorio, codigosConsulta);
        scrollPane = new JScrollPane(tabelaDados, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jFrame.setLayout(new BorderLayout());
        jFrame.getContentPane().add(barraFerramentas, java.awt.BorderLayout.NORTH);
        jFrame.getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);

        return jFrame;
    }

    private JTable prepararTable(Relatorio relatorio, String codigosConsulta) throws SQLException {

        switch (relatorio) {
            case RelatorioGeralMovimentacoes:
                return new PreparadorTabelaRelatorioMovimentacaoEstoque().prepararTabela(relatorio, codigosConsulta);
            case RelatorioSaldoEstoque:
                return new PreparadorTabelaRelatorioSaldoEstoque().prepararTabela(relatorio, codigosConsulta);
            case RelatorioSaldoGeralProdutos:
                return new PreparadorTabelaRelatorioSaldoGeralProdutos().prepararTabela(relatorio, codigosConsulta);
            case RelatorioProdutos:
                return new PreparadorTabelaRelatorioProdutos().prepararTabela(relatorio, codigosConsulta);

        }
        return null;
    }

    public void btnExportarExcelClicado(Relatorio relatorio) throws WriteException, IOException {

        new ExportadorExcel(jFrame, tabelaDados, relatorio, false);

    }

    public void btnEnviarEmailClicado(Relatorio relatorio) {

        try {

            jFrame.setCursor(new Cursor(Cursor.WAIT_CURSOR));
            new SendMailAttach(jFrame, 
                               relatorio, 
                               tabelaDados, 
                               JOptionPane.showInputDialog("Informe o(s) endereço(s) de e-mail: "));
            
            JOptionPane.showMessageDialog(jFrame, 
                                          "Relatório enviado como anexo com sucesso!", 
                                          "E-mail enviado!", 
                                          JOptionPane.INFORMATION_MESSAGE);
            
            jFrame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        } catch (WriteException | IOException ex) {
            Logger.getLogger(GeradorRelatorioExcelAdapter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
