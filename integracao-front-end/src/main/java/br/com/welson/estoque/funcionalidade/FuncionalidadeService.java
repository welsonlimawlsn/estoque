package br.com.welson.estoque.funcionalidade;

import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/funcionalidade")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface FuncionalidadeService {

    @GET
    ListaFuncionalidadesRespostaDTO listaFuncionalidades(@BeanParam ListaFuncionalidadesRequisicaoDTO requisicao) throws InfraestruturaException, NegocioException;

}
