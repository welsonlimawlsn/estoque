package br.com.welson.estoque.cliente.processador;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.welson.estoque.cliente.login.LoginClienteRequisicaoDTO;
import br.com.welson.estoque.cliente.login.LoginClienteRespostaDTO;
import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.funcionalidade.entidade.Funcionalidade;
import br.com.welson.estoque.menu.MenuDTO;
import br.com.welson.estoque.menu.dao.MenuDAO;
import br.com.welson.estoque.menu.entidade.Menu;
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

    @Inject
    private MenuDAO menuDAO;

    @Override
    protected void executaRequisicao(LoginClienteRequisicaoDTO requisicao, LoginClienteRespostaDTO resposta) throws NegocioException, InfraestruturaException {
        Autenticacao autenticacao = autenticacaoAutorizacaoService.autentica(requisicao.getUsuario(), requisicao.getSenha());

        resposta.setToken(autenticacao.getToken());
        resposta.setExpiracao(autenticacao.getExpiracao());
        resposta.setCliente(autenticacao.getClienteDTO());
        resposta.setMenu(buscaMenu(autenticacao.getCliente().getGrupo().getFuncionalidadesAcessiveis()));

        requisicao.setCliente(autenticacao.getCliente());
    }

    private List<MenuDTO> buscaMenu(List<Funcionalidade> funcionalidades) {
        List<Menu> menusPai = menuDAO.buscaMenusPai();
        List<MenuDTO> menuDTO = new ArrayList<>();
        for (Menu pai : menusPai) {
            List<MenuDTO> filhos = pai.getMenusFilhos().stream()
                    .filter(menu -> funcionalidades.contains(menu.getFuncionalidade()))
                    .map(menu -> MenuDTO.builder().caminho(menu.getCaminho()).descricao(menu.getDescricao()).nome(menu.getNome()).build())
                    .collect(Collectors.toList());

            if (filhos.size() > 0) {
                menuDTO.add(MenuDTO.builder().nome(pai.getNome()).descricao(pai.getDescricao()).menusFilhos(filhos).build());
            }
        }
        return menuDTO;
    }

}
