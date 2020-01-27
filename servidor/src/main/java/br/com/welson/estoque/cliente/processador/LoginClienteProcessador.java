package br.com.welson.estoque.cliente.processador;

import javax.inject.Inject;

import br.com.welson.estoque.cliente.login.LoginClienteRequisicaoDTO;
import br.com.welson.estoque.cliente.login.LoginClienteRespostaDTO;
import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.requisicao.anotacao.Processador;
import br.com.welson.estoque.requisicao.processador.AbstractProcessadorRequisicao;
import br.com.welson.estoque.seguranca.Autenticacao;
import br.com.welson.estoque.seguranca.AutenticacaoAutorizacaoService;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Processador(CodigoFuncionalidade.LOGIN_CLIENTE)
public class LoginClienteProcessador extends AbstractProcessadorRequisicao<LoginClienteRequisicaoDTO, LoginClienteRespostaDTO> {

    @Inject
    private AutenticacaoAutorizacaoService autenticacaoAutorizacaoService;

    @Override
    protected void executaRequisicao(LoginClienteRequisicaoDTO requisicao, LoginClienteRespostaDTO resposta) throws NegocioException, InfraestruturaException {
        Autenticacao autenticacao = autenticacaoAutorizacaoService.autentica(requisicao.getUsuario(), requisicao.getSenha());

        resposta.setToken(autenticacao.getToken());
        resposta.setExpiracao(autenticacao.getExpiracao());
        resposta.setCliente(autenticacao.getClienteDTO());

        requisicao.setCliente(autenticacao.getCliente());
    }

}
