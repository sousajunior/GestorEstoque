/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view;

import br.com.gestorestoque.geradorRelatorio.GeradorRelatorioJasperAdapter;
import br.com.gestorestoque.geradorRelatorio.GeradorRelatorioService;
import br.com.gestorestoque.view.enumerado.TipoRelatorio;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRRuntimeException;

/**
 *
 * @author DG
 */
public class FRMRelatorio extends javax.swing.JDialog {

    /**
     * Creates new form FRMRelatorio
     *
     * @param parent
     * @param modal
     * @param jrRS
     * @param nomeRelatorio
     */
    public FRMRelatorio(java.awt.Dialog parent, boolean modal, String codigos, String nomeRelatorio, TipoRelatorio tipoRelatorio) {
        super(parent, modal);
        initComponents();

        vizualizarRelatorio(codigos, nomeRelatorio, tipoRelatorio);
    }

    private void vizualizarRelatorio(String codigosConsulta, String nomeRelatorio, TipoRelatorio tipoRelatorio) {
        try {

            GeradorRelatorioService geradorRelatorioService;
            
            if (tipoRelatorio == TipoRelatorio.PDF) {
                
                geradorRelatorioService = new GeradorRelatorioJasperAdapter();
                
                //Adicionando o relatorio no Jdialog  
                this.getContentPane().add(geradorRelatorioService.vizualizarRelatorio(nomeRelatorio, codigosConsulta));
                //Deixar True para exibir a tela no sistema  
            }
        } catch (JRRuntimeException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar/salvar o relatório! \nErro:" + e.getMessage(), "Erro", 0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível gerar o relatório! \nErro:" + e.getMessage(), "Erro", 0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório");

        setSize(new java.awt.Dimension(1200, 700));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FRMRelatorio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRMRelatorio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRMRelatorio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRMRelatorio.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FRMRelatorio dialog = new FRMRelatorio(new javax.swing.JDialog(), true, "", "", TipoRelatorio.PDF);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
