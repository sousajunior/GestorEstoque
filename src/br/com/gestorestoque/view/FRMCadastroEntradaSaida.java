/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view;

import br.com.gestorestoque.controller.Controlador;
import br.com.gestorestoque.controller.ControladorProdutoArmazenado;
import br.com.gestorestoque.controller.ControladorArmazem;
import br.com.gestorestoque.controller.ControladorFornecedor;
import br.com.gestorestoque.controller.ControladorMovimentacao;
import br.com.gestorestoque.controller.ControladorProduto;
import br.com.gestorestoque.model.Produto;
import br.com.gestorestoque.model.Armazem;
import br.com.gestorestoque.model.Fornecedor;
import br.com.gestorestoque.model.Movimentacao;
import br.com.gestorestoque.model.ProdutoArmazenado;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Carlinhos
 */
public class FRMCadastroEntradaSaida extends javax.swing.JDialog {

    ProdutoArmazenado produtoArmazenado = new ProdutoArmazenado();
    ControladorProduto ctrlProduto;
    ControladorFornecedor ctrlFornecedor;
    ControladorArmazem ctrlArmazem;
    ControladorProdutoArmazenado ctrlProdutoArmazenado;
    Controlador controlador;

    /**
     * Creates new form FRMCadastroEntrada
     *
     * @param parent
     * @param modal
     * @param isEntrada
     * @param title
     */
    public FRMCadastroEntradaSaida(java.awt.Frame parent, boolean modal, boolean isEntrada, String title) {
        super(parent, title, modal);
        initialize(isEntrada);
    }

    /**
     * Creates new form FRMCadastroEntrada
     *
     * @param parent
     * @param modal
     * @param isEntrada
     * @param title
     */
    public FRMCadastroEntradaSaida(java.awt.Dialog parent, boolean modal, boolean isEntrada, String title) {
        super(parent, title, modal);
        initialize(isEntrada);
    }

