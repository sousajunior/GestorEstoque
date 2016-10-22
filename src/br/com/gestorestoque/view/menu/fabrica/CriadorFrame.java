/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gestorestoque.view.menu.fabrica;
import br.com.gestorestoque.view.FRMCadastroArmazem;
import br.com.gestorestoque.view.FRMCadastroEntradaSaida;
import br.com.gestorestoque.view.FRMCadastroFornecedor;
import br.com.gestorestoque.view.FRMCadastroProduto;
import br.com.gestorestoque.view.FRMCadastroUnidadeMedida;
import br.com.gestorestoque.view.FRMInventario;
import br.com.gestorestoque.view.FRMMovimentacao;
import br.com.gestorestoque.view.FRMPrincipal;
import br.com.gestorestoque.view.FRMRelatorio;
import br.com.gestorestoque.view.FRMSaldoEstoque;
import br.com.gestorestoque.view.FRMSobre;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Matheus
 */

public class CriadorFrame {
    public static JDialog getFrame(EnumFRM tipoFRM) {
        switch (tipoFRM) {
            case INVENTARIO:
                return new FRMInventario(FRMPrincipal.getInstance(), true);
            case ARMAZEM:
                return new FRMCadastroArmazem(FRMPrincipal.getInstance(), true);
            case PRODUTO:
                return new FRMCadastroProduto(FRMPrincipal.getInstance(), true);
            case UNIDADE_MEDIDA:
                return new FRMCadastroUnidadeMedida(FRMPrincipal.getInstance(), true);
            case MOVIMENTACOES:
                return new FRMMovimentacao(FRMPrincipal.getInstance(), true);
            case FORNECEDOR:
                return new FRMCadastroFornecedor(FRMPrincipal.getInstance(), true);
            case SOBRE:
                return new FRMSobre(FRMPrincipal.getInstance(), true);
            case SALDOS:
                return new FRMSaldoEstoque(FRMPrincipal.getInstance(), true);
            case ENTRADA:
                return new FRMCadastroEntradaSaida(FRMPrincipal.getInstance(), true,true);
            case SAIDA:
                return new FRMCadastroEntradaSaida(FRMPrincipal.getInstance(), true,false);
            default:
                return null;
        }
    }
}
