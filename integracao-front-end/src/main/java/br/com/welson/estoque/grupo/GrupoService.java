package br.com.welson.estoque.grupo;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.welson.estoque.grupo.listagrupos.ListaGruposRequisicaoDTO;
import br.com.welson.estoque.grupo.novogrupo.NovoGrupoRequisicaoDTO;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Path("/grupo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface GrupoService {

    @GET
    Response listaGrupos(@BeanParam ListaGruposRequisicaoDTO requisicao) throws InfraestruturaException, NegocioException;

    @POST
    Response novoGrupo(NovoGrupoRequisicaoDTO requisicao) throws InfraestruturaException, NegocioException;

}
