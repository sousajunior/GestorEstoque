/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view;

import br.com.gestorestoque.controller.ControladorUnidadeMedida;
import br.com.gestorestoque.model.UnidadeMedida;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Matheus
 */
public class FRMCadastroUnidadeMedida extends javax.swing.JDialog {

    TableModel modeloTabelaUnidadeMedida;
    UnidadeMedida unidadeMedidaAlterarExcluir;

    /**
     * Creates new form FRMCadastroProduto
     */
    public FRMCadastroUnidadeMedida(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        prepararComponentes();

    }

    /**
     * Executa o método ControladorUnidadeMedida.selecionarTodasUnidadesMedida()
     * da classe ControladorUnidadeMedida. Este método retorna uma lista com
     * todos as unidades de medida cadastradas na base de dados.
     *
     * @return
     */
    private List<UnidadeMedida> getUnidadesMedida() {

        List<UnidadeMedida> unidadesMedida = new ArrayList<>();
        try {
            unidadesMedida = ControladorUnidadeMedida.selecionarTodasUnidadesMedida();
            return unidadesMedida;
        } catch (SQLException ex) {
            Logger.getLogger(FRMCadastroUnidadeMedida.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Classe interna que define o TableModel da tabela de Unidade de Medida.
     */
    private class UnidadeMedidaTableModel extends AbstractTableModel {

        public final List<UnidadeMedida> unidadesMedidas;

        public UnidadeMedidaTableModel(List<UnidadeMedida> unidadesMedidas) {
            this.unidadesMedidas = unidadesMedidas;
        }

        /**
         * Retorna a quantidade de linhas existentes na TableModel
         *
         * @return
         */
        @Override
        public int getRowCount() {
            return unidadesMedidas.size();
        }

        /**
         * Retorna a quantidade de colunas existentes na TableModel
         *
         * @return
         */
        @Override
        public int getColumnCount() {
            return 2;
        }

        /**
         * Retorna o valor de uma célula da TableModel, sendo necessário
         * informar por parâmetro a linha e a coluna específicas a qual se
         * deseja obter o valor.
         *
         * @param rowIndex
         * @param columnIndex
         * @return
         */
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {

            if (rowIndex < 0 | rowIndex >= unidadesMedidas.size()) {
                return null;

            }

            UnidadeMedida unidadeMedida = unidadesMedidas.get(rowIndex);

            if (columnIndex == 0) {
                return unidadeMedida.getNome();
            } else if (columnIndex == 1) {
                return unidadeMedida.getAbreviacao();
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

                return "Nome";
            } else if (column == 1) {
                return "Abreviação";

            }

            return null;
        }

    }

    /**
     * Prepara os componentes da tela para o uso da mesma. O preparo dos
     * componentes envolve adição de listeners
     */
    public void prepararComponentes() {

        //Preparar Jtable de unidades de medida
        atualizaTabelaUnidadeMedida();

        jtUnidadeMedida.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        jtUnidadeMedida.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabelaUnidadeMedidaClicada();
            }
        });

        jtUnidadeMedida.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int posicaoAtual = jtUnidadeMedida.getSelectedRow();

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (posicaoAtual >= 1) {

                        jtUnidadeMedida.setRowSelectionInterval(posicaoAtual, posicaoAtual);
                        tabelaUnidadeMedidaClicada();
                    }
                    if (posicaoAtual == 0) {

                        jtUnidadeMedida.setRowSelectionInterval(posicaoAtual, posicaoAtual);
                        tabelaUnidadeMedidaClicada();
                    }

                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (posicaoAtual < jtUnidadeMedida.getRowCount()) {

                        jtUnidadeMedida.setRowSelectionInterval(posicaoAtual, posicaoAtual);
                        tabelaUnidadeMedidaClicada();

                    }

                }
            }
        });

        //Preparar Botoes 
        //Salvar
        jbtnSalvar.addActionListener(
                (e) -> {
                    btnSalvarUnidadeMedidaClicado();
                }
        );

        //Limpar
        jbtnLimpar.addActionListener(
                (e) -> {
                    btnLimparClicado();
                }
        );

        //excluir
        jbtnExcluir.addActionListener(
                (e) -> {
                    btnExcluirClicado();
                }
        );
        
        //jdialogUnidadeMedida
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(verificarComponentesPreenchidos()){
                    if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Há itens que não foram salvos!\n Deseja mesmo sair?", "Fechar", JOptionPane.YES_NO_OPTION, 3)){
                        dispose();
                    }
                }else{
                    dispose();
                }
            }
            
            
            
        });

    }
    
    private boolean verificarComponentesPreenchidos(){
        
        
        
        if(!jtfNome.getText().equalsIgnoreCase("")){
            return true;
        }
        
        if(!jtfAbreviacao.getText().equalsIgnoreCase("")){
            return true;
        }
        
        
        return false;
    }

    /**
     * Atualiza as linhas da tabela de unidades de medida com todos as unidades
     * de medida cadastrados na base.
     */
    private void atualizaTabelaUnidadeMedida() {
        modeloTabelaUnidadeMedida = new UnidadeMedidaTableModel(getUnidadesMedida());
        jtUnidadeMedida.setModel(modeloTabelaUnidadeMedida);
    }

    /**
     * Método invocado quando alguma linha da tabela de unidade de medida é
     * selecionada.
     */
    private void tabelaUnidadeMedidaClicada() {
        this.unidadeMedidaAlterarExcluir = new UnidadeMedida();
        this.unidadeMedidaAlterarExcluir = procurarUnidadeMedidaNaLista(modeloTabelaUnidadeMedida.getValueAt(jtUnidadeMedida.getSelectedRow(), 0).toString(), modeloTabelaUnidadeMedida.getValueAt(jtUnidadeMedida.getSelectedRow(), 1).toString());
        jtfNome.setText(modeloTabelaUnidadeMedida.getValueAt(jtUnidadeMedida.getSelectedRow(), 0).toString());
        jtfAbreviacao.setText(modeloTabelaUnidadeMedida.getValueAt(jtUnidadeMedida.getSelectedRow(), 1).toString());
    }

    /**
     * Método procura uma unidade de medida na base de dados. É necessário
     * informar a descrição, e abreviação da unidade de medida por parâmetro.
     *
     * @param descricao String
     * @param abreviacao String
     * @return
     */
    private UnidadeMedida procurarUnidadeMedidaNaLista(String descricao, String abreviacao) {

        for (UnidadeMedida unidadeMedida : getUnidadesMedida()) {
            if (unidadeMedida.getNome().equals(descricao) && unidadeMedida.getAbreviacao().equals(abreviacao)) {
                unidadeMedidaAlterarExcluir = unidadeMedida;
                return unidadeMedida;
            }
        }

        return null;
    }

    /**
     * Método invocado quando o botão Limpar é clicado.
     */
    private void btnLimparClicado() {
        this.jtfNome.setText("");
        this.jtfAbreviacao.setText("");
        unidadeMedidaAlterarExcluir = null;

    }

    /**
     * Método invocado quando o botão Excluir é clicado.
     */
    private void btnExcluirClicado() {

        try {
            if (!this.jtfNome.getText().equalsIgnoreCase("") && !this.jtfAbreviacao.getText().equalsIgnoreCase("")) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir este item?", "Confirmação de exclusão", JOptionPane.YES_NO_OPTION, 3)) {
                    ControladorUnidadeMedida.deletarUnidadeMedida(unidadeMedidaAlterarExcluir);
                    atualizaTabelaUnidadeMedida();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma unidade de medida para realizar a exclusão!", "Atenção!", 2);
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Houve um problema ao excluir esta unidade de medida!\nJá existem produtos associados a esta unidade de medida,\npara realizar a exclusão, \n atualize o cadastro dos produtos que estão vinculados a esta unidade de medida .", "Atenção!", 2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Houve um problema ao excluir esta unidade de medida!\nErro: " + ex, "Erro!", 0);
        }
        btnLimparClicado();
    }

    /**
     * Método invocado quando o botão Salvar é clicado.
     */
    private void btnSalvarUnidadeMedidaClicado() {

        try {
            if (!this.jtfNome.getText().equalsIgnoreCase("") && !this.jtfAbreviacao.getText().equalsIgnoreCase("")) {
                if (unidadeMedidaAlterarExcluir == null) {

                    UnidadeMedida unidadeMedida = new UnidadeMedida(this.jtfNome.getText(), this.jtfAbreviacao.getText());

                    ControladorUnidadeMedida.inserirUnidadeMedida(unidadeMedida);

                } else {

                    ControladorUnidadeMedida.updateUnidadeMedidaPorCodigo(jtfNome.getText(), this.jtfAbreviacao.getText(), unidadeMedidaAlterarExcluir.getCodigo().toString());
                }
                atualizaTabelaUnidadeMedida();
            } else {
                JOptionPane.showMessageDialog(null, "A unidade de medida deve ter uma descrição, e uma abreviação!", "Atenção!", 2);
            }

        } catch (MySQLIntegrityConstraintViolationException ex) {

            JOptionPane.showMessageDialog(null, "A unidade de medida já está cadastrada!", "Atenção!", 2);

        } catch (SQLException ex) {
            Logger.getLogger(FRMCadastroUnidadeMedida.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnLimparClicado();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        bgControleLote = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtUnidadeMedida = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlNome = new javax.swing.JLabel();
        jlQtdMinima = new javax.swing.JLabel();
        jbtnSalvar = new javax.swing.JButton();
        jbtnLimpar = new javax.swing.JButton();
        jbtnExcluir = new javax.swing.JButton();
        jtfNome = new javax.swing.JTextField();
        jtfAbreviacao = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cadastro/Edição de Unidades de Medidas");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(null);
        setMinimumSize(new java.awt.Dimension(584, 443));

        jScrollPane1.setName(""); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 200));

        jtUnidadeMedida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtUnidadeMedida.setToolTipText("Tabela de unidades de medida");
        jScrollPane1.setViewportView(jtUnidadeMedida);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jPanel1.setMinimumSize(new java.awt.Dimension(200, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel1.setRequestFocusEnabled(false);
        java.awt.GridBagLayout jPanel1Layout1 = new java.awt.GridBagLayout();
        jPanel1Layout1.columnWidths = new int[] {0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0};
        jPanel1Layout1.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel1.setLayout(jPanel1Layout1);

        jlNome.setText("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jlNome, gridBagConstraints);

        jlQtdMinima.setText("Abreviação:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(jlQtdMinima, gridBagConstraints);

        jbtnSalvar.setText("Salvar");
        jbtnSalvar.setToolTipText("Salvar/Atualizar unidade de medida");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 3;
        jPanel1.add(jbtnSalvar, gridBagConstraints);

        jbtnLimpar.setText("Limpar");
        jbtnLimpar.setToolTipText("Nova unidade de medida");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        jPanel1.add(jbtnLimpar, gridBagConstraints);

        jbtnExcluir.setText("Excluir");
        jbtnExcluir.setToolTipText("Excluir unidade de medida");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 10;
        jPanel1.add(jbtnExcluir, gridBagConstraints);

        jtfNome.setToolTipText("Informe a descrição da unidade de medida");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jtfNome, gridBagConstraints);

        jtfAbreviacao.setToolTipText("Informe a abreviação da unidade de medida");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jtfAbreviacao, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 220;
        gridBagConstraints.ipady = -100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        jPanel3.add(jPanel1, gridBagConstraints);

        getContentPane().add(jPanel3, java.awt.BorderLayout.NORTH);

        pack();
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
            java.util.logging.Logger.getLogger(FRMCadastroUnidadeMedida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRMCadastroUnidadeMedida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRMCadastroUnidadeMedida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRMCadastroUnidadeMedida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FRMCadastroUnidadeMedida dialog = new FRMCadastroUnidadeMedida(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup bgControleLote;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnExcluir;
    private javax.swing.JButton jbtnLimpar;
    private javax.swing.JButton jbtnSalvar;
    private javax.swing.JLabel jlNome;
    private javax.swing.JLabel jlQtdMinima;
    private javax.swing.JTable jtUnidadeMedida;
    private javax.swing.JTextField jtfAbreviacao;
    private javax.swing.JTextField jtfNome;
    // End of variables declaration//GEN-END:variables
}
