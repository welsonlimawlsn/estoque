package br.com.welson.estoque.cliente.processador;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.welson.estoque.cliente.ClienteDTO;
import br.com.welson.estoque.cliente.consulta.ConsultaClienteRespostaDTO;
import br.com.welson.estoque.cliente.consulta.ConsultaClientesRequisicaoDTO;
import br.com.welson.estoque.cliente.dao.ClienteDAO;
import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.grupo.dao.GrupoDAO;
import br.com.welson.estoque.grupo.entidade.Grupo;
import br.com.welson.estoque.parametro.CodigoParametro;
import br.com.welson.estoque.parametro.service.ParametroService;
import br.com.welson.estoque.requisicao.anotacao.Processador;
import br.com.welson.estoque.requisicao.processador.AbstractProcessadorRequisicao;
import br.com.welson.estoque.util.EstoqueErro;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Processador(CodigoFuncionalidade.CONSULTA_CLIENTES)
public class ConsultaClientesProcessador extends AbstractProcessadorRequisicao<ConsultaClientesRequisicaoDTO, ConsultaClienteRespostaDTO> {

    public static final int PRIMEIRA_PAGINA = 1;

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private GrupoDAO grupoDAO;

    @Inject
    private ParametroService parametroService;

    @Override
    protected void executaRequisicao(ConsultaClientesRequisicaoDTO requisicao, ConsultaClienteRespostaDTO resposta)
            throws NegocioException, InfraestruturaException {
        Grupo grupo = null;

        if (requisicao.getGrupo() != null) {
            grupo = grupoDAO.buscaPorId(requisicao.getGrupo()).orElseThrow(() -> new NegocioException(EstoqueErro.GRUPO_INVALIDO));
        }

        Integer numeroPagina = Optional.ofNullable(requisicao.getNumeroPagina()).orElse(PRIMEIRA_PAGINA);

        Integer tamanhoPagina = Optional.ofNullable(requisicao.getQuantidadePorPagina())
                .orElse(parametroService.buscaParametro(CodigoParametro.QUANTIDADE_ITENS_PAGINA_DEFAULT, Integer.class));

        resposta.setTotalPaginas(clienteDAO.totalRegistrosComFitro(requisicao.getCpf(), requisicao.getNomeCliente(), grupo, tamanhoPagina));

        if (resposta.getTotalPaginas() != 0 && resposta.getTotalPaginas() < numeroPagina) {
            throw new NegocioException(EstoqueErro.NUMERO_DE_PAGINA_INEXISTENTE);
        }

        List<Cliente> clientes = clienteDAO.buscaComFiltrosPaginada(requisicao.getCpf(), requisicao.getNomeCliente(), grupo, numeroPagina, tamanhoPagina);

        if (clientes.isEmpty()) {
            throw new NegocioException(EstoqueErro.NENHUM_RESULTADO);
        }

        resposta.setLista(parseParaDTO(clientes));
        resposta.setPaginaAtual(numeroPagina);
        resposta.setQuantidadePorPagina(tamanhoPagina);
    }

    private List<ClienteDTO> parseParaDTO(List<Cliente> clientes) {
        List<ClienteDTO> clientesDTO = new ArrayList<>();
        for (Cliente cliente : clientes) {
            clientesDTO.add(ClienteDTO.builder()
                    .nomeUsuario(cliente.getUsuario())
                    .nome(cliente.getNome())
                    .email(cliente.getEmail())
                    .cpf(cliente.getCpf())
                    .build());
        }
        return clientesDTO;
    }

}
