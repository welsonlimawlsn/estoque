package br.com.welson.estoque.requisicao.ejb;

import javax.ws.rs.core.Response;

import br.com.welson.estoque.requisicao.RequisicaoDTO;
import br.com.welson.estoque.requisicao.RespostaDTO;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

public interface ProcessadorRequisicao {

    <RESPOSTA extends RespostaDTO, REQUISICAO extends RequisicaoDTO<RESPOSTA>> RESPOSTA executa(REQUISICAO requisicao) throws InfraestruturaException, NegocioException;

}
