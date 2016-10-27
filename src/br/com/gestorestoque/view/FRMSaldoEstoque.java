/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view;

import br.com.gestorestoque.controller.ControladorArmazem;
import br.com.gestorestoque.controller.ControladorProduto;
import br.com.gestorestoque.controller.ControladorProdutoArmazenado;
import br.com.gestorestoque.controller.FiltroComposite;
import br.com.gestorestoque.controller.FiltroDescricaoProduto;
import br.com.gestorestoque.controller.FiltroFornecedor;
import br.com.gestorestoque.controller.FiltroLote;
import br.com.gestorestoque.controller.FiltroNotaFiscal;
import br.com.gestorestoque.controller.FiltroUnidadeMedida;
import br.com.gestorestoque.model.Armazem;
import br.com.gestorestoque.model.Fornecedor;
import br.com.gestorestoque.model.Produto;
import br.com.gestorestoque.model.ProdutoArmazenado;
import br.com.gestorestoque.view.enumerado.Relatorio;
import br.com.gestorestoque.view.enumerado.TipoRelatorio;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author Carlinhos
 */
public class FRMSaldoEstoque extends javax.swing.JDialog {

    List<ProdutoArmazenado> produtosArmazenados = new ArrayList<>();
    List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
    List<Produto> produtos = new ArrayList<>();
    List<Armazem> armazens = new ArrayList<>();
    List<Armazem> armazensPesquisa = new ArrayList<>();
    TableModel modeloTabelaProdutoArmazenado;
    ProdutoArmazenado produtoArmazenadoPesquisa;
    ControladorArmazem ctrlArmazem;
    ControladorProdutoArmazenado ctrlProdutoArmazenado;
    ControladorProduto ctrlProduto;
    List<Produto> produtosQtdMinima = new ArrayList<>();
    //Strings de pesquisa

    Boolean somentePesquisa;

    /**
     * Creates new form FRMRelatorioSaldosEstoque
     *
     * @param parent
     * @param modal
     */
    public FRMSaldoEstoque(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.ctrlArmazem = new ControladorArmazem();
        this.ctrlProdutoArmazenado = new ControladorProdutoArmazenado();
        this.ctrlProduto = new ControladorProduto();
        initComponents();
        somentePesquisa = false;
        prepararComponentes();
    }

    /**
     * Creates new form FRMRelatorioSaldosEstoque
     *
     * @param parent
     * @param modal
     */
    public FRMSaldoEstoque(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        this.ctrlArmazem = new ControladorArmazem();
        this.ctrlProdutoArmazenado = new ControladorProdutoArmazenado();
        initComponents();
        somentePesquisa = true;
        this.jScrollPane1.setAutoscrolls(true);
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
            produtosArmazenados = ctrlProdutoArmazenado.selecionarTodos();
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
    private List<Produto> getProdutos() {

        produtos = new ArrayList<>();
        try {
            ctrlProduto = new ControladorProduto();
            produtos = ctrlProduto.selecionarTodos();
            return produtos;
        } catch (SQLException ex) {
            Logger.getLogger(FRMCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Classe interna que define o TableModel da tabela de Armazém.
     */
    private class ProdutoArmazenadoTableModel extends AbstractTableModel {

        public final List<ProdutoArmazenado> produtosArmazenados;

        public ProdutoArmazenadoTableModel(List<ProdutoArmazenado> produtosProdutoArmazenadosList) {
            this.produtosArmazenados = produtosProdutoArmazenadosList;

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
            return 10;
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
                return produtoArmazenado.getCodigo();
            }

            if (columnIndex == 1) {
                return produtoArmazenado.getLote();
            }

            if (columnIndex == 2) {
                return produtoArmazenado.getProduto().getNome();
            }

            if (columnIndex == 3) {
                return produtoArmazenado.getFornecedor().getNome();

            }

            if (columnIndex == 4) {
                return produtoArmazenado.getQuantidade();

            }

            if (columnIndex == 5) {
                return produtoArmazenado.getProduto().getUnidadeMedida().getAbreviacao();

            }

            if (columnIndex == 6) {

                if (produtoArmazenado.getProduto().isControladoPorLote()) {
                    return "S";
                } else {
                    return "N";
                }

            }

            if (columnIndex == 7) {

                return produtoArmazenado.getArmazem().getDescricao();

            }

            if (columnIndex == 8) {

                return produtoArmazenado.getNotaFiscal();

            }
            if (columnIndex == 9) {

                return produtoArmazenado.getProduto().getPreco();

            }

            if (columnIndex == 10) {

                return produtoArmazenado;

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

                return "Lote";
            }

            if (column == 2) {
                return "Produto";
            }

            if (column == 3) {
                return "Fornecedor";

            }

            if (column == 4) {

                return "Saldo";
            }

            if (column == 5) {

                return "Unidade de medida";
            }
            if (column == 6) {

                return "Controlado por lote";
            }

            if (column == 7) {

                return "Armazem";
            }

            if (column == 8) {

                return "Nota Fiscal";
            }

            if (column == 9) {

                return "Preço";
            }

            if (column == 10) {

                return "Produto Armazenado";
            }

            return null;
        }

    }

    private class qtdMinimaRender extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, column);
            if (column == 4) {
                ProdutoArmazenado p = (ProdutoArmazenado) modeloTabelaProdutoArmazenado.getValueAt(row, 10);
                c = super.getTableCellRendererComponent(table, p.getQuantidade(), isSelected,
                        hasFocus, row, column);
                for (Produto produto : produtosQtdMinima) {

                    if (produto.getCodigo() == p.getProduto().getCodigo()) {
                        //if (Double.parseDouble(value.toString()) <= p.getProduto().getQuantidadeMinima()) {
                        c.setForeground(Color.red);
                        //}
                    }
                }
            } else {
                c.setForeground(Color.black);
            }
            return c;
        }
    }

