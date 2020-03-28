package br.com.welson.estoque.grupo.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.funcionalidade.anotacao.Funcionalidade;
import br.com.welson.estoque.grupo.GrupoService;
import br.com.welson.estoque.grupo.listagrupos.ListaGruposRequisicaoDTO;
import br.com.welson.estoque.grupo.listagrupos.ListaGruposRespostaDTO;
import br.com.welson.estoque.grupo.novogrupo.NovoGrupoRequisicaoDTO;
import br.com.welson.estoque.grupo.novogrupo.NovoGrupoRespostaDTO;
import br.com.welson.estoque.requisicao.anotacao.RequisicaoService;
import br.com.welson.estoque.requisicao.ejb.ProcessadorRequisicao;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Stateless
@RequisicaoService
public class GrupoServiceImpl implements GrupoService {

    @EJB
    private ProcessadorRequisicao processadorRequisicao;

    @Override
    public ListaGruposRespostaDTO listaGrupos(@Funcionalidade(CodigoFuncionalidade.LISTA_GRUPOS) ListaGruposRequisicaoDTO requisicao)
            throws InfraestruturaException, NegocioException {
        return processadorRequisicao.executa(requisicao);
    }

    @Override
    public NovoGrupoRespostaDTO novoGrupo(@Funcionalidade(CodigoFuncionalidade.NOVO_GRUPO) NovoGrupoRequisicaoDTO requisicao) throws InfraestruturaException, NegocioException {
        return processadorRequisicao.executa(requisicao);
    }

}
