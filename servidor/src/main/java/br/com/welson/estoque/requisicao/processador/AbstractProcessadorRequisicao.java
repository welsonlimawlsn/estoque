package br.com.welson.estoque.requisicao.processador;

import br.com.welson.estoque.funcionalidade.entidade.Funcionalidade;
import br.com.welson.estoque.requisicao.RequisicaoDTO;
import br.com.welson.estoque.requisicao.RespostaDTO;
import br.com.welson.estoque.util.EstoqueErro;
import br.com.welson.estoque.util.exception.NegocioException;

public abstract class AbstractProcessadorRequisicao<REQUISICAO extends RequisicaoDTO<RESPOSTA>, RESPOSTA extends RespostaDTO> {

    public void processaRequisicao(REQUISICAO requisicao, RESPOSTA resposta) throws NegocioException {

        validaPermissoes(requisicao);

        realizaTarefa(requisicao, resposta);
    }

    private void validaPermissoes(REQUISICAO requisicao) throws NegocioException {
        Funcionalidade funcionalidade = requisicao.getFuncionalidade();

        if (funcionalidade.getAutenticacaoNecessaria() && requisicao.getCliente() == null) {
            throw new NegocioException(EstoqueErro.FUNCIONALIDADE_REQUER_AUTENTICACAO);
        }

        if (funcionalidade.getAutenticacaoNecessaria()
                && !funcionalidade.getPublica()
                && !requisicao.getCliente().getFuncionalidadesAcessiveis().contains(funcionalidade)) {
            throw new NegocioException(EstoqueErro.VOCE_NAO_POSSUI_AUTORIZACAO_PARA_ACESSAR_ESSA_FUNCIONALIDADE);
        }
    }

    protected abstract void realizaTarefa(REQUISICAO requisicao, RESPOSTA resposta) throws NegocioException;

}
