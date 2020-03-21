package br.com.welson.estoque.relatorio;

import br.com.welson.estoque.util.exception.InfraestruturaException;

public interface ProcessadorRelatorio {

    void processaRelatorio(RelatorioRequisicaoDTO<?> respostaRelatorio) throws InfraestruturaException;

}
