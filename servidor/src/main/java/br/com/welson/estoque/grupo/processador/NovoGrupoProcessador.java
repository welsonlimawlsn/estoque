package br.com.welson.estoque.grupo.processador;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.funcionalidade.dao.FuncionalidadeDAO;
import br.com.welson.estoque.funcionalidade.entidade.Funcionalidade;
import br.com.welson.estoque.grupo.dao.GrupoDAO;
import br.com.welson.estoque.grupo.entidade.Grupo;
import br.com.welson.estoque.grupo.novogrupo.NovoGrupoRequisicaoDTO;
import br.com.welson.estoque.grupo.novogrupo.NovoGrupoRespostaDTO;
import br.com.welson.estoque.requisicao.anotacao.Processador;
import br.com.welson.estoque.requisicao.processador.AbstractProcessadorRequisicao;
import br.com.welson.estoque.util.EstoqueErro;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Processador(CodigoFuncionalidade.NOVO_GRUPO)
public class NovoGrupoProcessador extends AbstractProcessadorRequisicao<NovoGrupoRequisicaoDTO, NovoGrupoRespostaDTO> {

    @Inject
    private GrupoDAO grupoDAO;

    @Inject
    private FuncionalidadeDAO funcionalidadeDAO;

    @Override
    protected void executaRequisicao(NovoGrupoRequisicaoDTO requisicao, NovoGrupoRespostaDTO resposta) throws NegocioException, InfraestruturaException {
        Grupo grupo = new Grupo();
        grupo.setNome(requisicao.getNome());
        grupo.setFuncionalidadesAcessiveis(buscaFuncionalidades(requisicao.getFuncionalidades()));
        grupoDAO.insere(grupo);
    }

    private List<Funcionalidade> buscaFuncionalidades(List<Long> codigos) throws NegocioException {
        List<Funcionalidade> funcionalidades = new ArrayList<>();
        for (Long codigo : codigos) {
            funcionalidades.add(funcionalidadeDAO.buscaPorId(codigo)
                    .orElseThrow(() -> new NegocioException(EstoqueErro.FUNCIONALIDADE_INVALIDA)));
        }
        return funcionalidades;
    }

}
