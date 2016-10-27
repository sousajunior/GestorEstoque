/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view;

import br.com.gestorestoque.controller.ControladorMovimentacao;
import br.com.gestorestoque.controller.FiltroArmazem;
import br.com.gestorestoque.controller.FiltroComposite;
import br.com.gestorestoque.controller.FiltroDescricaoProduto;
import br.com.gestorestoque.controller.FiltroLote;
import br.com.gestorestoque.controller.FiltroNotaFiscal;
import br.com.gestorestoque.model.Movimentacao;
import br.com.gestorestoque.model.ProdutoArmazenado;
import br.com.gestorestoque.view.enumerado.Relatorio;
import br.com.gestorestoque.view.enumerado.TipoRelatorio;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author DG
 */
public class FRMMovimentacao extends javax.swing.JDialog {

    List<Movimentacao> movimentacoes = new ArrayList<>();
    List<Movimentacao> movimentacoesPesquisa = new ArrayList<>();
    List<ProdutoArmazenado> produtosArmazenados = new ArrayList<>();
    List<ProdutoArmazenado> produtosArmazenadosPesquisa = new ArrayList<>();
    ControladorMovimentacao ctrlMovimentacao;
    TableModel modeloTabelaMovimentacoes;

    /**
     * Creates new form FRMMovimentacao
     *
     * @param parent
     * @param modal
     */
    public FRMMovimentacao(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        ctrlMovimentacao = new ControladorMovimentacao();
        prepararComponentes();
    }

