package br.com.welson.estoque.cliente.processador;

import javax.inject.Inject;

import br.com.welson.estoque.cliente.dao.ClienteDAO;
import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.cliente.novocliente.NovoClienteRequisicaoDTO;
import br.com.welson.estoque.cliente.novocliente.NovoClienteRespostaDTO;
import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.grupo.dao.GrupoDAO;
import br.com.welson.estoque.grupo.entidade.Grupo;
import br.com.welson.estoque.requisicao.anotacao.Processador;
import br.com.welson.estoque.requisicao.processador.AbstractProcessadorRequisicao;
import br.com.welson.estoque.seguranca.SenhaService;
import br.com.welson.estoque.util.EstoqueErro;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Processador(CodigoFuncionalidade.NOVO_CLIENTE)
public class NovoClienteProcessador extends AbstractProcessadorRequisicao<NovoClienteRequisicaoDTO, NovoClienteRespostaDTO> {

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private GrupoDAO grupoDAO;

    @Inject
    private SenhaService senhaService;

    @Override
    protected void executaRequisicao(NovoClienteRequisicaoDTO requisicao, NovoClienteRespostaDTO resposta) throws NegocioException, InfraestruturaException {
        verificaSeClienteNaoEstaCadastrado(requisicao);

        Cliente cliente = new Cliente();
        cliente.setCpf(requisicao.getCpf());
        cliente.setEmail(requisicao.getEmail());
        cliente.setNome(requisicao.getNome());
        cliente.setSenha(senhaService.hash(requisicao.getSenha()));
        cliente.setUsuario(requisicao.getUsuario());
        cliente.setGrupo(buscaGrupo(requisicao.getCodigoGrupo()));

        clienteDAO.insere(cliente);
    }

    private Grupo buscaGrupo(Long codigoGrupo) throws NegocioException {
        return grupoDAO.buscaPorId(codigoGrupo).orElseThrow(() -> new NegocioException(EstoqueErro.GRUPO_INVALIDO));
    }

    private void verificaSeClienteNaoEstaCadastrado(NovoClienteRequisicaoDTO requisicao) throws NegocioException {
        if (clienteDAO.buscaPorId(requisicao.getCpf()).isPresent() || clienteDAO.buscaPorEmail(requisicao.getEmail()).isPresent()) {
            throw new NegocioException(EstoqueErro.CLIENTE_JA_CADASTRADO);
        }

        if (clienteDAO.buscaPorNomeUsuario(requisicao.getUsuario()).isPresent()) {
            throw new NegocioException(EstoqueErro.NOME_DE_USUARIO_JA_EXISTE);
        }
    }

}
