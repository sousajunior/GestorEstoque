package br.com.gestorestoque.view;

import br.com.gestorestoque.controller.ControladorArmazem;
import br.com.gestorestoque.model.Armazem;
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
 * @author DG
 */
public class FRMCadastroArmazem extends javax.swing.JDialog {

    TableModel modeloTabelaArmazem;
    Armazem armazemAlterarExcluir;
    Boolean somentePesquisa;
    ControladorArmazem ctrlArmazem;
    /**
     * Creates new form FRMCadastroArmazem
     * @param parent
     * @param modal
     */
    public FRMCadastroArmazem(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        somentePesquisa = false;
        initialize();
    }

    public FRMCadastroArmazem(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        somentePesquisa = true;
        initialize();
    }

    public void initialize() {
        initComponents();
        this.setLocationRelativeTo(null);
        ctrlArmazem = new ControladorArmazem();
        prepararComponentes();
    }

    /**
     * Executa o método ControladorArmazem.selecionarTodosArmazens() da classe
     * ControladorArmazem. Este método retorna uma lista com todos os armazéns
     * cadastrados na base de dados.
     *
     * @return
     */
    private List<Armazem> getArmazens() {

        List<Armazem> armazens = new ArrayList<>();
        try {
            armazens = ctrlArmazem.selecionarTodos();
            return armazens;
        } catch (SQLException ex) {
            Logger.getLogger(FRMCadastroArmazem.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Classe interna que define o TableModel da tabela de Armazém.
     */
    private class ArmazemTableModel extends AbstractTableModel {

        public final List<Armazem> armazens;

        public ArmazemTableModel(List<Armazem> armazens) {
            this.armazens = armazens;
        }

        /**
         * Retorna a quantidade de linhas existentes na TableModel
         *
         * @return
         */
        @Override
        public int getRowCount() {
            return armazens.size();
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

            if (rowIndex < 0 | rowIndex >= armazens.size()) {
                return null;

            }

            Armazem armazem = armazens.get(rowIndex);

            if (columnIndex == 0) {
                return armazem.getCodigo();
            } else if (columnIndex == 1) {
                return armazem.getDescricao();
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

                return "ID";
            }
            if (column == 1) {

                return "Descrição";
            }

            return null;
        }

    }

    /**
     * Prepara os componentes da tela para o uso da mesma. O preparo dos
     * componentes envolve adição de listeners
     */
    public void prepararComponentes() {

        //Preparar Jtable de armazéns
        atualizaTabelaArmazens();

        jtArmazens.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        jtArmazens.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    if (somentePesquisa) {
                        armazemAlterarExcluir = new Armazem();
                        armazemAlterarExcluir.setCodigo(Integer.parseInt("" + modeloTabelaArmazem.getValueAt(jtArmazens.getSelectedRow(), 0)));
                        armazemAlterarExcluir.setDescricao(modeloTabelaArmazem.getValueAt(jtArmazens.getSelectedRow(), 1).toString());
                        dispose();
                    }
                } else {
                    tabelaArmazemClicada();
                }
            }
        });

