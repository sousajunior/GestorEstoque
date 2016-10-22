/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.geradorRelatorio;

import br.com.gestorestoque.controller.ControladorMovimentacao;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author DG
 */
public class GeradorRelatorioJasperAdapter implements GeradorRelatorioService {

    @Override
    public JPanel vizualizarRelatorio(String nomeRelatorio, String codigosConsulta) throws JRException, SQLException {
        JPanel vizualizadorRelarorio = new JPanel();

        JasperPrint jasperPrint;
        jasperPrint = JasperFillManager.
                fillReport("src/br/com/gestorestoque/relatorio/jasper/" + nomeRelatorio + ".jasper",
                        new HashMap(),
                        new JRResultSetDataSource(new ControladorMovimentacao().selecionarParaRelatorio(codigosConsulta)));
        JasperViewer jrViewer = new JasperViewer(jasperPrint, true);
        jrViewer.setSize(1200, 700);

        return vizualizadorRelarorio;
    }

}
