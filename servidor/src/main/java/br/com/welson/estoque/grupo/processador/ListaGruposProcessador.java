package br.com.welson.estoque.grupo.processador;

import javax.inject.Inject;
import java.util.stream.Collectors;

import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.grupo.GrupoDTO;
import br.com.welson.estoque.grupo.dao.GrupoDAO;
import br.com.welson.estoque.grupo.listagrupos.ListaGruposRequisicaoDTO;
import br.com.welson.estoque.grupo.listagrupos.ListaGruposRespostaDTO;
import br.com.welson.estoque.requisicao.anotacao.Processador;
import br.com.welson.estoque.requisicao.processador.AbstractProcessadorRequisicao;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Processador(CodigoFuncionalidade.LISTA_GRUPOS)
public class ListaGruposProcessador extends AbstractProcessadorRequisicao<ListaGruposRequisicaoDTO, ListaGruposRespostaDTO> {

    @Inject
    private GrupoDAO grupoDAO;

    @Override
    protected void executaRequisicao(ListaGruposRequisicaoDTO requisicao, ListaGruposRespostaDTO resposta) throws NegocioException, InfraestruturaException {
        resposta.setGrupos(grupoDAO.listaTodos().stream()
                .map(grupo -> GrupoDTO.builder().id(grupo.getId()).nome(grupo.getNome()).build())
                .collect(Collectors.toList()));
    }

}
