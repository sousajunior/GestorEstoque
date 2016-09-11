/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view;

import br.com.gestorestoque.controller.ControladorFornecedor;
import br.com.gestorestoque.model.Fornecedor;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
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
public class FRMCadastroFornecedor extends javax.swing.JDialog {

    TableModel modeloTabelaFornecedor;
    Fornecedor fornecedorAlterarExcluir;

    /**
     * Creates new form FRMCadastroProduto
     */
    public FRMCadastroFornecedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        prepararComponentes();

    }

    /**
     * Executa o método ControladorFornecedor.selecionarTodosFornecedores() da
     * classe ControladorFornecedor. Este método retorna uma lista com todos
     * fornecedores cadastrados na base de dados.
     *
     * @return
     */
    private List<Fornecedor> getFornecedores() {

        List<Fornecedor> fornecedor = new ArrayList<>();
        try {
            fornecedor = ControladorFornecedor.selecionarTodosFornecedores();
            return fornecedor;
        } catch (SQLException ex) {
            Logger.getLogger(FRMCadastroFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Classe interna que define o TableModel da tabela de Fornecedores.
     */
    private class FornecedorTableModel extends AbstractTableModel {

        public final List<Fornecedor> fornecedores;

        public FornecedorTableModel(List<Fornecedor> fornecedores) {
            this.fornecedores = fornecedores;
        }

        /**
         * Retorna a quantidade de linhas existentes na TableModel
         *
         * @return
         */
        @Override
        public int getRowCount() {
            return fornecedores.size();
        }

        /**
         * Retorna a quantidade de colunas existentes na TableModel
         *
         * @return
         */
        @Override
        public int getColumnCount() {
            return 4;
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

            if (rowIndex < 0 | rowIndex >= fornecedores.size()) {
                return null;

            }

            Fornecedor fornecedor = fornecedores.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return fornecedor.getIdFornecedor();
                case 1:
                    return fornecedor.getNome();
                case 2:
                    return fornecedor.getCpf();
                case 3:
                    return fornecedor.getCnpj();
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
            switch (column) {
                case 0:
                    return "ID";
                case 1:
                    return "Nome";
                case 2:
                    return "CPF";
                case 3:
                    return "CNPJ";
            }

            return null;
        }

    }

    /**
     * Prepara os componentes da tela para o uso da mesma. O preparo dos
     * componentes envolve adição de listeners
     */
    public void prepararComponentes() {

        //Preparar Jtable fornecedores
        atualizaFornecedor();

        jtFornecedor.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        jtFornecedor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabelaFornecedorClicada();
            }
        });

        jtFornecedor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int posicaoAtual = jtFornecedor.getSelectedRow();

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (posicaoAtual >= 1) {

                        jtFornecedor.setRowSelectionInterval(posicaoAtual, posicaoAtual);
                        tabelaFornecedorClicada();
                    }
                    if (posicaoAtual == 0) {

                        jtFornecedor.setRowSelectionInterval(posicaoAtual, posicaoAtual);
                        tabelaFornecedorClicada();
                    }

                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (posicaoAtual < jtFornecedor.getRowCount()) {

                        jtFornecedor.setRowSelectionInterval(posicaoAtual, posicaoAtual);
                        tabelaFornecedorClicada();

                    }

                }
            }
        });

        //Preparar Botoes 
        //Salvar
        jbtnSalvar.addActionListener(
                (e) -> {
                    btnSalvarFornecedor();
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
        jfCPF.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
                if (jfCPF.getText().equalsIgnoreCase("   .   .   -  ")) {
                    jfCPF.setValue(null);
                }
            }
        });

        jfCNPJ.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
                if (jfCNPJ.getText().equalsIgnoreCase("  .   .   /    -  ")) {
                    jfCNPJ.setValue(null);
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (verificarComponentesPreenchidos()) {
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Há itens que não foram salvos!\n Deseja mesmo sair?", "Fechar", JOptionPane.YES_NO_OPTION, 3)) {
                        dispose();
                    }

                } else {
                    dispose();
                }
            }
        });
    }

    public boolean verificarComponentesPreenchidos() {
        if (!this.jtfNome.getText().equalsIgnoreCase("")) {
            return true;
        }
        if (!this.jfCPF.getText().equalsIgnoreCase("   .   .   -  ") || (!this.jfCNPJ.getText().equalsIgnoreCase("  .   .   /    -  "))) {
            return true;
        }
        return false;
    }

    /**
     * Atualiza as linhas da tabela de fornecedores com todos os fornecedores
     * cadastrados na base.
     */
    private void atualizaFornecedor() {
        modeloTabelaFornecedor = new FornecedorTableModel(getFornecedores());
        jtFornecedor.setModel(modeloTabelaFornecedor);
    }

    /**
     * Método invocado quando alguma linha da tabela de fornecedor é
     * selecionada.
     */
    private void tabelaFornecedorClicada() {
        this.fornecedorAlterarExcluir = new Fornecedor();
        this.fornecedorAlterarExcluir = procurarFornecedorNaLista(Integer.parseInt(modeloTabelaFornecedor.getValueAt(jtFornecedor.getSelectedRow(), 0).toString()));
        jtfNome.setText(fornecedorAlterarExcluir.getNome());
        jfCPF.setText(fornecedorAlterarExcluir.getCpf());
        jfCNPJ.setText(fornecedorAlterarExcluir.getCnpj());
        try {
            jfCPF.commitEdit();
            jfCNPJ.commitEdit();
        } catch (ParseException ex) {
            Logger.getLogger(FRMCadastroFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método procura um fornecedor na base de dados. É necessário informar o id
     * por parâmetro.
     *
     * @param id int
     * @return
     */
    private Fornecedor procurarFornecedorNaLista(int id) {

        for (Fornecedor fornecedor : getFornecedores()) {
            if (fornecedor.getIdFornecedor() == id) {
                return fornecedor;
            }
        }

        return null;
    }

    /**
     * Método invocado quando o botão Limpar é clicado.
     */
    private void btnLimparClicado() {
        this.jtfNome.setText("");
        this.jfCPF.setText("");
        this.jfCPF.setValue(null);
        this.jfCNPJ.setText("");
        this.jfCNPJ.setValue(null);
        fornecedorAlterarExcluir = null;

    }

    /**
     * Método invocado quando o botão Excluir é clicado.
     */
    private void btnExcluirClicado() {

        try {
            if (!this.jtfNome.getText().equalsIgnoreCase("") && (!this.jfCPF.getText().equalsIgnoreCase("   .   .   -  ") || (!this.jfCNPJ.getText().equalsIgnoreCase("  .   .   /    -  ")))) {

                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir este item?", "Confirmação de exclusão", JOptionPane.YES_NO_OPTION, 3)) {
                    ControladorFornecedor.deleteFornececor(fornecedorAlterarExcluir);
                    atualizaFornecedor();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Selecione um fornecedor para realizar a exclusão!", "Atenção!", 2);
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Houve um problema ao excluir esta unidade de medida!\nExistem produtos associados a este fornecedor.", "Atenção!", 2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Houve um problema ao excluir esta unidade de medida!\nErro: " + ex, "Erro!", 0);
        }
        btnLimparClicado();
    }

    /**
     * Método invocado quando o botão Salvar é clicado.
     */
    private void btnSalvarFornecedor() {

        try {
            //valida campos
            if (!this.jtfNome.getText().equalsIgnoreCase("") && (!this.jfCPF.getText().equalsIgnoreCase("   .   .   -  ") || (!this.jfCNPJ.getText().equalsIgnoreCase("  .   .   /    -  ")))) {

                Fornecedor fornecedor = new Fornecedor(this.jtfNome.getText(), this.jfCPF.getText(), this.jfCNPJ.getText());

                if (fornecedor.getCpf().equalsIgnoreCase("   .   .   -  ")) {
                    fornecedor.setCpf("null");
                }
                if (fornecedor.getCnpj().equalsIgnoreCase("  .   .   /    -  ")) {
                    fornecedor.setCnpj("null");
                }

                //decide se vai fazer update ou insert
                if (fornecedorAlterarExcluir == null) {
                    ControladorFornecedor.inserirFornecedor(fornecedor);
                    btnLimparClicado();

                } else {

                    ControladorFornecedor.updateFornecedorPorId("nome = '" + fornecedor.getNome() + "', cpf = '" + fornecedor.getCpf() + "', cnpj= '" + fornecedor.getCnpj() + "'", Integer.toString(fornecedorAlterarExcluir.getIdFornecedor()));
                    btnLimparClicado();
                }
                atualizaFornecedor();
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos necessários !", "Atenção!", 2);
            }

        } catch (MySQLIntegrityConstraintViolationException ex) {

            JOptionPane.showMessageDialog(null, "O CPF ou CNPJ já existem na base de dados !", "Atenção!", 2);
            Logger.getLogger(FRMCadastroFornecedor.class.getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(FRMCadastroFornecedor.class.getName()).log(Level.SEVERE, null, ex);
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
        java.awt.GridBagConstraints gridBagConstraints;

        bgControleLote = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtFornecedor = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlNome = new javax.swing.JLabel();
        jlCPF = new javax.swing.JLabel();
        jbtnSalvar = new javax.swing.JButton();
        jbtnLimpar = new javax.swing.JButton();
        jbtnExcluir = new javax.swing.JButton();
        jtfNome = new javax.swing.JTextField();
        jlCNPJ = new javax.swing.JLabel();
        jfCPF = new javax.swing.JFormattedTextField();
        jfCNPJ = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cadastro/Edição de Fornecedores");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(null);
        setMinimumSize(new java.awt.Dimension(584, 443));

        jScrollPane1.setName(""); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 200));

        jtFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jtFornecedor);

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

        jlCPF.setText("CPF:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(jlCPF, gridBagConstraints);

        jbtnSalvar.setText("Salvar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 14;
        jPanel1.add(jbtnSalvar, gridBagConstraints);

        jbtnLimpar.setText("Limpar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        jPanel1.add(jbtnLimpar, gridBagConstraints);

        jbtnExcluir.setText("Excluir");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 14;
        jPanel1.add(jbtnExcluir, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jtfNome, gridBagConstraints);

        jlCNPJ.setText("CNPJ:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        jPanel1.add(jlCNPJ, gridBagConstraints);

        try {
            jfCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jfCPF, gridBagConstraints);

        try {
            jfCNPJ.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 70;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jfCNPJ, gridBagConstraints);

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
            java.util.logging.Logger.getLogger(FRMCadastroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRMCadastroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRMCadastroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRMCadastroFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FRMCadastroFornecedor dialog = new FRMCadastroFornecedor(new javax.swing.JFrame(), true);
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
    private javax.swing.JFormattedTextField jfCNPJ;
    private javax.swing.JFormattedTextField jfCPF;
    private javax.swing.JLabel jlCNPJ;
    private javax.swing.JLabel jlCPF;
    private javax.swing.JLabel jlNome;
    private javax.swing.JTable jtFornecedor;
    private javax.swing.JTextField jtfNome;
    // End of variables declaration//GEN-END:variables
}