    /**
     * Prepara os componentes da tela para o uso da mesma. O preparo dos
     * componentes envolve adição de listeners
     */
    public void prepararComponentes() {

        //Preparar Jtable de produtos
        getProdutosArmazenados();
        atualizarListaProdutosQuantidadeMinima();
        atualizarTabelaProdutosArmazenados();

        jtProdutosArmazenados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    if (somentePesquisa) {
                        produtoArmazenadoPesquisa = new ProdutoArmazenado(Integer.parseInt("" + modeloTabelaProdutoArmazenado.getValueAt(jtProdutosArmazenados.getSelectedRow(), 0)),
                                "" + modeloTabelaProdutoArmazenado.getValueAt(jtProdutosArmazenados.getSelectedRow(), 1),
                                Double.parseDouble("" + modeloTabelaProdutoArmazenado.getValueAt(jtProdutosArmazenados.getSelectedRow(), 4)),
                                Integer.parseInt("" + modeloTabelaProdutoArmazenado.getValueAt(jtProdutosArmazenados.getSelectedRow(), 8)),
                                procurarProduto("" + modeloTabelaProdutoArmazenado.getValueAt(jtProdutosArmazenados.getSelectedRow(), 2)),
                                procurarFornecedor("" + modeloTabelaProdutoArmazenado.getValueAt(jtProdutosArmazenados.getSelectedRow(), 3)),
                                procurarArmazem("" + modeloTabelaProdutoArmazenado.getValueAt(jtProdutosArmazenados.getSelectedRow(), 7)));

                        dispose();
                    }
                }
            }
        });

        //Relatório
        jbtGerarRelatorio.addActionListener((e) -> {
            try {
                this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                btnGerarRelatorioClicado();
                this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } catch (SQLException ex) {
                Logger.getLogger(FRMSaldoEstoque.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        );
        //Limpar
        jbtLimparCamposPesquisa.addActionListener(
                (e) -> {
                    btnLimparClicado();
                }
        );

        jcbTipoRelatorio.addActionListener(
                (e) -> {
                    itemComboTipoRelatorioSelecionado();
                }
        );

        //entrada de produto
        jbtEntradaProduto.addActionListener(
                (e) -> {
                    btnEntradaProdutoClicado();
                }
        );

        //inventario
        jbtnventario.addActionListener(
                (e) -> {
                    btnInventarioClicado();
                }
        );

        //pesquisar
        jbtPesquisar.addActionListener(
                (e) -> {
                    btnPesquisarClicado();
                }
        );

        //saida de produto
        jbtSaidaProduto.addActionListener(
                (e) -> {
                    btnSaidaProdutoClicado();
                }
        );

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

        //combo de condição para unidade de medida
        jcbCondicaoUnidadeMedida.addActionListener(
                (e) -> {
                    itemComboCondicaoUnidadeMedidaSelecionado();
                }
        );

        //combo de condição para preco
        jcbCondicaoPreco.addActionListener(
                (e) -> {
                    itemComboCondicaoPrecoSelecionado();
                }
        );

        //listener do campo de lote
        jtfLote.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    filtrar();
                }

            }

        });

        //listener do campo de produto
        jtfProduto.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    filtrar();
                }

            }

        });

        //listener do campo de fornecedor
        jtfFornecedor.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    filtrar();
                }

            }

        });

        //listener do campo de quantidade(saldo)
        jtfSaldo.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    filtrar();
                }

            }

        });

        //listener do campo de notafiscal
        jtfNotaFiscal.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    filtrar();
                }

            }

        });

        //listener do campo de unidade de medida
        jtfUnidadeMedida.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    filtrar();
                }

            }

        });

        //listener do campo de armazem
        jtfArmazem.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    filtrar();
                }

            }

        });

        //listener do campo de preço
        jtfPreco.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    filtrar();
                }

            }

        });

    }

    private void itemComboTipoRelatorioSelecionado() {

        if (this.jcbTipoRelatorio.getSelectedIndex() == 0) {
            this.jbtGerarRelatorio.setEnabled(false);
        } else {
            this.jbtGerarRelatorio.setEnabled(true);
        }

    }

    private void btnGerarRelatorioClicado() throws SQLException {

        if (modeloTabelaProdutoArmazenado.getRowCount() > 0) {
            String codigosProdutosArmazenados = "";
            for (int i = 0; i < modeloTabelaProdutoArmazenado.getRowCount(); i++) {
                codigosProdutosArmazenados += " " + modeloTabelaProdutoArmazenado.getValueAt(i, 0) + ",";
                // System.out.println(" "+modeloTabelaProdutoArmazenado.getValueAt(jtProdutosArmazenados.getSelectedRow(), 0) + ",");
                // System.out.println(codigosProdutosArmazenados);
            }
            if (codigosProdutosArmazenados.length() > 0) {

                if (this.jcbTipoRelatorio.getSelectedIndex() == 1) {
                    new FRMRelatorio(this,
                            true, codigosProdutosArmazenados.substring(0, codigosProdutosArmazenados.length() - 1),
                            Relatorio.RelatorioSaldoEstoque, TipoRelatorio.PDF).setVisible(true);
                }

                if (this.jcbTipoRelatorio.getSelectedIndex() == 2) {
                }

            }
        }

    }

    private void btnLimparClicado() {

        this.jcbCondicaoLote.setSelectedIndex(0);
        this.jcbCondicaoProduto.setSelectedIndex(0);
        this.jcbCondicaoFornecedor.setSelectedIndex(0);
        this.jcbCondicaoSaldo.setSelectedIndex(0);
        this.jcbCondicaoArmazem.setSelectedIndex(0);
        this.jcbCondicaoNotaFiscal.setSelectedIndex(0);
        this.jcbCondicaoPreco.setSelectedIndex(0);
        this.jcbCondicaoUnidadeMedida.setSelectedIndex(0);

        atualizarTabelaProdutosArmazenados();

        produtosArmazenadosPesquisa = new ArrayList<>();

    }

    private Produto procurarProduto(String produto) {

        Produto produtoEncontrado = new Produto();
        for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {
            if (produtosArmazenado.getProduto().getNome().equals(produto)) {
                return produtosArmazenado.getProduto();
            }
        }

        return produtoEncontrado;
    }

    private Fornecedor procurarFornecedor(String fornecedor) {
        Fornecedor fornecedorEncontrado = new Fornecedor();
        for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {
            if (fornecedor.equals(produtosArmazenado.getFornecedor().getNome())) {
                return produtosArmazenado.getFornecedor();
            }
        }

        return fornecedorEncontrado;
    }

    private Armazem procurarArmazem(String armazem) {
        Armazem armazemEncontrado = new Armazem();
        for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {
            if (armazem.equals(produtosArmazenado.getArmazem().getDescricao())) {
                return produtosArmazenado.getArmazem();
            }
        }

        return armazemEncontrado;
    }

    private void btnInventarioClicado() {

        new FRMInventario(this, true).setVisible(true);
        this.btnLimparClicado();

    }

    protected void btnPesquisarClicado() {

        produtosArmazenadosPesquisa = new ArrayList<>();
        filtrar();

    }

    private void btnEntradaProdutoClicado() {

        new FRMCadastroEntradaSaida(this, true, true).setVisible(true);
        this.btnLimparClicado();
    }

    private void btnSaidaProdutoClicado() {
        new FRMCadastroEntradaSaida(this, true, false).setVisible(true);
        this.btnLimparClicado();
    }

    private void itemComboCondicaoPrecoSelecionado() {

        if (this.jcbCondicaoPreco.getSelectedIndex() == 0) {
            this.jtfPreco.setText("");
            this.jtfPreco.setEnabled(false);
        } else {
            this.jtfPreco.setEnabled(true);
        }

    }

    private void itemComboCondicaoUnidadeMedidaSelecionado() {

        if (this.jcbCondicaoUnidadeMedida.getSelectedIndex() == 0) {
            this.jtfUnidadeMedida.setText("");
            this.jtfUnidadeMedida.setEnabled(false);
        } else {
            this.jtfUnidadeMedida.setEnabled(true);
        }

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

    private void filtrar() {

        /* produtosArmazenadosPesquisa = new ArrayList<>();
        //Filtro por lote
        //Verificar se o combo de condição por lote não está ocm o traço selecionado
        if (this.jcbCondicaoLote.getSelectedIndex() > 0) {

            //Verificar se o combo de condição por lote esta selecionando (Contém a expressão)
            if (this.jcbCondicaoLote.getSelectedIndex() == 1) {

                //Verificar se a lista de produto armazenado para pesquisa está vazia 
                if (produtosArmazenadosPesquisa.isEmpty()) {

                    //percorre a lista primária de produtos armazenados
                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        //verifica se o produto é controlado por lote
                        if (produtosArmazenado.getProduto().isControladoPorLote()) {

                            //verifica se o lote da posição atual contem a expressão que o usuário digitou
                            if (produtosArmazenado.getLote().toUpperCase().contains(jtfLote.getText().toUpperCase())) {

                                //adiciona na lista quando encontra um lote que contenha o que o usuário digitou
                                produtosArmazenadosPesquisa.add(produtosArmazenado);

                            }

                        }

                    }

                } else {

                    //Se a lista secundaria de produtos armazenados não for vazia, cria uma nova 
                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    //percorre a lista secundária de produtos armazenados que foi criada para pesquisa
                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        //verifica se o produto é controlado por lote
                        if (produtosArmazenado.getProduto().isControladoPorLote()) {

                            //verifica se o lote atual contem a expressão que o usuário digitou
                            if (produtosArmazenado.getLote().toUpperCase().contains(jtfLote.getText().toUpperCase())) {

                                //adiciona o item que encontrou na terceira lista
                                produtosArmazenadosPesquisa2.add(produtosArmazenado);

                            }

                        }

                    }

                    //remove os itens da segunda lista
                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);

                    //e atualiza com o que existe na terceira lista
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

                //quando o item (é igual a), e selecionado no combo de filtro por lote
            } else if (this.jcbCondicaoLote.getSelectedIndex() == 2) {

                //Verificar se a lista de produto armazenado para pesquisa está vazia 
                if (produtosArmazenadosPesquisa.isEmpty()) {

                    //percorre a lista primária de produtos armazenados
                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        //verifica se o produto é controlado por lote
                        if (produtosArmazenado.getProduto().isControladoPorLote()) {

                            //verifica se o lote atual é igual a expressão que o usuário digitou
                            if (produtosArmazenado.getLote().toUpperCase().equalsIgnoreCase(jtfLote.getText().toUpperCase())) {

                                //adiciona na lista quando encontra um lote que seja igual o que o usuário digitou
                                produtosArmazenadosPesquisa.add(produtosArmazenado);

                            }

                        }

                    }

                } else {

                    // se a lista de pesquisa nao estiver vazia
                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    //percorre a lista secundária de produtos armazenados que foi criada para pesquisa
                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        //verifica se o produto é controlado por lote
                        if (produtosArmazenado.getProduto().isControladoPorLote()) {

                            //verifica se o lote atual é igual a expressão que o usuário digitou
                            if (produtosArmazenado.getLote().toUpperCase().equalsIgnoreCase(jtfLote.getText().toUpperCase())) {

                                //adiciona o item que encontrou na terceira lista
                                produtosArmazenadosPesquisa2.add(produtosArmazenado);

                            }

                        }

                    }

                    //remove os itens da segunda lista
                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);

                    //e atualiza com o que existe na terceira lista
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            }

        }

        //----Produto
        //Filtro por produto
        //Verificar se o combo de condição por produto não está ocm o traço selecionado
        if (this.jcbCondicaoProduto.getSelectedIndex() > 0) {

            if (this.jcbCondicaoProduto.getSelectedIndex() == 1) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getProduto().getNome().toUpperCase().contains(jtfProduto.getText().toUpperCase())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getProduto().getNome().toUpperCase().contains(jtfProduto.getText().toUpperCase())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            } else if (this.jcbCondicaoProduto.getSelectedIndex() == 2) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getProduto().getNome().toUpperCase().equalsIgnoreCase(jtfProduto.getText().toUpperCase())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getProduto().getNome().toUpperCase().equalsIgnoreCase(jtfProduto.getText().toUpperCase())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            }

        }

        //----Unidade de medida
        //Filtro por unidade de medida
        //Verificar se o combo de condição por unidade de medida não está ocm o traço selecionado
        if (this.jcbCondicaoUnidadeMedida.getSelectedIndex() > 0) {

            if (this.jcbCondicaoUnidadeMedida.getSelectedIndex() == 1) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getProduto().getUnidadeMedida().getAbreviacao().toUpperCase().contains(jtfUnidadeMedida.getText().toUpperCase())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getProduto().getUnidadeMedida().getAbreviacao().toUpperCase().contains(jtfUnidadeMedida.getText().toUpperCase())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);

                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            } else if (this.jcbCondicaoUnidadeMedida.getSelectedIndex() == 2) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getProduto().getUnidadeMedida().getAbreviacao().toUpperCase().equalsIgnoreCase(jtfUnidadeMedida.getText().toUpperCase())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getProduto().getUnidadeMedida().getAbreviacao().toUpperCase().equalsIgnoreCase(jtfUnidadeMedida.getText().toUpperCase())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            }

        }

        //----Fornecedor
        //Filtro por fornecedor
        //Verificar se o combo de condição por produto não está ocm o traço selecionado
        if (this.jcbCondicaoFornecedor.getSelectedIndex() > 0) {

            if (this.jcbCondicaoFornecedor.getSelectedIndex() == 1) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getFornecedor().getNome().toUpperCase().contains(jtfFornecedor.getText().toUpperCase())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getFornecedor().getNome().toUpperCase().contains(jtfFornecedor.getText().toUpperCase())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            } else if (this.jcbCondicaoFornecedor.getSelectedIndex() == 2) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getFornecedor().getNome().toUpperCase().equalsIgnoreCase(jtfFornecedor.getText().toUpperCase())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getFornecedor().getNome().toUpperCase().equalsIgnoreCase(jtfFornecedor.getText().toUpperCase())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);

                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            }

        }

        //----NotaFiscal
        //Filtro por NotaFiscal
        //Verificar se o combo de condição por produto não está ocm o traço selecionado
        if (this.jcbCondicaoNotaFiscal.getSelectedIndex() > 0) {

            if (this.jcbCondicaoNotaFiscal.getSelectedIndex() == 1) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        String nf = "" + produtosArmazenado.getNotaFiscal();

                        if (nf.contains(jtfNotaFiscal.getText())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        String nf = "" + produtosArmazenado.getNotaFiscal();

                        if (nf.contains(jtfNotaFiscal.getText())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            } else if (this.jcbCondicaoNotaFiscal.getSelectedIndex() == 2) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        String nf = "" + produtosArmazenado.getNotaFiscal();

                        if (nf.equalsIgnoreCase(jtfNotaFiscal.getText())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        String nf = "" + produtosArmazenado.getNotaFiscal();

                        if (nf.equalsIgnoreCase(jtfNotaFiscal.getText())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            }

        }

        //----Saldo
        //Filtro por Saldo
        //Verificar se o combo de condição por produto não está ocm o traço selecionado
        if (this.jcbCondicaoSaldo.getSelectedIndex() > 0) {

            if (this.jcbCondicaoSaldo.getSelectedIndex() == 1) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getQuantidade() == Double.parseDouble(jtfSaldo.getText())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getQuantidade() == Double.parseDouble(jtfSaldo.getText())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            } else if (this.jcbCondicaoSaldo.getSelectedIndex() == 2) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getQuantidade() > Double.parseDouble(jtfSaldo.getText())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getQuantidade() > Double.parseDouble(jtfSaldo.getText())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            } else if (this.jcbCondicaoSaldo.getSelectedIndex() == 3) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getQuantidade() > Double.parseDouble(jtfSaldo.getText()) || produtosArmazenado.getQuantidade() == Double.parseDouble(jtfSaldo.getText())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getQuantidade() > Double.parseDouble(jtfSaldo.getText()) || produtosArmazenado.getQuantidade() == Double.parseDouble(jtfSaldo.getText())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            } else if (this.jcbCondicaoSaldo.getSelectedIndex() == 4) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getQuantidade() < Double.parseDouble(jtfSaldo.getText())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getQuantidade() < Double.parseDouble(jtfSaldo.getText())) {
                            produtosArmazenadosPesquisa2.add(produtosArmazenado);
                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            } else if (this.jcbCondicaoSaldo.getSelectedIndex() == 5) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getQuantidade() <= Double.parseDouble(jtfSaldo.getText())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getQuantidade() <= Double.parseDouble(jtfSaldo.getText())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            }

        }

        //----preco
        //Filtro por preco
        //Verificar se o combo de condição por produto não está ocm o traço selecionado
        if (this.jcbCondicaoPreco.getSelectedIndex() > 0) {

            if (this.jcbCondicaoPreco.getSelectedIndex() == 1) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getProduto().getPreco() == Double.parseDouble(jtfPreco.getText())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getProduto().getPreco() == Double.parseDouble(jtfPreco.getText())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            } else if (this.jcbCondicaoPreco.getSelectedIndex() == 2) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getProduto().getPreco() > Double.parseDouble(jtfPreco.getText())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getProduto().getPreco() > Double.parseDouble(jtfPreco.getText())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);

                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            } else if (this.jcbCondicaoPreco.getSelectedIndex() == 3) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getProduto().getPreco() > Double.parseDouble(jtfPreco.getText()) || produtosArmazenado.getProduto().getPreco() == Double.parseDouble(jtfPreco.getText())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getProduto().getPreco() > Double.parseDouble(jtfPreco.getText()) || produtosArmazenado.getProduto().getPreco() == Double.parseDouble(jtfPreco.getText())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            } else if (this.jcbCondicaoPreco.getSelectedIndex() == 4) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getProduto().getPreco() < Double.parseDouble(jtfPreco.getText())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getProduto().getPreco() < Double.parseDouble(jtfPreco.getText())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            } else if (this.jcbCondicaoPreco.getSelectedIndex() == 5) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getProduto().getPreco() <= Double.parseDouble(jtfPreco.getText())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getProduto().getPreco() <= Double.parseDouble(jtfPreco.getText())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            }

        }

        //Filtro por armazem
        //Verificar se o combo de condição por armazem não está ocm o traço selecionado
        if (this.jcbCondicaoArmazem.getSelectedIndex() > 0) {

            if (this.jcbCondicaoArmazem.getSelectedIndex() == 1) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getArmazem().getDescricao().toUpperCase().contains(this.jtfArmazem.getText().toUpperCase())) {

                            produtosArmazenadosPesquisa.add(produtosArmazenado);

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getArmazem().getDescricao().toUpperCase().contains(jtfArmazem.getText().toUpperCase())) {

                            produtosArmazenadosPesquisa2.add(produtosArmazenado);

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            } else if (this.jcbCondicaoArmazem.getSelectedIndex() == 2) {

                if (produtosArmazenadosPesquisa.isEmpty()) {

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {

                        if (produtosArmazenado.getProduto().isControladoPorLote()) {

                            if (produtosArmazenado.getArmazem().getDescricao().toUpperCase().equalsIgnoreCase(jtfArmazem.getText().toUpperCase())) {

                                produtosArmazenadosPesquisa.add(produtosArmazenado);

                            }

                        }

                    }

                } else {

                    List<ProdutoArmazenado> produtosArmazenadosPesquisa2 = new ArrayList<>();

                    for (ProdutoArmazenado produtosArmazenado : produtosArmazenadosPesquisa) {

                        if (produtosArmazenado.getProduto().isControladoPorLote()) {

                            if (produtosArmazenado.getArmazem().getDescricao().toUpperCase().equalsIgnoreCase(jtfArmazem.getText().toUpperCase())) {

                                produtosArmazenadosPesquisa2.add(produtosArmazenado);

                            }

                        }

                    }

                    produtosArmazenadosPesquisa.removeAll(produtosArmazenadosPesquisa);
                    produtosArmazenadosPesquisa = produtosArmazenadosPesquisa2;

                }

            }

        }
         */
        FiltroComposite filtroComposite = new FiltroComposite();
        
        if (this.jcbCondicaoLote.getSelectedIndex() > 0) {
            
            filtroComposite.add(new FiltroLote( 
                    this.jtfLote.getText(), 
                    this.jcbCondicaoLote.getSelectedIndex()));
        }
        
        if(this.jcbCondicaoProduto.getSelectedIndex() > 0){
            
            filtroComposite.add(new FiltroDescricaoProduto(this.jtfProduto.getText(), 
                    this.jcbCondicaoProduto.getSelectedIndex()));
        }
        
        if(this.jcbCondicaoFornecedor.getSelectedIndex() > 0){
            
            filtroComposite.add(new FiltroFornecedor(this.jtfFornecedor.getText(), 
                    this.jcbCondicaoFornecedor.getSelectedIndex()));
        }
        
        if(this.jcbCondicaoSaldo.getSelectedIndex() > 0){
            
            filtroComposite.add(new FiltroSaldo(this.jtfSaldo.getText(), 
                    this.jcbCondicaoSaldo.getSelectedIndex()));
        }
        
        if(this.jcbCondicaoUnidadeMedida.getSelectedIndex() > 0){
            
            filtroComposite.add(new FiltroUnidadeMedida(this.jcbCondicaoUnidadeMedida.getSelectedIndex(),this.jtfUnidadeMedida.getText()));
        }
        
        if(this.jcbCondicaoNotaFiscal.getSelectedIndex() > 0){
            
            filtroComposite.add(new FiltroNotaFiscal(this.jcbCondicaoNotaFiscal.getSelectedIndex(),this.jtfNotaFiscal.getText()));
        }
        
        
        produtosArmazenadosPesquisa = filtroComposite.filtrar(produtosArmazenados);
        //atualiza a tabela com o resultado da pesquisa
        atualizarTabelaProdutosArmazenadosComPesquisa();

    }

    private void atualizarTabelaProdutosArmazenadosComPesquisa() {
        
        modeloTabelaProdutoArmazenado = new ProdutoArmazenadoTableModel(produtosArmazenadosPesquisa);
        jtProdutosArmazenados.setModel(modeloTabelaProdutoArmazenado);
        atualizarListaProdutosQuantidadeMinima();
        DefaultTableCellRenderer tcr = new qtdMinimaRender();
        jtProdutosArmazenados.setDefaultRenderer(Object.class, tcr);

    }

    private void atualizarListaProdutosQuantidadeMinima() {

        produtos = getProdutos();
        produtosQtdMinima = new ArrayList<>();
        double qtdSaldo = 0;
        for (Produto produto : produtos) {

            for (ProdutoArmazenado produtosArmazenado : produtosArmazenados) {
                if (produtosArmazenado.getProduto().getCodigo() == produto.getCodigo()) {
                    qtdSaldo += produtosArmazenado.getQuantidade();
                }
            }
            if (qtdSaldo <= produto.getQuantidadeMinima()) {
                produtosQtdMinima.add(produto);
            }
            qtdSaldo = 0;
        }

    }

    /**
     * Atualiza as linhas da tabela de armazéns com todos os armazéns
     * cadastrados na base.
     */
    private void atualizarTabelaProdutosArmazenados() {
        modeloTabelaProdutoArmazenado = new ProdutoArmazenadoTableModel(getProdutosArmazenados());
        jtProdutosArmazenados.setModel(modeloTabelaProdutoArmazenado);
        atualizarListaProdutosQuantidadeMinima();
        DefaultTableCellRenderer tcr = new qtdMinimaRender();
        jtProdutosArmazenados.setDefaultRenderer(Object.class, tcr);

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
        jtfArmazem = new javax.swing.JTextField();
        jcbCondicaoArmazem = new javax.swing.JComboBox<>();
        jlArmazem = new javax.swing.JLabel();
        jtfSaldo = new javax.swing.JFormattedTextField();
        jluniadeMedida = new javax.swing.JLabel();
        jtfUnidadeMedida = new javax.swing.JTextField();
        jcbCondicaoUnidadeMedida = new javax.swing.JComboBox<>();
        jlPreco = new javax.swing.JLabel();
        jtfPreco = new javax.swing.JTextField();
        jcbCondicaoPreco = new javax.swing.JComboBox<>();
        jbtPesquisar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jcbTipoRelatorio = new javax.swing.JComboBox<>();
        jbtGerarRelatorio = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jbtEntradaProduto = new javax.swing.JButton();
        jbtnventario = new javax.swing.JButton();
        jbtSaidaProduto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Saldos em estoque");
        setMinimumSize(new java.awt.Dimension(800, 700));

        jpBaixo.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setMinimumSize(new java.awt.Dimension(80, 230));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(80, 230));

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
        jtProdutosArmazenados.setToolTipText("Tabela de saldo dos produtos. Itens em vermelho chegaram na quantidade mínima(ponto de pedido)");
        jtProdutosArmazenados.setMaximumSize(new java.awt.Dimension(300, 200));
        jtProdutosArmazenados.setMinimumSize(new java.awt.Dimension(300, 200));
        jtProdutosArmazenados.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jtProdutosArmazenados);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(9, 5, 2, 5);
        jpBaixo.add(jScrollPane1, gridBagConstraints);

        getContentPane().add(jpBaixo, java.awt.BorderLayout.PAGE_END);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jpPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisar"));
        java.awt.GridBagLayout jpPesquisaLayout = new java.awt.GridBagLayout();
        jpPesquisaLayout.columnWidths = new int[] {0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0};
        jpPesquisaLayout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
        jpPesquisa.setLayout(jpPesquisaLayout);

        jlbLote.setText("Lote");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jpPesquisa.add(jlbLote, gridBagConstraints);

        jtfLote.setToolTipText("Informe um lote para a pesquisa");
        jtfLote.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jpPesquisa.add(jtfLote, gridBagConstraints);

        jlbSaldo.setText("Saldo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jpPesquisa.add(jlbSaldo, gridBagConstraints);

        Produto.setText("Produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 22;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jpPesquisa.add(Produto, gridBagConstraints);

        jtfProduto.setToolTipText("Informe a descrição de um produto para a pesquisa");
        jtfProduto.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 22;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 27;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jtfProduto, gridBagConstraints);

        jlbNotaFiscal.setText("Nota Fiscal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 22;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jpPesquisa.add(jlbNotaFiscal, gridBagConstraints);

        jtfNotaFiscal.setToolTipText("Informe uma nota fiscal para a pesquisa");
        jtfNotaFiscal.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 22;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jtfNotaFiscal, gridBagConstraints);

        jLabel5.setText("Fornecedor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 50;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jpPesquisa.add(jLabel5, gridBagConstraints);

        jtfFornecedor.setToolTipText("Informe um fornecedor para a pesquisa");
        jtfFornecedor.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 50;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jpPesquisa.add(jtfFornecedor, gridBagConstraints);

        jbtLimparCamposPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/edit-clear.png"))); // NOI18N
        jbtLimparCamposPesquisa.setText("Limpar");
        jbtLimparCamposPesquisa.setToolTipText("Limpar campos de pesquisa");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 60;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jpPesquisa.add(jbtLimparCamposPesquisa, gridBagConstraints);

        jcbCondicaoLote.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Contém a expressão", "Igual a" }));
        jcbCondicaoLote.setToolTipText("Selecione um filtro para lote");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jpPesquisa.add(jcbCondicaoLote, gridBagConstraints);

        jcbCondicaoSaldo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Igual a", "É maior", "É maior ou igual", "É menor", "É menor ou igual" }));
        jcbCondicaoSaldo.setToolTipText("Selecione um filtro para saldo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jpPesquisa.add(jcbCondicaoSaldo, gridBagConstraints);

        jcbCondicaoProduto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Contém a expressão", "Igual a" }));
        jcbCondicaoProduto.setToolTipText("Selecione um filtro para produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 22;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 27;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jcbCondicaoProduto, gridBagConstraints);

        jcbCondicaoNotaFiscal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Contém a expressão", "Igual a" }));
        jcbCondicaoNotaFiscal.setToolTipText("Selecione um filtro para nota fiscal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 22;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jcbCondicaoNotaFiscal, gridBagConstraints);

        jcbCondicaoFornecedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Contém a expressão", "Igual a" }));
        jcbCondicaoFornecedor.setToolTipText("Selecione um filtro para fornecedor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 50;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jpPesquisa.add(jcbCondicaoFornecedor, gridBagConstraints);

        jtfArmazem.setToolTipText("Informe um filtro para armazem");
        jtfArmazem.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 50;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jpPesquisa.add(jtfArmazem, gridBagConstraints);

        jcbCondicaoArmazem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Contém a expressão", "Igual a" }));
        jcbCondicaoArmazem.setToolTipText("Selecione um filtro para armazém");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 50;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jpPesquisa.add(jcbCondicaoArmazem, gridBagConstraints);

        jlArmazem.setText("Armazém");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 50;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jpPesquisa.add(jlArmazem, gridBagConstraints);

        jtfSaldo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        jtfSaldo.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jpPesquisa.add(jtfSaldo, gridBagConstraints);

        jluniadeMedida.setText("Unidade de medida");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 38;
        gridBagConstraints.gridy = 8;
        jpPesquisa.add(jluniadeMedida, gridBagConstraints);

        jtfUnidadeMedida.setToolTipText("Informe um filtro para abreviação de unidade de medida");
        jtfUnidadeMedida.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 38;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jtfUnidadeMedida, gridBagConstraints);

        jcbCondicaoUnidadeMedida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Contém a expressão", "Igual a" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 38;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jpPesquisa.add(jcbCondicaoUnidadeMedida, gridBagConstraints);

        jlPreco.setText("Preço");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        jpPesquisa.add(jlPreco, gridBagConstraints);

        jtfPreco.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jpPesquisa.add(jtfPreco, gridBagConstraints);

        jcbCondicaoPreco.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Igual a", "É maior", "É maior ou igual", "É menor", "É menor ou igual" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jpPesquisa.add(jcbCondicaoPreco, gridBagConstraints);

        jbtPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/search.png"))); // NOI18N
        jbtPesquisar.setText("Pesquisar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 50;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jpPesquisa.add(jbtPesquisar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = -3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 0, 0, 0);
        jPanel1.add(jpPesquisa, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Relatórios"));
        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0};
        jPanel3Layout.rowHeights = new int[] {0, 5, 0};
        jPanel3.setLayout(jPanel3Layout);

        jcbTipoRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "PDF", "EXCEL" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 88;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        jPanel3.add(jcbTipoRelatorio, gridBagConstraints);

        jbtGerarRelatorio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/report.png"))); // NOI18N
        jbtGerarRelatorio.setToolTipText("Gerar relatório das movimentações");
        jbtGerarRelatorio.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 92;
        gridBagConstraints.gridy = 0;
        jPanel3.add(jbtGerarRelatorio, gridBagConstraints);

        jLabel1.setText("Relatório");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 86;
        gridBagConstraints.gridy = 0;
        jPanel3.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(jPanel3, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setMinimumSize(new java.awt.Dimension(794, 697));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 12, 0, 12, 0};
        jPanel2Layout.rowHeights = new int[] {0};
        jPanel2.setLayout(jPanel2Layout);

        jbtEntradaProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/entrada-32.png"))); // NOI18N
        jbtEntradaProduto.setText("Entrada de produto");
        jbtEntradaProduto.setToolTipText("Ir para tela de entrada de produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel2.add(jbtEntradaProduto, gridBagConstraints);

        jbtnventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/inventario-32.png"))); // NOI18N
        jbtnventario.setText("Inventário");
        jbtnventario.setToolTipText("Ir para a tela de inventário");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel2.add(jbtnventario, gridBagConstraints);

        jbtSaidaProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/saida-32.png"))); // NOI18N
        jbtSaidaProduto.setText("Saída de produto");
        jbtSaidaProduto.setToolTipText("Ir para tela de saída de produto");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        jPanel2.add(jbtSaidaProduto, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);
        jPanel2.getAccessibleContext().setAccessibleParent(this);

        setSize(new java.awt.Dimension(852, 699));
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
            java.util.logging.Logger.getLogger(FRMSaldoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRMSaldoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRMSaldoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRMSaldoEstoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FRMSaldoEstoque dialog = new FRMSaldoEstoque(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JButton jbtEntradaProduto;
    private javax.swing.JButton jbtGerarRelatorio;
    private javax.swing.JButton jbtLimparCamposPesquisa;
    private javax.swing.JButton jbtPesquisar;
    protected javax.swing.JButton jbtSaidaProduto;
    protected javax.swing.JButton jbtnventario;
    private javax.swing.JComboBox<String> jcbCondicaoArmazem;
    private javax.swing.JComboBox<String> jcbCondicaoFornecedor;
    private javax.swing.JComboBox<String> jcbCondicaoLote;
    private javax.swing.JComboBox<String> jcbCondicaoNotaFiscal;
    private javax.swing.JComboBox<String> jcbCondicaoPreco;
    protected javax.swing.JComboBox<String> jcbCondicaoProduto;
    private javax.swing.JComboBox<String> jcbCondicaoSaldo;
    private javax.swing.JComboBox<String> jcbCondicaoUnidadeMedida;
    private javax.swing.JComboBox<String> jcbTipoRelatorio;
    private javax.swing.JLabel jlArmazem;
    private javax.swing.JLabel jlPreco;
    private javax.swing.JLabel jlbLote;
    private javax.swing.JLabel jlbNotaFiscal;
    private javax.swing.JLabel jlbSaldo;
    private javax.swing.JLabel jluniadeMedida;
    private javax.swing.JPanel jpBaixo;
    private javax.swing.JPanel jpPesquisa;
    protected javax.swing.JTable jtProdutosArmazenados;
    private javax.swing.JTextField jtfArmazem;
    private javax.swing.JTextField jtfFornecedor;
    private javax.swing.JTextField jtfLote;
    private javax.swing.JTextField jtfNotaFiscal;
    private javax.swing.JTextField jtfPreco;
    protected javax.swing.JTextField jtfProduto;
    private javax.swing.JFormattedTextField jtfSaldo;
    private javax.swing.JTextField jtfUnidadeMedida;
    // End of variables declaration//GEN-END:variables
}
