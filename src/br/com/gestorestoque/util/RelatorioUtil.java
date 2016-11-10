
package br.com.gestorestoque.util;

import br.com.gestorestoque.view.enumerado.Relatorio;

/**
 *
 * @author DG
 */
public class RelatorioUtil {

    public static String retornarNomeRelatorio(Relatorio relatorio) {

        switch (relatorio) {
            case RelatorioGeralMovimentacoes:
                return "RelatorioGeralMovimentacoes";
            case RelatorioSaldoEstoque:
                return "RelatorioSaldoEstoque";
            case RelatorioSaldoGeralProdutos:
                return "RelatorioSaldoGeralProdutos";
            case RelatorioProdutos:
                return "RelatorioProdutos";

        }

        return "RelatorioSemNome";
    }

}
