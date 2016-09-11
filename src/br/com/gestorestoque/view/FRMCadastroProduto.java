package br.com.gestorestoque.view;

import br.com.gestorestoque.controller.ControladorProduto;
import br.com.gestorestoque.controller.ControladorUnidadeMedida;
import br.com.gestorestoque.model.Produto;
import br.com.gestorestoque.model.UnidadeMedida;
import java.awt.Color;
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
public class FRMCadastroProduto extends javax.swing.JDialog {

    List<Produto> produtos = new ArrayList<>();
    TableModel modeloTabelaProduto;
    Produto produtoAlterarExcluir;
    UnidadeMedida unidadeMedidaSelecionada;
    List<UnidadeMedida> unidadesMedida = new ArrayList<>();

    /**
     * Creates new form FRMCadastroProduto
     */
    public FRMCadastroProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        prepararComponentes();
    }

    /**
     * Executa o método ControladorProduto.selecionarTodosArmazens() da classe
     * ControladorProduto. Este método retorna uma lista com todos os armazéns
     * cadastrados na base de dados.
     *
     * @return
     */
    private List<Produto> getProdutos() {

        produtos = new ArrayList<>();
        try {
            produtos = ControladorProduto.selecionarTodosProdutos();
            return produtos;
        } catch (SQLException ex) {
            Logger.getLogger(FRMCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Classe interna que define o TableModel da tabela de Armazém.
     */
    private class ProdutoTableModel extends AbstractTableModel {

        public final List<Produto> produtos;

        public ProdutoTableModel(List<Produto> produtos) {
            this.produtos = produtos;

        }

        public final void esconderCodigo() {
            jtProdutos.removeColumn(jtProdutos.getColumnModel().getColumn(0));
        }

        /**
         * Retorna a quantidade de linhas existentes na TableModel
         *
         * @return
         */
        @Override
        public int getRowCount() {
            return produtos.size();
        }

        /**
         * Retorna a quantidade de colunas existentes na TableModel
         *
         * @return
         */
        @Override
        public int getColumnCount() {
            return 6;
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

            if (rowIndex < 0 | rowIndex >= produtos.size()) {
                return null;

            }

            Produto produto = produtos.get(rowIndex);

            if (columnIndex == 0) {
                return produto.getCodigo();
            }

            if (columnIndex == 1) {
                return produto.getNome();
            }

            if (columnIndex == 2) {
                if (produto.isControladoPorLote()) {
                    return "S";
                } else {
                    return "N";
                }
            }

            if (columnIndex == 3) {

                return produto.getPreco();

            }

            if (columnIndex == 4) {

                return produto.getQuantidadeMinima();

            }
            if (columnIndex == 5) {

                return produto.getUnidadeMedida().getNome();

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
                return "Código";
            }

            if (column == 1) {

                return "Nome";
            }

            if (column == 2) {
                return "Controlado por Lote";
            }

            if (column == 3) {

                return "Preço";

            }

            if (column == 4) {

                return "Quantidade Mínima";
            }

            if (column == 5) {

                return "Unidade de Medida";
            }

            return null;
        }

    }

    /**
     * Prepara os componentes da tela para o uso da mesma. O preparo dos
     * componentes envolve adição de listeners
     */
    public void prepararComponentes() {

        //Preparar Jtable de produtos
        atualizarTabelaProdutos();

        //jtProdutos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jtProdutos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                tabelaProdutoClicada();
            }
        });

        jtProdutos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int posicaoAtual = jtProdutos.getSelectedRow();

                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (posicaoAtual >= 1) {

                        jtProdutos.setRowSelectionInterval(posicaoAtual, posicaoAtual);
                        tabelaProdutoClicada();
                    }
                    if (posicaoAtual == 0) {

                        jtProdutos.setRowSelectionInterval(posicaoAtual, posicaoAtual);
                        tabelaProdutoClicada();
                    }

                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (posicaoAtual < jtProdutos.getRowCount()) {

                        jtProdutos.setRowSelectionInterval(posicaoAtual, posicaoAtual);
                        tabelaProdutoClicada();

                    }

                }
            }
        });

        //Preparar Botoes 
        //Salvar
        jbtnSalvar.addActionListener(
                (e) -> {
                    btnSalvarProdutoClicado();
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

        //combo de unidade de medida
        jcbUnidadeMedida.addActionListener(
                (e) -> {
                    itemComboSelecionado();
                }
        );

        //jdialogProduto
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

        preencheComboUnidadeMedida();
    }

    private boolean verificarComponentesPreenchidos(){
        
        
        
        if(!jtfNome.getText().equalsIgnoreCase("")){
            return true;
        }
        
        if(!jtfPreco.getText().equalsIgnoreCase("")){
            return true;
        }
        
        if(!jtfQtdMinima.getText().equalsIgnoreCase("")){
            return true;
        }
        
        if(jcbControladoPorLote.isSelected()){
            return true;
        }
        
        if(!(jcbUnidadeMedida.getSelectedIndex() == 0)){
            return true;
        }
        
        return false;
    }
    
    private void itemComboSelecionado() {

        for (UnidadeMedida unidadeMedida : unidadesMedida) {

            if (unidadeMedida.getNome().equals(this.jcbUnidadeMedida.getSelectedItem().toString())) {
                unidadeMedidaSelecionada = unidadeMedida;
            }
        }

    }

    private void preencheComboUnidadeMedida() {
        try {
            unidadesMedida = ControladorUnidadeMedida.selecionarTodasUnidadesMedida();

            for (UnidadeMedida unidadeMedida : unidadesMedida) {
                this.jcbUnidadeMedida.addItem(unidadeMedida.getNome());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possível preencher a lista de unidades de medida!", "Erro!", 0);
        }
    }

    /**
     * Atualiza as linhas da tabela de armazéns com todos os armazéns
     * cadastrados na base.
     */
    private void atualizarTabelaProdutos() {
        modeloTabelaProduto = new ProdutoTableModel(getProdutos());
        jtProdutos.setModel(modeloTabelaProduto);
    }

    /**
     * Método invocado quando alguma linha da tabela de produto é selecionada.
     */
    private void tabelaProdutoClicada() {
        this.produtoAlterarExcluir = new Produto();
        this.produtoAlterarExcluir = procurarProdutoNaLista(Integer.parseInt("" + modeloTabelaProduto.getValueAt(jtProdutos.getSelectedRow(), 0)));

        jtfNome.setText(produtoAlterarExcluir.getNome());

        if (produtoAlterarExcluir.isControladoPorLote()) {

            jcbControladoPorLote.setSelected(true);

        } else {

            jcbControladoPorLote.setSelected(false);
        }
        jtfQtdMinima.setText(Double.toString(produtoAlterarExcluir.getQuantidadeMinima()));
        jtfPreco.setText(Double.toString(produtoAlterarExcluir.getPreco()));
        jcbUnidadeMedida.setSelectedItem(produtoAlterarExcluir.getUnidadeMedida().getNome());

    }

    /**
     * Método invocado quando o botão Limpar é clicado.
     */
    private void btnLimparClicado() {
        this.jtfNome.setText("");
        this.jtfQtdMinima.setText("");
        this.jtfPreco.setText("");
        this.jcbUnidadeMedida.setSelectedIndex(0);
        this.jcbControladoPorLote.setSelected(false);
        produtoAlterarExcluir = null;

    }

    /**
     * Método invocado quando o botão Excluir é clicado.
     */
    private void btnExcluirClicado() {

        try {

            if (!this.jtfNome.getText().equalsIgnoreCase("") && !this.jtfPreco.getText().equalsIgnoreCase("")) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir este item?", "Confirmação de exclusão", JOptionPane.YES_NO_OPTION, 3)) {
                    ControladorProduto.deletarProduto(produtoAlterarExcluir);
                    atualizarTabelaProdutos();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um produto para realizar a exclusão!", "Atenção!", 2);
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
    private void btnSalvarProdutoClicado() {

        try {

            if (!this.jtfNome.getText().equalsIgnoreCase("") && !this.jtfPreco.getText().equalsIgnoreCase("") && !(this.jcbUnidadeMedida.getSelectedIndex() == 0)) {
                if (produtoAlterarExcluir == null) {

                    //produto = procurarProdutoNaLista(produto.getDescricao());
                    ControladorProduto.inserirProduto(new Produto(this.jtfNome.getText(),
                            jcbControladoPorLote.isSelected(),
                            Double.parseDouble(this.jtfQtdMinima.getText()),
                            Double.parseDouble(this.jtfPreco.getText()),
                            unidadeMedidaSelecionada));

                } else {

                    produtoAlterarExcluir.setControladoPorLote(this.jcbControladoPorLote.isSelected());
                    produtoAlterarExcluir.setNome(this.jtfNome.getText());
                    produtoAlterarExcluir.setPreco(Double.parseDouble(this.jtfPreco.getText()));
                    produtoAlterarExcluir.setQuantidadeMinima(Double.parseDouble(this.jtfQtdMinima.getText()));
                    produtoAlterarExcluir.setUnidadeMedida(unidadeMedidaSelecionada);

                    ControladorProduto.updateProdutoPorCodigo(produtoAlterarExcluir);
                }
                atualizarTabelaProdutos();
            } else {
                JOptionPane.showMessageDialog(null, "O produto deve ter uma descrição, preço e unidade de medida especificados!", "Atenção!", 2);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FRMCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }

        btnLimparClicado();

    }

    /**
     * Método procura um produto na base de dados. É necessário informar a
     * descrição do armazém por parâmetro.
     *
     * @param descricao
     * @return
     */
    private Produto procurarProdutoNaLista(int codigo) {

        for (Produto produto : produtos) {
            if (produto.getCodigo() == (codigo)) {
                produtoAlterarExcluir = produto;
                return produto;
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

        bgControleLote = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProdutos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jlNome = new javax.swing.JLabel();
        jtfNome = new javax.swing.JTextField();
        jlQtdMinima = new javax.swing.JLabel();
        jtfQtdMinima = new javax.swing.JTextField();
        jlPreco = new javax.swing.JLabel();
        jtfPreco = new javax.swing.JTextField();
        jlUnidadeMedida = new javax.swing.JLabel();
        jcbUnidadeMedida = new javax.swing.JComboBox<>();
        jbtnSalvar = new javax.swing.JButton();
        jbtnLimpar = new javax.swing.JButton();
        jbtnExcluir = new javax.swing.JButton();
        jcbControladoPorLote = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Cadastro/Edição de Produtos");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(584, 443));
        setPreferredSize(new java.awt.Dimension(806, 448));

        jScrollPane1.setName(""); // NOI18N
        jScrollPane1.setPreferredSize(new java.awt.Dimension(100, 200));

        jtProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtProdutos.setToolTipText("Tabela de produtos");
        jtProdutos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtProdutos);

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

        jlNome.setText("Nome:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jlNome, gridBagConstraints);

        jtfNome.setToolTipText("Informe o nome/descrição do produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 19;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 380;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jtfNome, gridBagConstraints);

        jlQtdMinima.setText("Quantidade Mínima:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(jlQtdMinima, gridBagConstraints);

        jtfQtdMinima.setToolTipText("Informe a quantidade mínima");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.ipadx = 137;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.6;
        jPanel1.add(jtfQtdMinima, gridBagConstraints);

        jlPreco.setText("Preço:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jlPreco, gridBagConstraints);

        jtfPreco.setToolTipText("Informe o preço do produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.ipadx = 137;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jtfPreco, gridBagConstraints);

        jlUnidadeMedida.setText("Unidade de Medida:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        jPanel1.add(jlUnidadeMedida, gridBagConstraints);

        jcbUnidadeMedida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        jcbUnidadeMedida.setToolTipText("Selecione uma unidade de medida");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.ipadx = 250;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.3;
        jPanel1.add(jcbUnidadeMedida, gridBagConstraints);

        jbtnSalvar.setText("Salvar");
        jbtnSalvar.setToolTipText("Salvar/Atualizar produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 3;
        jPanel1.add(jbtnSalvar, gridBagConstraints);

        jbtnLimpar.setText("Limpar");
        jbtnLimpar.setToolTipText("Novo produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(jbtnLimpar, gridBagConstraints);

        jbtnExcluir.setText("Excluir");
        jbtnExcluir.setToolTipText("Excluir produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 18;
        jPanel1.add(jbtnExcluir, gridBagConstraints);

        jcbControladoPorLote.setText("Controlado por lote");
        jcbControladoPorLote.setToolTipText("Produto controlado por lote S/N");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 5;
        jPanel1.add(jcbControladoPorLote, gridBagConstraints);

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
            java.util.logging.Logger.getLogger(FRMCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRMCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRMCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRMCadastroProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FRMCadastroProduto dialog = new FRMCadastroProduto(new javax.swing.JFrame(), true);
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
    private javax.swing.JCheckBox jcbControladoPorLote;
    private javax.swing.JComboBox<String> jcbUnidadeMedida;
    private javax.swing.JLabel jlNome;
    private javax.swing.JLabel jlPreco;
    private javax.swing.JLabel jlQtdMinima;
    private javax.swing.JLabel jlUnidadeMedida;
    private javax.swing.JTable jtProdutos;
    private javax.swing.JTextField jtfNome;
    private javax.swing.JTextField jtfPreco;
    private javax.swing.JTextField jtfQtdMinima;
    // End of variables declaration//GEN-END:variables
}
