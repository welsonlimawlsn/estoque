package br.com.welson.estoque.chavecriptografia;

import br.com.welson.estoque.chavecriptografia.gerachave.GeraNovaChaveRequisicaoDTO;
import br.com.welson.estoque.chavecriptografia.gerachave.GeraNovaChaveRespostaDTO;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/chave-criptografia")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ChaveCriptografiaService
{

    @POST
    GeraNovaChaveRespostaDTO geraChave(GeraNovaChaveRequisicaoDTO requisicao) throws InfraestruturaException, NegocioException;

}