    /**
     * Executa o método ControladorProduto.selecionarTodosArmazens() da classe
     * ControladorProduto. Este método retorna uma lista com todos os armazéns
     * cadastrados na base de dados.
     *
     * @return
     */
    private List<Movimentacao> getMovimentacao() {

        movimentacoes = new ArrayList<>();
        try {
            movimentacoes = this.ctrlMovimentacao.selecionarTodos();
            return movimentacoes;
        } catch (SQLException ex) {
            Logger.getLogger(FRMMovimentacao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Classe interna que define o TableModel da tabela de Armazém.
     */
    private class MovimentacaoTableModel extends AbstractTableModel {

        public final List<Movimentacao> movimentacoes;

        public MovimentacaoTableModel(List<Movimentacao> movimentacoes) {
            this.movimentacoes = movimentacoes;

        }

        /**
         * Retorna a quantidade de linhas existentes na TableModel
         *
         * @return
         */
        @Override
        public int getRowCount() {
            return movimentacoes.size();
        }

        /**
         * Retorna a quantidade de colunas existentes na TableModel
         *
         * @return
         */
        @Override
        public int getColumnCount() {
            return 7;
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

            if (rowIndex < 0 | rowIndex >= movimentacoes.size()) {
                return null;

            }

            Movimentacao movimentacao = movimentacoes.get(rowIndex);

            if (columnIndex == 0) {
                String tipo = "" + movimentacao.getTipoMovimentacao();

                if (tipo.equalsIgnoreCase("e")) {
                    return "Entrada";
                }

                if (tipo.equalsIgnoreCase("s")) {
                    return "Saída";
                }

                if (tipo.equalsIgnoreCase("i")) {
                    return "Inventário";
                }

                return "";
            }

            if (columnIndex == 1) {
                return movimentacao.getLote();
            }

            if (columnIndex == 2) {
                return movimentacao.getQtd();
            }

            if (columnIndex == 3) {
                return movimentacao.getNotaFiscal();

            }

            if (columnIndex == 4) {

                return movimentacao.getData();

            }
            if (columnIndex == 5) {

                return movimentacao.getIdProdutoArmazenado().getProduto().getNome();

            }

            if (columnIndex == 6) {

                return movimentacao.getIdArmazem().getDescricao();

            }

            if (columnIndex == 7) {

                return movimentacao.getId();

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
                return "Tipo da movimentção";
            }

            if (column == 1) {

                return "Lote";
            }

            if (column == 2) {
                return "Quantidade";
            }

            if (column == 3) {
                return "Nota fiscal";

            }

            if (column == 4) {

                return "Data";
            }

            if (column == 5) {

                return "Produto";
            }

            if (column == 6) {

                return "Armazém";
            }

            if (column == 7) {

                return "Código";
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
        atualizarTabelaMovimentacao();

        //jtProdutos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //Relatório (lista de produtos cadastrados)
        //Limpar
        jbtLimparCamposPesquisa.addActionListener(
                (e) -> {
                    btnLimparClicado();
                }
        );

        //pesquisar
        jbtPesquisar.addActionListener(
                (e) -> {
                    btnPesquisarClicado();
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

        //combo de condição para Saldo
        jcbCondicaoQuantidade.addActionListener(
                (e) -> {
                    itemComboCondicaoQuantidadeSelecionado();
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

        //combo de condição para Saldo
        jcbCondicaoQuantidade.addActionListener(
                (e) -> {
                    itemComboCondicaoQuantidadeSelecionado();
                }
        );

        
        jcbTipoRelatorio.addActionListener(
                (e) -> {
                    itemComboTipoRelatorioSelecionado();
                }
        );

        //listener do campo de quantidade(saldo)
        jtfQuantidade.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    filtrar();
                }

            }

        });

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

        //listener do campo de quantidade(saldo)
        jtfQuantidade.addKeyListener(new KeyAdapter() {

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

        //listener do campo de armazem
        jtfArmazem.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    filtrar();
                }

            }

        });

        jbtnGerarRelatorioMovimentacoes.addActionListener(
                (e) -> {
                    try {
                        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
                        btnGerarRelatorioClicado();
                        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    } catch (SQLException ex) {
                        Logger.getLogger(FRMCadastroProduto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        );

    }

    private void btnLimparClicado() {

        this.jcbCondicaoLote.setSelectedIndex(0);
        this.jcbCondicaoProduto.setSelectedIndex(0);
        this.jcbCondicaoQuantidade.setSelectedIndex(0);
        this.jcbCondicaoArmazem.setSelectedIndex(0);
        this.jcbCondicaoNotaFiscal.setSelectedIndex(0);

        atualizarTabelaMovimentacao();
        movimentacoesPesquisa = new ArrayList<>();

    }

    protected void btnPesquisarClicado() {

        movimentacoesPesquisa = new ArrayList<>();
        filtrar();

    }

    private void itemComboTipoRelatorioSelecionado() {

        if (this.jcbTipoRelatorio.getSelectedIndex() == 0) {
            this.jbtnGerarRelatorioMovimentacoes.setEnabled(false);
        } else {
            this.jbtnGerarRelatorioMovimentacoes.setEnabled(true);
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

    private void itemComboCondicaoQuantidadeSelecionado() {

        if (this.jcbCondicaoQuantidade.getSelectedIndex() == 0) {
            this.jtfQuantidade.setText("");
            this.jtfQuantidade.setEnabled(false);
        } else {
            this.jtfQuantidade.setEnabled(true);
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

    private void btnGerarRelatorioClicado() throws SQLException {

        if (modeloTabelaMovimentacoes.getRowCount() > 0) {
            String codigosMovimentacoes = "";
            for (int i = 0; i < modeloTabelaMovimentacoes.getRowCount(); i++) {
                codigosMovimentacoes += " " + modeloTabelaMovimentacoes.getValueAt(i, 7) + ",";
                // System.out.println(" "+modeloTabelaMovimentacao.getValueAt(jtProdutosArmazenados.getSelectedRow(), 0) + ",");
                // System.out.println(codigosProdutosArmazenados);
            }
            if (codigosMovimentacoes.length() > 0) {

                if (this.jcbTipoRelatorio.getSelectedIndex() == 1) {
                    new FRMRelatorio(this,
                            true,codigosMovimentacoes.substring(0, codigosMovimentacoes.length() - 1),
                            Relatorio.RelatorioGeralMovimentacoes, TipoRelatorio.PDF).setVisible(true);
                }

                if (this.jcbTipoRelatorio.getSelectedIndex() == 2) {
                }

            }
        }

    }

    /**
     * Atualiza as linhas da tabela de armazéns com todos os armazéns
     * cadastrados na base.
     */
    private void atualizarTabelaMovimentacao() {
        modeloTabelaMovimentacoes = new MovimentacaoTableModel(getMovimentacao());
        jtMovimentacoes.setModel(modeloTabelaMovimentacoes);
    }

    private void filtrar() {
/*
        movimentacoesPesquisa = new ArrayList<>();
        //Filtro por lote
        //Verificar se o combo de condição por lote não está ocm o traço selecionado
        if (this.jcbCondicaoLote.getSelectedIndex() > 0) {

            //Verificar se o combo de condição por lote esta selecionando (Contém a expressão)
            if (this.jcbCondicaoLote.getSelectedIndex() == 1) {

                //Verificar se a lista de produto armazenado para pesquisa está vazia 
                if (movimentacoesPesquisa.isEmpty()) {

                    //percorre a lista primária de produtos armazenados
                    for (Movimentacao movimentacao : movimentacoes) {

                        //verifica se o produto é controlado por lote
                        if (movimentacao.getIdProdutoArmazenado().getProduto().isControladoPorLote()) {

                            //verifica se o lote da posição atual contem a expressão que o usuário digitou
                            if (movimentacao.getLote().toUpperCase().contains(jtfLote.getText().toUpperCase())) {

                                //adiciona na lista quando encontra um lote que contenha o que o usuário digitou
                                movimentacoesPesquisa.add(movimentacao);

                            }

                        }

                    }

                } else {

                    //Se a lista secundaria de produtos armazenados não for vazia, cria uma nova 
                    List<Movimentacao> movimentacoesPesquisa2 = new ArrayList<>();

                    //percorre a lista secundária de produtos armazenados que foi criada para pesquisa
                    for (Movimentacao movimentacao : movimentacoesPesquisa) {

                        //verifica se o produto é controlado por lote
                        if (movimentacao.getIdProdutoArmazenado().getProduto().isControladoPorLote()) {

                            //verifica se o lote atual contem a expressão que o usuário digitou
                            if (movimentacao.getLote().toUpperCase().contains(jtfLote.getText().toUpperCase())) {

                                //adiciona o item que encontrou na terceira lista
                                movimentacoesPesquisa2.add(movimentacao);

                            }

                        }

                    }

                    //remove os itens da segunda lista
                    movimentacoesPesquisa.removeAll(movimentacoesPesquisa);

                    //e atualiza com o que existe na terceira lista
                    movimentacoesPesquisa = movimentacoesPesquisa2;

                }

                //quando o item (é igual a), e selecionado no combo de filtro por lote
            } else if (this.jcbCondicaoLote.getSelectedIndex() == 2) {

                //Verificar se a lista de produto armazenado para pesquisa está vazia 
                if (movimentacoesPesquisa.isEmpty()) {

                    //percorre a lista primária de produtos armazenados
                    for (Movimentacao movimentacao : movimentacoes) {

                        //verifica se o produto é controlado por lote
                        if (movimentacao.getIdProdutoArmazenado().getProduto().isControladoPorLote()) {

                            //verifica se o lote atual é igual a expressão que o usuário digitou
                            if (movimentacao.getLote().toUpperCase().equalsIgnoreCase(jtfLote.getText().toUpperCase())) {

                                //adiciona na lista quando encontra um lote que seja igual o que o usuário digitou
                                movimentacoesPesquisa.add(movimentacao);

                            }

                        }

                    }

                } else {

                    // se a lista de pesquisa nao estiver vazia
                    List<Movimentacao> movimentacoesPesquisa2 = new ArrayList<>();

                    //percorre a lista secundária de produtos armazenados que foi criada para pesquisa
                    for (Movimentacao movimentacao : movimentacoesPesquisa) {

                        //verifica se o produto é controlado por lote
                        if (movimentacao.getIdProdutoArmazenado().getProduto().isControladoPorLote()) {

                            //verifica se o lote atual é igual a expressão que o usuário digitou
                            if (movimentacao.getLote().toUpperCase().equalsIgnoreCase(jtfLote.getText().toUpperCase())) {

                                //adiciona o item que encontrou na terceira lista
                                movimentacoesPesquisa2.add(movimentacao);

                            }

                        }

                    }

                    //remove os itens da segunda lista
                    movimentacoesPesquisa.removeAll(movimentacoesPesquisa);

                    //e atualiza com o que existe na terceira lista
                    movimentacoesPesquisa = movimentacoesPesquisa2;

                }

            }

        }

        //----Produto
        //Filtro por produto
        //Verificar se o combo de condição por produto não está ocm o traço selecionado
        if (this.jcbCondicaoProduto.getSelectedIndex() > 0) {

            if (this.jcbCondicaoProduto.getSelectedIndex() == 1) {

                if (movimentacoesPesquisa.isEmpty()) {

                    for (Movimentacao movimentacao : movimentacoes) {

                        if (movimentacao.getIdProdutoArmazenado().getProduto().getNome().toUpperCase().contains(jtfProduto.getText().toUpperCase())) {

                            movimentacoesPesquisa.add(movimentacao);

                        }

                    }

                } else {

                    List<Movimentacao> movimentacoesPesquisa2 = new ArrayList<>();

                    for (Movimentacao movimentacao : movimentacoesPesquisa) {

                        if (movimentacao.getIdProdutoArmazenado().getProduto().getNome().toUpperCase().contains(jtfProduto.getText().toUpperCase())) {

                            movimentacoesPesquisa2.add(movimentacao);

                        }

                    }

                    movimentacoesPesquisa.removeAll(movimentacoesPesquisa);
                    movimentacoesPesquisa = movimentacoesPesquisa2;

                }

            } else if (this.jcbCondicaoProduto.getSelectedIndex() == 2) {

                if (movimentacoesPesquisa.isEmpty()) {

                    for (Movimentacao movimentacao : movimentacoes) {

                        if (movimentacao.getIdProdutoArmazenado().getProduto().getNome().toUpperCase().equalsIgnoreCase(jtfProduto.getText().toUpperCase())) {

                            movimentacoesPesquisa.add(movimentacao);

                        }

                    }

                } else {

                    List<Movimentacao> movimentacoesPesquisa2 = new ArrayList<>();

                    for (Movimentacao movimentacao : movimentacoesPesquisa) {

                        if (movimentacao.getIdProdutoArmazenado().getProduto().getNome().toUpperCase().equalsIgnoreCase(jtfProduto.getText().toUpperCase())) {

                            movimentacoesPesquisa2.add(movimentacao);

                        }

                    }

                    movimentacoesPesquisa.removeAll(movimentacoesPesquisa);
                    movimentacoesPesquisa = movimentacoesPesquisa2;

                }

            }

        }

        //----NotaFiscal
        //Filtro por NotaFiscal
        //Verificar se o combo de condição por produto não está ocm o traço selecionado
        if (this.jcbCondicaoNotaFiscal.getSelectedIndex() > 0) {

            if (this.jcbCondicaoNotaFiscal.getSelectedIndex() == 1) {

                if (movimentacoesPesquisa.isEmpty()) {

                    for (Movimentacao movimentacao : movimentacoes) {

                        String nf = "" + movimentacao.getNotaFiscal();

                        if (nf.contains(jtfNotaFiscal.getText())) {

                            movimentacoesPesquisa.add(movimentacao);

                        }

                    }

                } else {

                    List<Movimentacao> movimentacoesPesquisa2 = new ArrayList<>();

                    for (Movimentacao movimentacao : movimentacoesPesquisa) {

                        String nf = "" + movimentacao.getNotaFiscal();

                        if (nf.contains(jtfNotaFiscal.getText())) {

                            movimentacoesPesquisa2.add(movimentacao);

                        }

                    }

                    movimentacoesPesquisa.removeAll(movimentacoesPesquisa);
                    movimentacoesPesquisa = movimentacoesPesquisa2;

                }

            } else if (this.jcbCondicaoNotaFiscal.getSelectedIndex() == 2) {

                if (movimentacoesPesquisa.isEmpty()) {

                    for (Movimentacao movimentacao : movimentacoes) {

                        String nf = "" + movimentacao.getNotaFiscal();

                        if (nf.equalsIgnoreCase(jtfNotaFiscal.getText())) {

                            movimentacoesPesquisa.add(movimentacao);

                        }

                    }

                } else {

                    List<Movimentacao> movimentacoesPesquisa2 = new ArrayList<>();

                    for (Movimentacao movimentacao : movimentacoesPesquisa) {

                        String nf = "" + movimentacao.getNotaFiscal();

                        if (nf.equalsIgnoreCase(jtfNotaFiscal.getText())) {

                            movimentacoesPesquisa2.add(movimentacao);

                        }

                    }

                    movimentacoesPesquisa.removeAll(movimentacoesPesquisa);
                    movimentacoesPesquisa = movimentacoesPesquisa2;

                }

            }

        }

        //----Saldo
        //Filtro por Saldo
        //Verificar se o combo de condição por produto não está ocm o traço selecionado
        if (this.jcbCondicaoQuantidade.getSelectedIndex() > 0) {

            if (this.jcbCondicaoQuantidade.getSelectedIndex() == 1) {

                if (movimentacoesPesquisa.isEmpty()) {

                    for (Movimentacao movimentacao : movimentacoes) {

                        if (movimentacao.getQtd() == Double.parseDouble(jtfQuantidade.getText())) {

                            movimentacoesPesquisa.add(movimentacao);

                        }

                    }

                } else {

                    List<Movimentacao> movimentacoesPesquisa2 = new ArrayList<>();

                    for (Movimentacao movimentacao : movimentacoesPesquisa) {

                        if (movimentacao.getQtd() == Double.parseDouble(jtfQuantidade.getText())) {

                            movimentacoesPesquisa2.add(movimentacao);

                        }

                    }

                    movimentacoesPesquisa.removeAll(movimentacoesPesquisa);
                    movimentacoesPesquisa = movimentacoesPesquisa2;

                }

            } else if (this.jcbCondicaoQuantidade.getSelectedIndex() == 2) {

                if (movimentacoesPesquisa.isEmpty()) {

                    for (Movimentacao movimentacao : movimentacoes) {

                        if (movimentacao.getQtd() > Double.parseDouble(jtfQuantidade.getText())) {

                            movimentacoesPesquisa.add(movimentacao);

                        }

                    }

                } else {

                    List<Movimentacao> movimentacoesPesquisa2 = new ArrayList<>();

                    for (Movimentacao movimentacao : movimentacoesPesquisa) {

                        if (movimentacao.getQtd() > Double.parseDouble(jtfQuantidade.getText())) {

                            movimentacoesPesquisa2.add(movimentacao);

                        }

                    }

                    movimentacoesPesquisa.removeAll(movimentacoesPesquisa);
                    movimentacoesPesquisa = movimentacoesPesquisa2;

                }

            } else if (this.jcbCondicaoQuantidade.getSelectedIndex() == 3) {

                if (movimentacoesPesquisa.isEmpty()) {

                    for (Movimentacao movimentacao : movimentacoes) {

                        if (movimentacao.getQtd() > Double.parseDouble(jtfQuantidade.getText()) || movimentacao.getQtd() == Double.parseDouble(jtfQuantidade.getText())) {

                            movimentacoesPesquisa.add(movimentacao);

                        }

                    }

                } else {

                    List<Movimentacao> movimentacoesPesquisa2 = new ArrayList<>();

                    for (Movimentacao movimentacao : movimentacoesPesquisa) {

                        if (movimentacao.getQtd() > Double.parseDouble(jtfQuantidade.getText()) || movimentacao.getQtd() == Double.parseDouble(jtfQuantidade.getText())) {

                            movimentacoesPesquisa2.add(movimentacao);

                        }

                    }

                    movimentacoesPesquisa.removeAll(movimentacoesPesquisa);
                    movimentacoesPesquisa = movimentacoesPesquisa2;

                }

            } else if (this.jcbCondicaoQuantidade.getSelectedIndex() == 4) {

                if (movimentacoesPesquisa.isEmpty()) {

                    for (Movimentacao movimentacao : movimentacoes) {

                        if (movimentacao.getQtd() < Double.parseDouble(jtfQuantidade.getText())) {

                            movimentacoesPesquisa.add(movimentacao);

                        }

                    }

                } else {

                    List<Movimentacao> movimentacoesPesquisa2 = new ArrayList<>();

                    for (Movimentacao movimentacao : movimentacoesPesquisa) {

                        if (movimentacao.getQtd() < Double.parseDouble(jtfQuantidade.getText())) {
                            movimentacoesPesquisa2.add(movimentacao);
                        }

                    }

                    movimentacoesPesquisa.removeAll(movimentacoesPesquisa);
                    movimentacoesPesquisa = movimentacoesPesquisa2;

                }

            } else if (this.jcbCondicaoQuantidade.getSelectedIndex() == 5) {

                if (movimentacoesPesquisa.isEmpty()) {

                    for (Movimentacao movimentacao : movimentacoes) {

                        if (movimentacao.getQtd() <= Double.parseDouble(jtfQuantidade.getText())) {

                            movimentacoesPesquisa.add(movimentacao);

                        }

                    }

                } else {

                    List<Movimentacao> movimentacoesPesquisa2 = new ArrayList<>();

                    for (Movimentacao movimentacao : movimentacoesPesquisa) {

                        if (movimentacao.getQtd() <= Double.parseDouble(jtfQuantidade.getText())) {

                            movimentacoesPesquisa2.add(movimentacao);

                        }

                    }

                    movimentacoesPesquisa.removeAll(movimentacoesPesquisa);
                    movimentacoesPesquisa = movimentacoesPesquisa2;

                }

            }

        }

        //Filtro por armazem
        //Verificar se o combo de condição por armazem não está ocm o traço selecionado
        if (this.jcbCondicaoArmazem.getSelectedIndex() > 0) {

            if (this.jcbCondicaoArmazem.getSelectedIndex() == 1) {

                if (movimentacoesPesquisa.isEmpty()) {

                    for (Movimentacao movimentacao : movimentacoes) {

                        if (movimentacao.getIdArmazem().getDescricao().toUpperCase().contains(this.jtfArmazem.getText().toUpperCase())) {

                            movimentacoesPesquisa.add(movimentacao);

                        }

                    }

                } else {

                    List<Movimentacao> movimentacoesPesquisa2 = new ArrayList<>();

                    for (Movimentacao movimentacao : movimentacoesPesquisa) {

                        if (movimentacao.getIdArmazem().getDescricao().toUpperCase().contains(jtfArmazem.getText().toUpperCase())) {

                            movimentacoesPesquisa2.add(movimentacao);

                        }

                    }

                    movimentacoesPesquisa.removeAll(movimentacoesPesquisa);
                    movimentacoesPesquisa = movimentacoesPesquisa2;

                }

            } else if (this.jcbCondicaoArmazem.getSelectedIndex() == 2) {

                if (movimentacoesPesquisa.isEmpty()) {

                    for (Movimentacao movimentacao : movimentacoes) {

                        if (movimentacao.getIdArmazem().getDescricao().toUpperCase().equalsIgnoreCase(jtfArmazem.getText().toUpperCase())) {

                            movimentacoesPesquisa.add(movimentacao);

                        }

                    }

                } else {

                    List<Movimentacao> movimentacoesPesquisa2 = new ArrayList<>();

                    for (Movimentacao movimentacao : movimentacoesPesquisa) {

                        if (movimentacao.getIdArmazem().getDescricao().toUpperCase().equalsIgnoreCase(jtfArmazem.getText().toUpperCase())) {

                            movimentacoesPesquisa2.add(movimentacao);

                        }

                    }

                    movimentacoesPesquisa.removeAll(movimentacoesPesquisa);
                    movimentacoesPesquisa = movimentacoesPesquisa2;

                }

            }

        }

        //atualiza a tabela com o resultado da pesquisa
        atualizarTabelaMovimentacaoComPesquisa();
 */
        FiltroComposite filtroComposite = new FiltroComposite();
        
        //ativa o filtro por lote
        if(this.jcbCondicaoLote.getSelectedIndex() > 0)
        {
            filtroComposite.add(new FiltroLote(this.jtfLote.getText(), jcbCondicaoLote.getSelectedIndex()));
        }
        //ativa o filtro por descrição do produto
        if(this.jcbCondicaoProduto.getSelectedIndex() > 0)
        {
            filtroComposite.add(new FiltroDescricaoProduto(this.jtfProduto.getText(), jcbCondicaoProduto.getSelectedIndex()));
        }
        //ativa o filtro por aramazém
        if(this.jcbCondicaoArmazem.getSelectedIndex() > 0)
        {
            filtroComposite.add(new FiltroArmazem(this.jcbCondicaoArmazem.getSelectedIndex(),this.jtfArmazem.getText()));
        }
        //ativa o filtro por quantidade (saldo)
        if(this.jcbCondicaoQuantidade.getSelectedIndex() > 0)
        {
            filtroComposite.add(new FiltroSaldo(this.jtfQuantidade.getText(),this.jcbCondicaoQuantidade.getSelectedIndex()));
        }
        //ativa o filtro por nota fiscal
        if(this.jcbCondicaoNotaFiscal.getSelectedIndex() > 0)
        {
            filtroComposite.add(new FiltroNotaFiscal(this.jcbCondicaoNotaFiscal.getSelectedIndex(),this.jtfNotaFiscal.getText()));
        }
        
        //chama o composite de filtros com todos os filtros ativos
        produtosArmazenadosPesquisa = filtroComposite.filtrar(produtosArmazenados);
        //atualiza a tabela com o resultado da pesquisa
        atualizarTabelaMovimentacaoComPesquisa();
        
    }

    private void atualizarTabelaMovimentacaoComPesquisa() {
        modeloTabelaMovimentacoes = new MovimentacaoTableModel(movimentacoesPesquisa);
        jtMovimentacoes.setModel(modeloTabelaMovimentacoes);

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
        jScrollPane1 = new javax.swing.JScrollPane();
        jtMovimentacoes = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jpPesquisa = new javax.swing.JPanel();
        jlbLote = new javax.swing.JLabel();
        jtfLote = new javax.swing.JTextField();
        jlbQuantidade = new javax.swing.JLabel();
        Produto = new javax.swing.JLabel();
        jtfProduto = new javax.swing.JTextField();
        jlbNotaFiscal = new javax.swing.JLabel();
        jtfNotaFiscal = new javax.swing.JTextField();
        jbtLimparCamposPesquisa = new javax.swing.JButton();
        jcbCondicaoLote = new javax.swing.JComboBox<>();
        jcbCondicaoQuantidade = new javax.swing.JComboBox<>();
        jcbCondicaoProduto = new javax.swing.JComboBox<>();
        jcbCondicaoNotaFiscal = new javax.swing.JComboBox<>();
        jtfArmazem = new javax.swing.JTextField();
        jcbCondicaoArmazem = new javax.swing.JComboBox<>();
        jlArmazem = new javax.swing.JLabel();
        jtfQuantidade = new javax.swing.JFormattedTextField();
        jbtPesquisar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jcbTipoRelatorio = new javax.swing.JComboBox<>();
        jbtnGerarRelatorioMovimentacoes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Histórico de movimentações");

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0};
        jPanel1Layout.rowHeights = new int[] {0};
        jPanel1.setLayout(jPanel1Layout);

        jScrollPane1.setMinimumSize(new java.awt.Dimension(675, 220));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(685, 220));

        jtMovimentacoes.setModel(new javax.swing.table.DefaultTableModel(
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
        jtMovimentacoes.setToolTipText("Tabela de movimentações");
        jtMovimentacoes.setMinimumSize(new java.awt.Dimension(80, 230));
        jScrollPane1.setViewportView(jtMovimentacoes);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 13, 0);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0};
        jPanel2Layout.rowHeights = new int[] {0, 5, 0, 5, 0};
        jPanel2.setLayout(jPanel2Layout);

        jpPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder("Pesquisar"));
        java.awt.GridBagLayout jpPesquisaLayout = new java.awt.GridBagLayout();
        jpPesquisaLayout.columnWidths = new int[] {0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0};
        jpPesquisaLayout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0, 5, 0};
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

        jlbQuantidade.setText("Quantidade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jpPesquisa.add(jlbQuantidade, gridBagConstraints);

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

        jbtLimparCamposPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/edit-clear.png"))); // NOI18N
        jbtLimparCamposPesquisa.setText("Limpar");
        jbtLimparCamposPesquisa.setToolTipText("Limpar campos de pesquisa");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 60;
        gridBagConstraints.gridy = 14;
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

        jcbCondicaoQuantidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Igual a", "É maior", "É maior ou igual", "É menor", "É menor ou igual" }));
        jcbCondicaoQuantidade.setToolTipText("Selecione um filtro para a quantidade");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jpPesquisa.add(jcbCondicaoQuantidade, gridBagConstraints);

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

        jtfArmazem.setToolTipText("Informe um filtro para armazem");
        jtfArmazem.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 50;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jpPesquisa.add(jtfArmazem, gridBagConstraints);

        jcbCondicaoArmazem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Contém a expressão", "Igual a" }));
        jcbCondicaoArmazem.setToolTipText("Selecione um filtro para armazém");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 50;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 0, 4);
        jpPesquisa.add(jcbCondicaoArmazem, gridBagConstraints);

        jlArmazem.setText("Armazém");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 50;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jpPesquisa.add(jlArmazem, gridBagConstraints);

        jtfQuantidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        jtfQuantidade.setToolTipText("Informe um filtro para a quantidade e tecle 'Enter'");
        jtfQuantidade.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 21;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jpPesquisa.add(jtfQuantidade, gridBagConstraints);

        jbtPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/search.png"))); // NOI18N
        jbtPesquisar.setText("Pesquisar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 50;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        jpPesquisa.add(jbtPesquisar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 41;
        jPanel2.add(jpPesquisa, gridBagConstraints);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Relatórios"));
        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWidths = new int[] {0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0, 12, 0};
        jPanel3Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0};
        jPanel3.setLayout(jPanel3Layout);

        jcbTipoRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "PDF", "EXCEL" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 76;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        jPanel3.add(jcbTipoRelatorio, gridBagConstraints);

        jbtnGerarRelatorioMovimentacoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/gestorestoque/view/Imagens/report.png"))); // NOI18N
        jbtnGerarRelatorioMovimentacoes.setToolTipText("Gerar relatório das movimentações");
        jbtnGerarRelatorioMovimentacoes.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 80;
        gridBagConstraints.gridy = 4;
        jPanel3.add(jbtnGerarRelatorioMovimentacoes, gridBagConstraints);

        jLabel1.setText("Relatório");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 74;
        gridBagConstraints.gridy = 4;
        jPanel3.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 41;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(jPanel3, gridBagConstraints);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(720, 590));
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
            java.util.logging.Logger.getLogger(FRMMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FRMMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FRMMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FRMMovimentacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FRMMovimentacao dialog = new FRMMovimentacao(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtLimparCamposPesquisa;
    private javax.swing.JButton jbtPesquisar;
    private javax.swing.JButton jbtnGerarRelatorioMovimentacoes;
    private javax.swing.JComboBox<String> jcbCondicaoArmazem;
    private javax.swing.JComboBox<String> jcbCondicaoLote;
    private javax.swing.JComboBox<String> jcbCondicaoNotaFiscal;
    protected javax.swing.JComboBox<String> jcbCondicaoProduto;
    private javax.swing.JComboBox<String> jcbCondicaoQuantidade;
    private javax.swing.JComboBox<String> jcbTipoRelatorio;
    private javax.swing.JLabel jlArmazem;
    private javax.swing.JLabel jlbLote;
    private javax.swing.JLabel jlbNotaFiscal;
    private javax.swing.JLabel jlbQuantidade;
    private javax.swing.JPanel jpPesquisa;
    private javax.swing.JTable jtMovimentacoes;
    private javax.swing.JTextField jtfArmazem;
    private javax.swing.JTextField jtfLote;
    private javax.swing.JTextField jtfNotaFiscal;
    protected javax.swing.JTextField jtfProduto;
    private javax.swing.JFormattedTextField jtfQuantidade;
    // End of variables declaration//GEN-END:variables
}
