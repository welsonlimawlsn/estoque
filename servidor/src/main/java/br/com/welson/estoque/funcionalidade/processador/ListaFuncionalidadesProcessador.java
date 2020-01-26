package br.com.welson.estoque.funcionalidade.processador;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.funcionalidade.FuncionalidadeDTO;
import br.com.welson.estoque.funcionalidade.ListaFuncionalidadesRequisicaoDTO;
import br.com.welson.estoque.funcionalidade.ListaFuncionalidadesRespostaDTO;
import br.com.welson.estoque.funcionalidade.dao.FuncionalidadeDAO;
import br.com.welson.estoque.funcionalidade.entidade.Funcionalidade;
import br.com.welson.estoque.requisicao.anotacao.Processador;
import br.com.welson.estoque.requisicao.processador.AbstractProcessadorRequisicao;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Processador(CodigoFuncionalidade.LISTA_FUNCIONALIDADES)
public class ListaFuncionalidadesProcessador extends AbstractProcessadorRequisicao<ListaFuncionalidadesRequisicaoDTO, ListaFuncionalidadesRespostaDTO> {

    @Inject
    private FuncionalidadeDAO funcionalidadeDAO;

    @Override
    protected void executaRequisicao(ListaFuncionalidadesRequisicaoDTO requisicao, ListaFuncionalidadesRespostaDTO resposta) throws NegocioException, InfraestruturaException {
        List<FuncionalidadeDTO> funcionalidades = funcionalidadeDAO.buscaFuncionalidades().stream().map(this::criaDto).collect(Collectors.toList());
        resposta.setFuncionalidades(funcionalidades);
    }

    private FuncionalidadeDTO criaDto(Funcionalidade funcionalidade) {
        return FuncionalidadeDTO.builder()
                .codigo(funcionalidade.getCodigo())
                .nome(funcionalidade.getNome())
                .descricao(funcionalidade.getDescricao())
                .build();
    }

}
