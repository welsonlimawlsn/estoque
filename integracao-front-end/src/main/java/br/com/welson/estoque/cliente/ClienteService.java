package br.com.welson.estoque.cliente;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.welson.estoque.cliente.consulta.ConsultaClientesRequisicaoDTO;
import br.com.welson.estoque.cliente.login.LoginClienteRequisicaoDTO;
import br.com.welson.estoque.cliente.novocliente.NovoClienteRequisicaoDTO;
import br.com.welson.estoque.cliente.relatorioclientes.RelatorioClientesRequisicaoDTO;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ClienteService {

    @POST
    Response novoCliente(NovoClienteRequisicaoDTO requisicao) throws InfraestruturaException, NegocioException;

    @POST
    @Path("/login")
    Response loginClinte(LoginClienteRequisicaoDTO requisicao) throws InfraestruturaException, NegocioException;

    @GET
    Response consultaClientes(@BeanParam ConsultaClientesRequisicaoDTO requisicao) throws InfraestruturaException, NegocioException;

    @GET
    @Path("/relatorio")
    Response relatorioConsultaCliente(@BeanParam RelatorioClientesRequisicaoDTO requisicao) throws InfraestruturaException, NegocioException;

}
