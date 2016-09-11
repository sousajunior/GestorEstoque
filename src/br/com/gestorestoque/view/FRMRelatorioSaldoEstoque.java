/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view;

import br.com.gestorestoque.controller.ControladorArmazem;
import br.com.gestorestoque.controller.ControladorProdutoArmazenado;
import br.com.gestorestoque.model.Armazem;
import br.com.gestorestoque.model.ProdutoArmazenado;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Carlinhos
 */
public class FRMRelatorioSaldoEstoque extends javax.swing.JDialog {

    List<ProdutoArmazenado> produtosArmazenados = new ArrayList<>();
    List<Armazem> armazens = new ArrayList<>();
    List<Armazem> armazensPesquisa = new ArrayList<>();
    TableModel modeloTabelaProdutoArmazenado;

    /**
     * Creates new form FRMRelatorioSaldosEstoque
     */
    public FRMRelatorioSaldoEstoque(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        prepararComponentes();
    }

    /**
     * Executa o método ControladorProduto.selecionarTodosArmazens() da classe
     * ControladorProduto. Este método retorna uma lista com todos os armazéns
     * cadastrados na base de dados.
     *
     * @return
     */
    private List<ProdutoArmazenado> getProdutosArmazenados() {

        produtosArmazenados = new ArrayList<>();
        try {
            produtosArmazenados = ControladorProdutoArmazenado.selecionarTodosProdutosArmazenados();
            return produtosArmazenados;
        } catch (SQLException ex) {
            Logger.getLogger(FRMCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Executa o método ControladorProduto.selecionarTodosArmazens() da classe
     * ControladorProduto. Este método retorna uma lista com todos os armazéns
     * cadastrados na base de dados.
     *
     * @return
     */
    private List<Armazem> getArmazens() {

        armazens = new ArrayList<>();
        try {
            armazens = ControladorArmazem.selecionarTodosArmazens();
            return armazens;
        } catch (Exception ex) {
            Logger.getLogger(FRMRelatorioSaldoEstoque.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Classe interna que define o TableModel da tabela de Armazém.
     */
    private class ProdutoArmazenadoTableModel extends AbstractTableModel {

        public final List<ProdutoArmazenado> produtosProdutoArmazenados2;

        public ProdutoArmazenadoTableModel(List<ProdutoArmazenado> produtosProdutoArmazenadosList) {
            this.produtosProdutoArmazenados2 = produtosProdutoArmazenadosList;

        }

        /**
         * Retorna a quantidade de linhas existentes na TableModel
         *
         * @return
         */
        @Override
        public int getRowCount() {
            return produtosArmazenados.size();
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

            if (rowIndex < 0 | rowIndex >= produtosArmazenados.size()) {
                return null;

            }

            ProdutoArmazenado produtoArmazenado = produtosArmazenados.get(rowIndex);

            if (columnIndex == 0) {
                return produtoArmazenado.getLote();
            }

            if (columnIndex == 1) {
                return produtoArmazenado.getProduto().getNome();
            }

            if (columnIndex == 2) {
                return produtoArmazenado.getFornecedor().getNome();
            }

            if (columnIndex == 3) {

                return produtoArmazenado.getQuantidade();

            }

            if (columnIndex == 4) {

                return produtoArmazenado.getNotaFiscal();

            }

            if (columnIndex == 5) {

                return produtoArmazenado.getArmazem().getDescricao();

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
                return "Lote";
            }

            if (column == 1) {

                return "Produto";
            }

            if (column == 2) {
                return "Fornecedor";
            }

            if (column == 3) {

                return "Saldo";

            }

            if (column == 4) {

                return "Nota Fiscal";
            }

            if (column == 5) {

                return "Armazem";
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
        atualizarTabelaProdutosArmazenados();
//
//        //jtProdutos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//        jtProdutos.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                tabelaProdutoClicada();
//            }
//        });
//
//        jtProdutos.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent e) {
//                int posicaoAtual = jtProdutos.getSelectedRow();
//
//                if (e.getKeyCode() == KeyEvent.VK_UP) {
//                    if (posicaoAtual >= 1) {
//
//                        jtProdutos.setRowSelectionInterval(posicaoAtual, posicaoAtual);
//                        tabelaProdutoClicada();
//                    }
//                    if (posicaoAtual == 0) {
//
//                        jtProdutos.setRowSelectionInterval(posicaoAtual, posicaoAtual);
//                        tabelaProdutoClicada();
//                    }
//
//                }
//                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
//                    if (posicaoAtual < jtProdutos.getRowCount()) {
//
//                        jtProdutos.setRowSelectionInterval(posicaoAtual, posicaoAtual);
//                        tabelaProdutoClicada();
//
//                    }
//
//                }
//            }
//        });
//        });
//
//        //Preparar Botoes 
//        //Salvar
//        jbtnSalvar.addActionListener(
//                (e) -> {
//                    btnSalvarProdutoClicado();
//                }
//        );
//
        //Limpar
        jbtLimparCamposPesquisa.addActionListener(
                (e) -> {
                    btnLimparClicado();
                }
        );
//
//        //excluir
//        jbtnExcluir.addActionListener(
//                (e) -> {
//                    btnExcluirClicado();
//                }
//        );
//
        //combo de condição para lote
        jcbCondicaoLote.addActionListener(
                (e) -> {
                    itemComboCondicaoLoteSelecionado();
                }
        );
        
        //combo de condição para produto
        jcbCondicaoProduto.addActionListener(
                (e) -> {
                    itemComboCondicaoProdutoSelecionado();
                }
        );
        
        //combo de condição para fornecedor
        jcbCondicaoFornecedor.addActionListener(
                (e) -> {
                    itemComboCondicaoFornecedorSelecionado();
                }
        );
        
        //combo de condição para Saldo
        jcbCondicaoSaldo.addActionListener(
                (e) -> {
                    itemComboCondicaoSaldoSelecionado();
                }
        );
        
        //combo de condição para Nota fiscal
        jcbCondicaoNotaFiscal.addActionListener(
                (e) -> {
                    itemComboCondicaoNotaFiscalSelecionado();
                }
        );
        
        
        //combo de condição para armazem
        jcbCondicaoArmazem.addActionListener(
                (e) -> {
                    itemComboCondicaoArmazemSelecionado();
                }
        );
//
//        //jdialogProduto
//        this.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                if(verificarComponentesPreenchidos()){
//                    if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Há itens que não foram salvos!\n Deseja mesmo sair?", "Fechar", JOptionPane.YES_NO_OPTION, 3)){
//                        dispose();
//                    }
//                }else{
//                    dispose();
//                }
//            }
//            
//            
//            
//        });
//
//        preencheComboUnidadeMedida();
    }

    private void btnLimparClicado() {

        this.jcbCondicaoLote.setSelectedIndex(0);
        this.jcbCondicaoProduto.setSelectedIndex(0);
        this.jcbCondicaoFornecedor.setSelectedIndex(0);
        this.jcbCondicaoSaldo.setSelectedIndex(0);
        this.jcbCondicaoArmazem.setSelectedIndex(0);
        this.jcbCondicaoNotaFiscal.setSelectedIndex(0);
        this.jcControladoPorLote.setSelected(false);

    }

    private void itemComboCondicaoLoteSelecionado() {

        if (this.jcbCondicaoLote.getSelectedIndex() == 0) {
            this.jtfLote.setText("");
            this.jtfLote.setEnabled(false);
        } else {
            this.jtfLote.setEnabled(true);
        }

    }
    
    private void itemComboCondicaoProdutoSelecionado() {

        if (this.jcbCondicaoProduto.getSelectedIndex() == 0) {
            this.jtfProduto.setText("");
            this.jtfProduto.setEnabled(false);
        } else {
            this.jtfProduto.setEnabled(true);
        }

    }
    
    private void itemComboCondicaoFornecedorSelecionado() {

        if (this.jcbCondicaoFornecedor.getSelectedIndex() == 0) {
            this.jtfFornecedor.setText("");
            this.jtfFornecedor.setEnabled(false);
        } else {
            this.jtfFornecedor.setEnabled(true);
        }

    }
    
    private void itemComboCondicaoSaldoSelecionado() {

        if (this.jcbCondicaoSaldo.getSelectedIndex() == 0) {
            this.jtfSaldo.setText("");
            this.jtfSaldo.setEnabled(false);
        } else {
            this.jtfSaldo.setEnabled(true);
        }

    }
    
    
    private void itemComboCondicaoNotaFiscalSelecionado() {

        if (this.jcbCondicaoNotaFiscal.getSelectedIndex() == 0) {
            this.jtfNotaFiscal.setText("");
            this.jtfNotaFiscal.setEnabled(false);
        } else {
            this.jtfNotaFiscal.setEnabled(true);
        }

    }
    
    
    private void itemComboCondicaoArmazemSelecionado() {

        if (this.jcbCondicaoArmazem.getSelectedIndex() == 0) {
            this.jtfArmazem.setText("");
            this.jtfArmazem.setEnabled(false);
        } else {
            this.jtfArmazem.setEnabled(true);
        }

    }

    /**
     * Atualiza as linhas da tabela de armazéns com todos os armazéns
     * cadastrados na base.
     */
    private void atualizarTabelaProdutosArmazenados() {
        modeloTabelaProdutoArmazenado = new ProdutoArmazenadoTableModel(getProdutosArmazenados());
        jtProdutosArmazenados.setModel(modeloTabelaProdutoArmazenado);
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

        jpBaixo = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtProdutosArmazenados = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jpPesquisa = new javax.swing.JPanel();
        jlbLote = new javax.swing.JLabel();
        jtfLote = new javax.swing.JTextField();
        jlbSaldo = new javax.swing.JLabel();
        Produto = new javax.swing.JLabel();
        jtfProduto = new javax.swing.JTextField();
        jlbNotaFiscal = new javax.swing.JLabel();
        jtfNotaFiscal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfFornecedor = new javax.swing.JTextField();
        jbtLimparCamposPesquisa = new javax.swing.JButton();
        jcbCondicaoLote = new javax.swing.JComboBox<>();
        jcbCondicaoSaldo = new javax.swing.JComboBox<>();
        jcbCondicaoProduto = new javax.swing.JComboBox<>();
        jcbCondicaoNotaFiscal = new javax.swing.JComboBox<>();
        jcbCondicaoFornecedor = new javax.swing.JComboBox<>();
        jcControladoPorLote = new javax.swing.JCheckBox();
        jtfArmazem = new javax.swing.JTextField();
        jcbCondicaoArmazem = new javax.swing.JComboBox<>();
        jlArmazem = new javax.swing.JLabel();
        jtfSaldo = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jbtEntradaProduto = new javax.swing.JButton();
        jtbInventario = new javax.swing.JButton();
        jbtSaidaProduto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Saldos em estoque");
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jpBaixo.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(80, 300));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(80, 300));

        jtProdutosArmazenados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtProdutosArmazenados.setToolTipText("Tabela de saldo dos produtos");
        jtProdutosArmazenados.setMaximumSize(new java.awt.Dimension(300, 200));
        jtProdutosArmazenados.setMinimumSize(new java.awt.Dimension(300, 200));
        jtProdutosArmazenados.setPreferredSize(new java.awt.Dimension(300, 200));
        jScrollPane1.setViewportView(jtProdutosArmazenados);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(22, 11, 15, 11);
        jpBaixo.add(jScrollPane1, gridBagConstraints);

        getContentPane().add(jpBaixo, java.awt.BorderLayout.PAGE_END);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jpPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisar"));
        java.awt.GridBagLayout jpPesquisaLayout = new java.awt.GridBagLayout();
        jpPesquisaLayout.columnWidths = new int[] {0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0};
        jpPesquisaLayout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jpPesquisa.setLayout(jpPesquisaLayout);

        jlbLote.setText("Lote");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jpPesquisa.add(jlbLote, gridBagConstraints);

        jtfLote.setToolTipText("Informe um lote para a pesquisa");
        jtfLote.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jtfLote, gridBagConstraints);

        jlbSaldo.setText("Saldo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jpPesquisa.add(jlbSaldo, gridBagConstraints);

        Produto.setText("Produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 24;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jpPesquisa.add(Produto, gridBagConstraints);

        jtfProduto.setToolTipText("Informe a descrição de um produto para a pesquisa");
        jtfProduto.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 24;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 27;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jtfProduto, gridBagConstraints);

        jlbNotaFiscal.setText("Nota Fiscal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 24;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jpPesquisa.add(jlbNotaFiscal, gridBagConstraints);

        jtfNotaFiscal.setToolTipText("Informe uma nota fiscal para a pesquisa");
        jtfNotaFiscal.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 24;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jtfNotaFiscal, gridBagConstraints);

        jLabel5.setText("Fornecedor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 52;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jpPesquisa.add(jLabel5, gridBagConstraints);

        jtfFornecedor.setToolTipText("Informe um fornecedor para a pesquisa");
        jtfFornecedor.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 52;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jtfFornecedor, gridBagConstraints);

        jbtLimparCamposPesquisa.setText("Limpar");
        jbtLimparCamposPesquisa.setToolTipText("Limpar campos de pesquisa");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 68;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jbtLimparCamposPesquisa, gridBagConstraints);

        jcbCondicaoLote.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Contém a expressão", "Igual a" }));
        jcbCondicaoLote.setToolTipText("Selecione um filtro para lote");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jcbCondicaoLote, gridBagConstraints);

        jcbCondicaoSaldo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Igual a", "É maior", "É maior ou igual", "É menor", "É menor ou igual" }));
        jcbCondicaoSaldo.setToolTipText("Selecione um filtro para saldo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jcbCondicaoSaldo, gridBagConstraints);

        jcbCondicaoProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Contém a expressão", "Igual a" }));
        jcbCondicaoProduto.setToolTipText("Selecione um filtro para produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 24;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 27;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jcbCondicaoProduto, gridBagConstraints);

        jcbCondicaoNotaFiscal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Contém a expressão", "Igual a" }));
        jcbCondicaoNotaFiscal.setToolTipText("Selecione um filtro para nota fiscal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 24;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jcbCondicaoNotaFiscal, gridBagConstraints);

        jcbCondicaoFornecedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Contém a expressão", "Igual a" }));
        jcbCondicaoFornecedor.setToolTipText("Selecione um filtro para fornecedor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 52;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jcbCondicaoFornecedor, gridBagConstraints);

        jcControladoPorLote.setText("Controlado por lote");
        jcControladoPorLote.setToolTipText("Selecione filtro para produtos controlados por lote");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 42;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 7;
        jpPesquisa.add(jcControladoPorLote, gridBagConstraints);

        jtfArmazem.setToolTipText("Informe um filtro para armazem");
        jtfArmazem.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 52;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jtfArmazem, gridBagConstraints);

        jcbCondicaoArmazem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Contém a expressão", "Igual a" }));
        jcbCondicaoArmazem.setToolTipText("Selecione um filtro para armazém");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 52;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jcbCondicaoArmazem, gridBagConstraints);

        jlArmazem.setText("Armazém");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 52;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jpPesquisa.add(jlArmazem, gridBagConstraints);

        jtfSaldo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jtfSaldo.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jtfSaldo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = -3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 4, 16, 4);
        jPanel1.add(jpPesquisa, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 12, 0, 12, 0};
        jPanel2Layout.rowHeights = new int[] {0};
        jPanel2.setLayout(jPanel2Layout);

        jbtEntradaProduto.setText("Entrada de produto");
        jbtEntradaProduto.setToolTipText("Ir para tela de entrada de produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(21, 21, 21, 21);
        jPanel2.add(jbtEntradaProduto, gridBagConstraints);

        jtbInventario.setText("Inventário");
        jtbInventario.setToolTipText("Ir para a tela de inventário");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(21, 21, 21, 21);
        jPanel2.add(jtbInventario, gridBagConstraints);

        jbtSaidaProduto.setText("Saída de produto");
        jbtSaidaProduto.setToolTipText("Ir para tela de saída de produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(21, 21, 21, 21);
        jPanel2.add(jbtSaidaProduto, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);
        jPanel2.getAccessibleContext().setAccessibleParent(this);

        setSize(new java.awt.Dimension(805, 675));
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
            java.util.logging.Logger.getLogger(FRMRelatorioSaldoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRMRelatorioSaldoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRMRelatorioSaldoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRMRelatorioSaldoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FRMRelatorioSaldoEstoque dialog = new FRMRelatorioSaldoEstoque(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel Produto;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtEntradaProduto;
    private javax.swing.JButton jbtLimparCamposPesquisa;
    private javax.swing.JButton jbtSaidaProduto;
    private javax.swing.JCheckBox jcControladoPorLote;
    private javax.swing.JComboBox<String> jcbCondicaoArmazem;
    private javax.swing.JComboBox<String> jcbCondicaoFornecedor;
    private javax.swing.JComboBox<String> jcbCondicaoLote;
    private javax.swing.JComboBox<String> jcbCondicaoNotaFiscal;
    private javax.swing.JComboBox<String> jcbCondicaoProduto;
    private javax.swing.JComboBox<String> jcbCondicaoSaldo;
    private javax.swing.JLabel jlArmazem;
    private javax.swing.JLabel jlbLote;
    private javax.swing.JLabel jlbNotaFiscal;
    private javax.swing.JLabel jlbSaldo;
    private javax.swing.JPanel jpBaixo;
    private javax.swing.JPanel jpPesquisa;
    private javax.swing.JTable jtProdutosArmazenados;
    private javax.swing.JButton jtbInventario;
    private javax.swing.JTextField jtfArmazem;
    private javax.swing.JTextField jtfFornecedor;
    private javax.swing.JTextField jtfLote;
    private javax.swing.JTextField jtfNotaFiscal;
    private javax.swing.JTextField jtfProduto;
    private javax.swing.JFormattedTextField jtfSaldo;
    // End of variables declaration//GEN-END:variables
}
