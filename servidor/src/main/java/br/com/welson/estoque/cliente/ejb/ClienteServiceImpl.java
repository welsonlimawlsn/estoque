package br.com.welson.estoque.cliente.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import br.com.welson.estoque.cliente.ClienteService;
import br.com.welson.estoque.cliente.consulta.ConsultaClientesRequisicaoDTO;
import br.com.welson.estoque.cliente.login.LoginClienteRequisicaoDTO;
import br.com.welson.estoque.cliente.novocliente.NovoClienteRequisicaoDTO;
import br.com.welson.estoque.cliente.relatorioclientes.RelatorioClientesRequisicaoDTO;
import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.funcionalidade.anotacao.Funcionalidade;
import br.com.welson.estoque.requisicao.anotacao.RequisicaoService;
import br.com.welson.estoque.requisicao.ejb.ProcessadorRequisicao;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Stateless
@RequisicaoService
public class ClienteServiceImpl implements ClienteService {

    @EJB
    private ProcessadorRequisicao processadorRequisicao;

    @Override
    public Response novoCliente(@Funcionalidade(CodigoFuncionalidade.NOVO_CLIENTE) NovoClienteRequisicaoDTO requisicao)
            throws InfraestruturaException, NegocioException {
        return processadorRequisicao.executa(requisicao);
    }

    @Override
    public Response loginClinte(@Funcionalidade(CodigoFuncionalidade.LOGIN_CLIENTE) LoginClienteRequisicaoDTO requisicao)
            throws InfraestruturaException, NegocioException {
        return processadorRequisicao.executa(requisicao);
    }

    @Override
    public Response consultaClientes(
            @Funcionalidade(CodigoFuncionalidade.CONSULTA_CLIENTES) ConsultaClientesRequisicaoDTO requisicao)
            throws InfraestruturaException, NegocioException {
        return processadorRequisicao.executa(requisicao);
    }

    @Override
    public Response relatorioConsultaCliente(
            @Funcionalidade(CodigoFuncionalidade.RELATORIO_CONSULTA_CLIENTES) RelatorioClientesRequisicaoDTO requisicao)
            throws InfraestruturaException, NegocioException {
        return processadorRequisicao.executa(requisicao);
    }

}
