package br.com.welson.estoque.paginacao;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.QueryParam;

import br.com.welson.estoque.requisicao.RequisicaoDTO;
import br.com.welson.estoque.requisicao.RespostaDTO;

@Getter
@Setter
public class PaginacaoRequisicaoDTO<T extends RespostaDTO> extends RequisicaoDTO<T> {

    @QueryParam("numeroPagina")
    private Integer numeroPagina;

    @QueryParam("quantidadePorPagina")
    private Integer quantidadePorPagina;

}
