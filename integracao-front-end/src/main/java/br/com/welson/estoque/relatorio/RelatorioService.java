package br.com.welson.estoque.relatorio;

import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("relatorio")
public interface RelatorioService
{
    @GET
    @Path("{nomeRelatorio}")
    Response downloadRelatorio(@BeanParam DownloadRelatorioRequisicaoDTO requisicao) throws NegocioException, InfraestruturaException;
}
