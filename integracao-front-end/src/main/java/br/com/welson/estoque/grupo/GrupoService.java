package br.com.welson.estoque.grupo;

import br.com.welson.estoque.grupo.listagrupos.ListaGruposRequisicaoDTO;
import br.com.welson.estoque.grupo.listagrupos.ListaGruposRespostaDTO;
import br.com.welson.estoque.grupo.novogrupo.NovoGrupoRequisicaoDTO;
import br.com.welson.estoque.grupo.novogrupo.NovoGrupoRespostaDTO;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/grupo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface GrupoService {

    @GET
    ListaGruposRespostaDTO listaGrupos(@BeanParam ListaGruposRequisicaoDTO requisicao) throws InfraestruturaException, NegocioException;

    @POST
    NovoGrupoRespostaDTO novoGrupo(NovoGrupoRequisicaoDTO requisicao) throws InfraestruturaException, NegocioException;

}