        jtArmazens.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int posicaoAtual = jtArmazens.getSelectedRow();

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (posicaoAtual >= 1) {

                        jtArmazens.setRowSelectionInterval(posicaoAtual, posicaoAtual);
                        tabelaArmazemClicada();
                    }
                    if (posicaoAtual == 0) {

                        jtArmazens.setRowSelectionInterval(posicaoAtual, posicaoAtual);
                        tabelaArmazemClicada();
                    }

                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (posicaoAtual < jtArmazens.getRowCount()) {

                        jtArmazens.setRowSelectionInterval(posicaoAtual, posicaoAtual);
                        tabelaArmazemClicada();

                    }

                }
            }
        });

        //Preparar Botoes 
        //Salvar
        jbtnSalvar.addActionListener(
                (e) -> {
                    btnSalvarArmazemClicado();
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

        //jdialogArmazem
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

    private boolean verificarComponentesPreenchidos() {

        if (!jtfDescricao.getText().equalsIgnoreCase("")) {
            return true;
        }

        return false;
    }

    /**
     * Atualiza as linhas da tabela de armazéns com todos os armazéns
     * cadastrados na base.
     */
    private void atualizaTabelaArmazens() {
        modeloTabelaArmazem = new ArmazemTableModel(getArmazens());
        jtArmazens.setModel(modeloTabelaArmazem);
    }

    /**
     * Método invocado quando alguma linha da tabela de armazéns é selecionada.
     */
    private void tabelaArmazemClicada() {
        this.armazemAlterarExcluir = new Armazem();
        this.armazemAlterarExcluir = procurarArmazemNaLista(modeloTabelaArmazem.getValueAt(jtArmazens.getSelectedRow(), 1).toString());
        jtfDescricao.setText(modeloTabelaArmazem.getValueAt(jtArmazens.getSelectedRow(), 1).toString());
    }

    /**
     * Método invocado quando o botão Limpar é clicado.
     */
    private void btnLimparClicado() {
        this.jtfDescricao.setText("");
        armazemAlterarExcluir = null;

    }

    /**
     * Método invocado quando o botão Excluir é clicado.
     */
    private void btnExcluirClicado() {

        try {
            if (!this.jtfDescricao.getText().equalsIgnoreCase("")) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir este item?", "Confirmação de exclusão", JOptionPane.YES_NO_OPTION, 3)) {
                    Armazem armazem = new Armazem(this.jtfDescricao.getText());
                    ctrlArmazem.deletar(armazem);
                    atualizaTabelaArmazens();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Selecione um armazém para realizar a exclusão!", "Atenção!", 2);
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Houve um problema ao excluir este armazém!\nJá existem produtos armazenados neste armazém,\npara realizar a exclusão, \nremova todos os produtos deste armazém .", "Atenção!", 2);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Houve um problema ao excluir este armazém!\nErro: " + ex, "Erro!", 0);
        }
        btnLimparClicado();
    }

    /**
     * Método invocado quando o botão Salvar é clicado.
     */
    private void btnSalvarArmazemClicado() {

        try {
            if (!this.jtfDescricao.getText().equalsIgnoreCase("")) {
                if (armazemAlterarExcluir == null) {

                    Armazem armazem = new Armazem(this.jtfDescricao.getText());
                    //armazem = procurarArmazemNaLista(armazem.getDescricao());
                    ctrlArmazem.inserir(armazem);

                } else {
                    armazemAlterarExcluir.setDescricao(jtfDescricao.getText());
                    ctrlArmazem.atualizarPorCodigo(armazemAlterarExcluir);
                }
                atualizaTabelaArmazens();
            } else {
                JOptionPane.showMessageDialog(null, "O armazém deve ter uma descrição!", "Atenção!", 2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FRMCadastroArmazem.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnLimparClicado();

    }

    /**
     * Método procura um armazém na base de dados. É necessário informar a
     * descrição do armazém por parâmetro.
     *
     * @param descricao
     * @return
     */
    private Armazem procurarArmazemNaLista(String descricao) {

        for (Armazem armazem : getArmazens()) {
            if (armazem.getDescricao().equals(descricao)) {
                armazemAlterarExcluir = armazem;
                return armazem;
            }
        }

        return null;
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jtArmazens = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlDescricao = new javax.swing.JLabel();
        jtfDescricao = new javax.swing.JTextField();
        jbtnSalvar = new javax.swing.JButton();
        jbtnLimpar = new javax.swing.JButton();
        jbtnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cadastro de armazém");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(584, 443));

        jScrollPane1.setName(""); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 200));

        jtArmazens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Descrição"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtArmazens.setToolTipText("Tabela de armazéns");
        jScrollPane1.setViewportView(jtArmazens);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jPanel1.setMinimumSize(new java.awt.Dimension(200, 100));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel1.setRequestFocusEnabled(false);
        java.awt.GridBagLayout jPanel1Layout1 = new java.awt.GridBagLayout();
        jPanel1Layout1.columnWidths = new int[] {0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0};
        jPanel1Layout1.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel1.setLayout(jPanel1Layout1);

        jlDescricao.setText("Descrição:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jlDescricao, gridBagConstraints);

        jtfDescricao.setToolTipText("Informe a descrição do armazém");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 19;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 380;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jtfDescricao, gridBagConstraints);

        jbtnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/save_accept.png"))); // NOI18N
        jbtnSalvar.setText("Salvar");
        jbtnSalvar.setToolTipText("Salvar/Atualizar armazém");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 3;
        jPanel1.add(jbtnSalvar, gridBagConstraints);

        jbtnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/edit-clear.png"))); // NOI18N
        jbtnLimpar.setText("Limpar");
        jbtnLimpar.setToolTipText("Novo armazém");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jbtnLimpar, gridBagConstraints);

        jbtnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/trashcan-delete2.png"))); // NOI18N
        jbtnExcluir.setText("Excluir");
        jbtnExcluir.setToolTipText("Excluir armazém");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 18;
        jPanel1.add(jbtnExcluir, gridBagConstraints);

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
            java.util.logging.Logger.getLogger(FRMCadastroArmazem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRMCadastroArmazem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRMCadastroArmazem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRMCadastroArmazem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FRMCadastroArmazem dialog = new FRMCadastroArmazem(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtnExcluir;
    private javax.swing.JButton jbtnLimpar;
    private javax.swing.JButton jbtnSalvar;
    private javax.swing.JLabel jlDescricao;
    protected javax.swing.JTable jtArmazens;
    private javax.swing.JTextField jtfDescricao;
    // End of variables declaration//GEN-END:variables
}
