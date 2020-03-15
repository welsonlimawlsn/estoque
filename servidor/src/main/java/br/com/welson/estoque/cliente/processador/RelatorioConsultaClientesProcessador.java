package br.com.welson.estoque.cliente.processador;

import javax.inject.Inject;
import java.util.stream.Collectors;

import br.com.welson.estoque.cliente.ClienteDTO;
import br.com.welson.estoque.cliente.dao.ClienteDAO;
import br.com.welson.estoque.cliente.relatorioclientes.RelatorioClientesRequisicaoDTO;
import br.com.welson.estoque.cliente.relatorioclientes.RelatorioClientesRespostaDTO;
import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.grupo.dao.GrupoDAO;
import br.com.welson.estoque.grupo.entidade.Grupo;
import br.com.welson.estoque.requisicao.anotacao.Processador;
import br.com.welson.estoque.requisicao.processador.AbstractProcessadorRequisicao;
import br.com.welson.estoque.util.EstoqueErro;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Processador(CodigoFuncionalidade.RELATORIO_CONSULTA_CLIENTES)
public class RelatorioConsultaClientesProcessador extends AbstractProcessadorRequisicao<RelatorioClientesRequisicaoDTO, RelatorioClientesRespostaDTO> {

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private GrupoDAO grupoDAO;

    @Override
    protected void executaRequisicao(RelatorioClientesRequisicaoDTO requisicao, RelatorioClientesRespostaDTO resposta)
            throws NegocioException, InfraestruturaException {

        Grupo grupo = requisicao.getGrupo() == null ? null : buscaGrupo(requisicao.getGrupo());

        resposta.setClientes(clienteDAO.buscaComFiltros(requisicao.getCpf(), requisicao.getNomeCliente(), grupo).stream()
                .map(cliente -> ClienteDTO.builder()
                        .nome(cliente.getNome())
                        .cpf(cliente.getCpf())
                        .email(cliente.getEmail())
                        .nomeUsuario(cliente.getUsuario())
                        .build())
                .collect(Collectors.toList()));

    }

    private Grupo buscaGrupo(Long id) throws NegocioException {
        return grupoDAO.buscaPorId(id).orElseThrow(() -> new NegocioException(EstoqueErro.GRUPO_INVALIDO));
    }

}
