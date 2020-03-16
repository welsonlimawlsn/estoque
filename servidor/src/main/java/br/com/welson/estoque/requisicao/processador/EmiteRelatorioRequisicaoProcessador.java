package br.com.welson.estoque.requisicao.processador;

import javax.inject.Inject;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.welson.estoque.cliente.entidade.Cliente;
import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.grupo.entidade.Grupo;
import br.com.welson.estoque.requisicao.anotacao.Processador;
import br.com.welson.estoque.requisicao.dao.RequisicaoDAO;
import br.com.welson.estoque.requisicao.entidade.Requisicao;
import br.com.welson.estoque.requisicao.relatorio.EmiteRelatorioRequisicaoRequisicaoDTO;
import br.com.welson.estoque.requisicao.relatorio.EmiteRelatorioRequisicaoRespostaDTO;
import br.com.welson.estoque.requisicao.relatorio.RequisicaoRelatorioDTO;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Processador(CodigoFuncionalidade.EMITE_RELATORIO_REQUISICAO)
public class EmiteRelatorioRequisicaoProcessador extends AbstractProcessadorRequisicao<EmiteRelatorioRequisicaoRequisicaoDTO, EmiteRelatorioRequisicaoRespostaDTO> {

    @Inject
    private RequisicaoDAO requisicaoDAO;

    @Override
    protected void executaRequisicao(EmiteRelatorioRequisicaoRequisicaoDTO requisicao, EmiteRelatorioRequisicaoRespostaDTO resposta) throws NegocioException, InfraestruturaException {
        List<RequisicaoRelatorioDTO> relatorioDTOS = requisicaoDAO.listaTodos().stream()
                .filter(r -> r.getMotivoFalha() == null)
                .map(this::requisicaoRelatorioDTO).collect(Collectors.toList());

        resposta.setLista(relatorioDTOS);
    }

    private RequisicaoRelatorioDTO requisicaoRelatorioDTO(Requisicao requisicao) {
        Cliente semCliente = new Cliente();
        semCliente.setCpf("00000000000");
        semCliente.setNome("Anônimo");
        semCliente.setGrupo(new Grupo());
        semCliente.getGrupo().setNome("Sem grupo associado");

        return RequisicaoRelatorioDTO.builder()
                .cpfCliente(Optional.ofNullable(requisicao.getCliente()).orElse(semCliente).getCpf())
                .idRequisicao(requisicao.getId())
                .nomeFuncionalidade(requisicao.getFuncionalidade().getNome())
                .dataHora(Date.from(requisicao.getDataHora().toInstant(ZoneOffset.ofHours(0))))
                .ipOrigem(requisicao.getIpOrigem())
                .nomeCliente(Optional.ofNullable(requisicao.getCliente()).orElse(semCliente).getNome())
                .grupoCliente(Optional.ofNullable(requisicao.getCliente()).orElse(semCliente).getGrupo().getNome())
                .build();
    }

}