    private void initialize(boolean isEntrada) {
        initComponents();
        this.setLocationRelativeTo(null);
        isEntrada(isEntrada);
        this.ctrlProdutoArmazenado = new ControladorProdutoArmazenado();
        this.ctrlProduto = new ControladorProduto();
        this.ctrlFornecedor = new ControladorFornecedor();
        this.controlador = new ControladorProdutoArmazenado();
        this.ctrlArmazem = new ControladorArmazem();
        prepararComponentes();
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

        jPanel1 = new javax.swing.JPanel();
        jlDescricao = new javax.swing.JLabel();
        jtfLote = new javax.swing.JTextField();
        jbtnSalvar = new javax.swing.JButton();
        jbtnLimpar = new javax.swing.JButton();
        jlDescricao2 = new javax.swing.JLabel();
        jlDescricao3 = new javax.swing.JLabel();
        jlTipoMovimentacao = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jrbEntrada = new javax.swing.JRadioButton();
        jrbSaida = new javax.swing.JRadioButton();
        jsCodigoFornecedor = new javax.swing.JSpinner();
        jtfNomeFornecedor = new javax.swing.JTextField();
        jlFornecedor = new javax.swing.JLabel();
        jlArmazem1 = new javax.swing.JLabel();
        jsCodigoArmazem1 = new javax.swing.JSpinner();
        jtfNomeArmazem1 = new javax.swing.JTextField();
        jlProduto = new javax.swing.JLabel();
        jsProduto = new javax.swing.JSpinner();
        jtfNomeProduto = new javax.swing.JTextField();
        jbtnPesquisarArmazem = new javax.swing.JButton();
        jbtnPesquisarProduto = new javax.swing.JButton();
        jbtnPesquisarFornecedor = new javax.swing.JButton();
        jsQtd = new javax.swing.JSpinner();
        jftNotaFiscal = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Entrada/Saída de Produtos");
        setMinimumSize(new java.awt.Dimension(510, 380));
        setPreferredSize(new java.awt.Dimension(510, 380));

        jPanel1.setMinimumSize(new java.awt.Dimension(200, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel1.setRequestFocusEnabled(false);
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0};
        jPanel1Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel1.setLayout(jPanel1Layout);

        jlDescricao.setText("Lote:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jlDescricao, gridBagConstraints);

        jtfLote.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        jPanel1.add(jtfLote, gridBagConstraints);

        jbtnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/save_accept.png"))); // NOI18N
        jbtnSalvar.setText("Salvar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 18;
        jPanel1.add(jbtnSalvar, gridBagConstraints);

        jbtnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/edit-clear.png"))); // NOI18N
        jbtnLimpar.setText("Limpar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jbtnLimpar, gridBagConstraints);

        jlDescricao2.setText("Nota Fiscal:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jlDescricao2, gridBagConstraints);

        jlDescricao3.setText("Qtd:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jlDescricao3, gridBagConstraints);

        jlTipoMovimentacao.setText("Tipo Movimentação:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        jPanel1.add(jlTipoMovimentacao, gridBagConstraints);

        jrbEntrada.setText("Entrada");
        jPanel2.add(jrbEntrada);

        jrbSaida.setText("Saída");
        jPanel2.add(jrbSaida);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        jPanel1.add(jPanel2, gridBagConstraints);

        jsCodigoFornecedor.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        jPanel1.add(jsCodigoFornecedor, gridBagConstraints);

        jtfNomeFornecedor.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        jPanel1.add(jtfNomeFornecedor, gridBagConstraints);

        jlFornecedor.setText("Fornecedor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jlFornecedor, gridBagConstraints);

        jlArmazem1.setText("Armazem:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jlArmazem1, gridBagConstraints);

        jsCodigoArmazem1.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        jPanel1.add(jsCodigoArmazem1, gridBagConstraints);

        jtfNomeArmazem1.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        jPanel1.add(jtfNomeArmazem1, gridBagConstraints);

        jlProduto.setText("Produto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jlProduto, gridBagConstraints);

        jsProduto.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        jPanel1.add(jsProduto, gridBagConstraints);

        jtfNomeProduto.setEditable(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        jPanel1.add(jtfNomeProduto, gridBagConstraints);

        jbtnPesquisarArmazem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/search-16.png"))); // NOI18N
        jbtnPesquisarArmazem.setToolTipText("Abre a tela de armazéns. (duplo clique na tabela para selecionar um Armazém)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 6;
        jPanel1.add(jbtnPesquisarArmazem, gridBagConstraints);

        jbtnPesquisarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/search-16.png"))); // NOI18N
        jbtnPesquisarProduto.setToolTipText("Abre a tela de produtos. (duplo clique na tabela para selecionar um produto)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 4;
        jPanel1.add(jbtnPesquisarProduto, gridBagConstraints);

        jbtnPesquisarFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/search-16.png"))); // NOI18N
        jbtnPesquisarFornecedor.setToolTipText("Abre a tela de fornecedores. (duplo clique na tabela para selecionar um fornecedor)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 12;
        gridBagConstraints.gridy = 8;
        jPanel1.add(jbtnPesquisarFornecedor, gridBagConstraints);

        jsQtd.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jsQtd.setRequestFocusEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        jPanel1.add(jsQtd, gridBagConstraints);

        jftNotaFiscal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 2, 5, 2);
        jPanel1.add(jftNotaFiscal, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

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
            java.util.logging.Logger.getLogger(FRMCadastroEntradaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRMCadastroEntradaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRMCadastroEntradaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRMCadastroEntradaSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FRMCadastroEntradaSaida dialog = new FRMCadastroEntradaSaida(new javax.swing.JFrame(), true, true, "");
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jbtnLimpar;
    private javax.swing.JButton jbtnPesquisarArmazem;
    private javax.swing.JButton jbtnPesquisarFornecedor;
    private javax.swing.JButton jbtnPesquisarProduto;
    private javax.swing.JButton jbtnSalvar;
    private javax.swing.JFormattedTextField jftNotaFiscal;
    private javax.swing.JLabel jlArmazem1;
    private javax.swing.JLabel jlDescricao;
    private javax.swing.JLabel jlDescricao2;
    private javax.swing.JLabel jlDescricao3;
    private javax.swing.JLabel jlFornecedor;
    private javax.swing.JLabel jlProduto;
    private javax.swing.JLabel jlTipoMovimentacao;
    private javax.swing.JRadioButton jrbEntrada;
    private javax.swing.JRadioButton jrbSaida;
    private javax.swing.JSpinner jsCodigoArmazem1;
    private javax.swing.JSpinner jsCodigoFornecedor;
    private javax.swing.JSpinner jsProduto;
    private javax.swing.JSpinner jsQtd;
    private javax.swing.JTextField jtfLote;
    private javax.swing.JTextField jtfNomeArmazem1;
    private javax.swing.JTextField jtfNomeFornecedor;
    private javax.swing.JTextField jtfNomeProduto;
    // End of variables declaration//GEN-END:variables

    private void prepararComponentes() {

        jrbEntrada.addActionListener(
                (e) -> {
                    alterarParaEntrada();
                }
        );

        jrbSaida.addActionListener(
                (e) -> {
                    alterarParaSaida();
                }
        );

        jbtnSalvar.addActionListener(
                (e) -> {
                    Salvar();
                }
        );

        jbtnLimpar.addActionListener(
                (e) -> {
                    limpar();
                }
        );

        jftNotaFiscal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                String caracteres = "0987654321";
                if (!caracteres.contains(ke.getKeyChar() + "")) {
                    ke.consume();
                }
            }
        });

        jftNotaFiscal.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent fe) {
                if (jftNotaFiscal.getText().equalsIgnoreCase("")) {
                    jftNotaFiscal.setValue(null);
                }
            }
        });
        jbtnPesquisarArmazem.addActionListener(
                (e) -> {
                    pesquisaArmazem();
                }
        );

        jbtnPesquisarFornecedor.addActionListener(
                (e) -> {
                    pesquisaFornecedor();
                }
        );
        jbtnPesquisarProduto.addActionListener(
                (e) -> {
                    pesquisaProduto();
                }
        );

        ChangeListener Produtolistener = (ChangeEvent e) -> {
            try {
                if (jsProduto.getValue().toString().equalsIgnoreCase("0")) {
                    jtfNomeProduto.setText("");
                    return;
                }
                Produto p = this.ctrlProduto.selecionarPorCodigo(Integer.parseInt(jsProduto.getValue().toString()));

                if (p.getNome() == null) {
                    JOptionPane.showMessageDialog(null, "O código fornecido não remete a nenhum produto cadastrado!", "Atenção, produto inexistente!", JOptionPane.WARNING_MESSAGE);
                    jtfNomeProduto.setText("");
                    jsProduto.setValue(0);
                    jsProduto.requestFocus();
                } else {
                    jtfNomeProduto.setText(p.getNome());
                    if (jrbEntrada.isSelected()) {
                        if (p.isControladoPorLote()) {
                            jtfLote.setEnabled(true);
                            jtfLote.setText("");
                        } else {
                            jtfLote.setEnabled(false);
                            jtfLote.setText("");
                        }
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível concluir a operação: " + ex, "Erro", JOptionPane.ERROR_MESSAGE);
            }

        };

        ChangeListener ArmazemListener = (ChangeEvent e) -> {

            try {
                if (this.jsCodigoArmazem1.getValue().toString().equalsIgnoreCase("0")) {
                    jtfNomeArmazem1.setText("");
                    return;
                }
                Armazem a = ctrlArmazem.selecionarPorCodigo(Integer.parseInt(jsCodigoArmazem1.getValue().toString()));

                if (a.getDescricao() == null) {
                    JOptionPane.showMessageDialog(null, "O código fornecido não remete a nenhum armazém cadastrado!", "Atenção, armazém não existe!", JOptionPane.WARNING_MESSAGE);
                    jtfNomeArmazem1.setText("");
                    jsCodigoArmazem1.setValue(0);
                    jsCodigoArmazem1.requestFocus();
                } else {
                    jtfNomeArmazem1.setText(a.getDescricao());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível concluir a operação: " + ex, "Erro", JOptionPane.ERROR_MESSAGE);
            }

        };

        ChangeListener FornecedorListener = (ChangeEvent e) -> {

            try {
                if (this.jsCodigoFornecedor.getValue().toString().equalsIgnoreCase("0")) {
                    jtfNomeFornecedor.setText("");
                    return;
                }
                Fornecedor f = this.ctrlFornecedor.selecionarPorCodigo(Integer.parseInt(jsCodigoFornecedor.getValue().toString()));

                if (f.getNome() == null) {
                    JOptionPane.showMessageDialog(null, "O código fornecido não remete a nenhum fornecedor cadastrado!", "Atenção, fornecedor não existe!", JOptionPane.WARNING_MESSAGE);
                    jtfNomeFornecedor.setText("");
                    jsCodigoFornecedor.setValue(0);
                    jsCodigoFornecedor.requestFocus();
                } else {
                    jtfNomeFornecedor.setText(f.getNome());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Não foi possível concluir a operação: " + ex, "Erro", JOptionPane.ERROR_MESSAGE);
            }

        };

        jsProduto.addChangeListener(Produtolistener);
        jsCodigoArmazem1.addChangeListener(ArmazemListener);
        jsCodigoFornecedor.addChangeListener(FornecedorListener);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!jsCodigoArmazem1.getValue().toString().equalsIgnoreCase("0") || !jsCodigoFornecedor.getValue().toString().equalsIgnoreCase("0") || !jsProduto.getValue().toString().equalsIgnoreCase("0") || !jftNotaFiscal.getText().equalsIgnoreCase("") || (jtfLote.isEnabled() && !jtfLote.getText().equalsIgnoreCase(""))) {
                    if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Há itens que não foram salvos!\n Deseja mesmo sair?", "Fechar", JOptionPane.YES_NO_OPTION, 3)) {
                        dispose();
                    }

                } else {
                    dispose();
                }
            }

        });

    }

    private void pesquisaArmazem() {
        FRMCadastroArmazem cadastroArmazem = new FRMCadastroArmazem(this, true);
        cadastroArmazem.jtArmazens.setToolTipText("Duplo clique para selecionar um armazém!");
        cadastroArmazem.setVisible(true);
        cadastroArmazem.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    jsCodigoArmazem1.setValue(cadastroArmazem.armazemAlterarExcluir.getCodigo());
                    jtfNomeArmazem1.setText(cadastroArmazem.armazemAlterarExcluir.getDescricao());
                } catch (Exception ex) {

                }
            }
        });
    }

    private void pesquisaFornecedor() {
        FRMCadastroFornecedor cadastroFornecedor = new FRMCadastroFornecedor(this, true);
        cadastroFornecedor.jtFornecedor.setToolTipText("Duplo clique para selecionar um fornecedor!");
        cadastroFornecedor.setVisible(true);
        cadastroFornecedor.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    jsCodigoFornecedor.setValue(cadastroFornecedor.fornecedorAlterarExcluir.getIdFornecedor());
                    jtfNomeFornecedor.setText(cadastroFornecedor.fornecedorAlterarExcluir.getNome());
                } catch (Exception ex) {

                }
            }
        });
    }

    private void pesquisaProduto() {

        if (this.jrbSaida.isSelected()) {
            FRMRelatorioSaldoEstoque frmSaldo = new FRMRelatorioSaldoEstoque(this, true);
            frmSaldo.jbtEntradaProduto.setEnabled(false);
            frmSaldo.jbtSaidaProduto.setEnabled(false);
            frmSaldo.jbtnventario.setEnabled(false);
            frmSaldo.jtProdutosArmazenados.setToolTipText("Duplo clique para selecionar um produto!");
            frmSaldo.setVisible(true);

            frmSaldo.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    try {
                        produtoArmazenado = frmSaldo.produtoArmazenadoPesquisa;

                        //JOptionPane.showMessageDialog(null, produtoArmazenado.getProduto().isControladoPorLote());
                        jsCodigoArmazem1.setValue(produtoArmazenado.getArmazem().getCodigo());
                        jtfNomeArmazem1.setText(produtoArmazenado.getArmazem().getDescricao());
                        jsProduto.setValue(produtoArmazenado.getProduto().getCodigo());
                        jtfNomeProduto.setText(produtoArmazenado.getProduto().getNome());
                        jsCodigoFornecedor.setValue(produtoArmazenado.getFornecedor().getIdFornecedor());
                        jtfNomeFornecedor.setText(produtoArmazenado.getFornecedor().getNome());
                        //jsQtd.setValue(produtoArmazenado.getQuantidade());
                        jftNotaFiscal.setText(Integer.toString(produtoArmazenado.getNotaFiscal()));
                        if (produtoArmazenado.getProduto().isControladoPorLote()) {
                            jtfLote.setText(produtoArmazenado.getLote());

                        } else {
                            //JOptionPane.showMessageDialog(null, "limpar");
                            jtfLote.setEnabled(false);
                            jtfLote.setText("");
                        }
                    } catch (Exception ex) {

                    }
                }
            });

        } else {
            FRMCadastroProduto cadastroProduto = new FRMCadastroProduto(this, true);
            cadastroProduto.jtProdutos.setToolTipText("Duplo clique para selecionar um produto!");
            cadastroProduto.setVisible(true);

            cadastroProduto.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    try {
                        jsProduto.setValue(cadastroProduto.produtoAlterarExcluir.getCodigo());
                        jtfNomeProduto.setText(cadastroProduto.produtoAlterarExcluir.getNome());
                    } catch (Exception ex) {

                    }
                }
            });
        }
    }

    private void isEntrada(Boolean isEntrada) {
        if (isEntrada) {
            jrbEntrada.setSelected(true);
            jrbEntrada.setSelected(false);
            alterarParaEntrada();
        } else {
            jrbEntrada.setSelected(false);
            jrbSaida.setSelected(true);
            alterarParaSaida();
        }
    }

    private boolean verificarComponentesPreenchidos() {
        if ((this.jtfLote.isEnabled() && this.jtfLote.getText().equalsIgnoreCase("")) || this.jsQtd.getValue().equals(0) || jftNotaFiscal.getText().equalsIgnoreCase("") || jsCodigoArmazem1.getValue().equals(0) || jsCodigoFornecedor.getValue().equals(0) || jsProduto.getValue().equals(0)) {
            return false;
        }
        return true;
    }

    private void Salvar() {
        //valida os campos
        if (!verificarComponentesPreenchidos()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos necessários !", "Atenção!", 2);
            return;
        }

        //tenta inserir
        try {

            if (this.jrbEntrada.isSelected()) {
                produtoArmazenado = carregarCampos();
                //if(produtoArmazenado.getProduto().getNome().equalsIgnoreCase(jtf))
                ctrlProdutoArmazenado.inserir(produtoArmazenado);

                produtoArmazenado = ctrlProdutoArmazenado.selecionarUltimoRegistro();
                //Salvar movimentação
                controlador = new ControladorMovimentacao();
                controlador.inserir(new Movimentacao(produtoArmazenado.getLote(), produtoArmazenado.getQuantidade(), produtoArmazenado.getNotaFiscal(), "E", new Date(), produtoArmazenado, produtoArmazenado.getArmazem()));
                
                JOptionPane.showMessageDialog(null, "Entrada realizada com sucesso!", "Concluído!", JOptionPane.INFORMATION_MESSAGE);
                
                limpar();
                
            } else //da baixa se a quantidade for válida
            {
                if (produtoArmazenado.getQuantidade() >= Double.parseDouble(jsQtd.getValue().toString())) {
                    produtoArmazenado.setQuantidade(produtoArmazenado.diminuirQuantidade(Double.parseDouble("" + jsQtd.getValue())));
                    controlador = new ControladorProdutoArmazenado();
                    controlador.atualizarPorCodigo(produtoArmazenado);
                    //Salvar movimentação
                    controlador = new ControladorMovimentacao();
                    controlador.inserir(new Movimentacao(produtoArmazenado.getLote(), produtoArmazenado.getQuantidade(), produtoArmazenado.getNotaFiscal(), "S", new Date(), produtoArmazenado, produtoArmazenado.getArmazem()));

                    JOptionPane.showMessageDialog(null, "Saída realizada com sucesso!", "Concluído!", JOptionPane.INFORMATION_MESSAGE);
                    limpar();
                } else {
                    JOptionPane.showMessageDialog(null, "Não há quantidade suficiente no armazém para concluir a operação. ", "Atenção!", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível concluir a operação: " + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ProdutoArmazenado carregarCampos() {
        ProdutoArmazenado p = new ProdutoArmazenado();
        try {
            p.setLote(jtfLote.getText());
            p.setQuantidade(Double.parseDouble(jsQtd.getValue().toString()));
            p.setNotaFiscal(Integer.parseInt(jftNotaFiscal.getText()));
            p.setArmazem(ctrlArmazem.selecionarPorCodigo(Integer.parseInt(this.jsCodigoArmazem1.getValue().toString())));
            p.setProduto(new ControladorProduto().selecionarPorCodigo(Integer.parseInt(this.jsProduto.getValue().toString())));
            p.setFornecedor(this.ctrlFornecedor.selecionarPorCodigo(Integer.parseInt(this.jsCodigoFornecedor.getValue().toString())));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível concluir a operação: " + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return p;
    }

    private void limpar() {
        this.jsCodigoArmazem1.setValue(0);
        this.jsCodigoFornecedor.setValue(0);
        this.jsProduto.setValue(0);
        this.jsQtd.setValue(1);
        this.jtfLote.setText("");
        this.jtfNomeArmazem1.setText("");
        this.jtfNomeFornecedor.setText("");
        this.jtfNomeProduto.setText("");
        this.jftNotaFiscal.setText("");
    }

    private void habilitaComponentes() {
        this.jsCodigoArmazem1.setEnabled(true);
        this.jsCodigoFornecedor.setEnabled(true);
        this.jsProduto.setEnabled(true);
        this.jsQtd.setEnabled(true);
        this.jtfLote.setEnabled(true);
        this.jtfNomeArmazem1.setEnabled(true);
        this.jtfNomeFornecedor.setEnabled(true);
        this.jtfNomeProduto.setEnabled(true);
        this.jftNotaFiscal.setEnabled(true);
        this.jbtnPesquisarArmazem.setEnabled(true);
        this.jbtnPesquisarFornecedor.setEnabled(true);
    }

    private void desabilitaComponentes() {
        this.jsCodigoArmazem1.setEnabled(false);
        this.jsCodigoFornecedor.setEnabled(false);
        this.jsProduto.setEnabled(false);
        this.jtfLote.setEnabled(false);
        this.jtfNomeArmazem1.setEnabled(false);
        this.jtfNomeFornecedor.setEnabled(false);
        this.jtfNomeProduto.setEnabled(false);
        this.jftNotaFiscal.setEnabled(false);
        this.jbtnPesquisarArmazem.setEnabled(false);
        this.jbtnPesquisarFornecedor.setEnabled(false);
    }

    private void alterarParaEntrada() {
        habilitaComponentes();
        limpar();
        jrbEntrada.setSelected(true);
        jrbSaida.setSelected(false);
    }

    private void alterarParaSaida() {
        desabilitaComponentes();
        limpar();
        jrbSaida.setSelected(true);
        jrbEntrada.setSelected(false);
    }
}
