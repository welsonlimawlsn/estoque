package br.com.welson.estoque.cliente.relatorioclientes;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.QueryParam;

import br.com.welson.estoque.requisicao.RequisicaoDTO;

@Getter
@Setter
public class RelatorioClientesRequisicaoDTO extends RequisicaoDTO<RelatorioClientesRespostaDTO> {

    @QueryParam("nomeCliente")
    private String nomeCliente;

    @QueryParam("cpf")
    private String cpf;

    @QueryParam("grupo")
    private Long grupo;

}
