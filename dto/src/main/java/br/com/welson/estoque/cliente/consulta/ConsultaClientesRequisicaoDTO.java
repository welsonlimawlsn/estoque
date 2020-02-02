package br.com.welson.estoque.cliente.consulta;

import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.QueryParam;

import br.com.welson.estoque.paginacao.PaginacaoRequisicaoDTO;

@Getter
@Setter
public class ConsultaClientesRequisicaoDTO extends PaginacaoRequisicaoDTO<ConsultaClienteRespostaDTO> {

    @QueryParam("nomeCliente")
    private String nomeCliente;

    @QueryParam("cpf")
    private String cpf;

    @QueryParam("grupo")
    private Long grupo;

}
