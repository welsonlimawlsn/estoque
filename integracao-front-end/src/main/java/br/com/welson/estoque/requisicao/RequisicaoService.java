package br.com.welson.estoque.requisicao;

import br.com.welson.estoque.requisicao.relatorio.EmiteRelatorioRequisicaoRequisicaoDTO;
import br.com.welson.estoque.requisicao.relatorio.EmiteRelatorioRequisicaoRespostaDTO;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("requisicao")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface RequisicaoService {

    @GET
    @Path("relatorio")
    EmiteRelatorioRequisicaoRespostaDTO emiteRelatorioRequisicoes(@BeanParam EmiteRelatorioRequisicaoRequisicaoDTO requisicao) throws InfraestruturaException, NegocioException;

}
