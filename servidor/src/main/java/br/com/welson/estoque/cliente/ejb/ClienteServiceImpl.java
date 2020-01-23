package br.com.welson.estoque.cliente.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.welson.estoque.cliente.ClienteService;
import br.com.welson.estoque.cliente.novocliente.NovoClienteRequisicaoDTO;
import br.com.welson.estoque.cliente.novocliente.NovoClienteRespostaDTO;
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
    public NovoClienteRespostaDTO novoCliente(@Funcionalidade(CodigoFuncionalidade.NOVO_CLIENTE) NovoClienteRequisicaoDTO requisicao)
            throws InfraestruturaException, NegocioException {
        return processadorRequisicao.executa(requisicao);
    }

}
