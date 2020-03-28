package br.com.welson.estoque.funcionalidade.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.funcionalidade.FuncionalidadeService;
import br.com.welson.estoque.funcionalidade.ListaFuncionalidadesRequisicaoDTO;
import br.com.welson.estoque.funcionalidade.ListaFuncionalidadesRespostaDTO;
import br.com.welson.estoque.funcionalidade.anotacao.Funcionalidade;
import br.com.welson.estoque.requisicao.anotacao.RequisicaoService;
import br.com.welson.estoque.requisicao.ejb.ProcessadorRequisicao;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Stateless
@RequisicaoService
public class FuncionalidadeServiceImpl implements FuncionalidadeService {

    @EJB
    private ProcessadorRequisicao processadorRequisicao;

    @Override
    public ListaFuncionalidadesRespostaDTO listaFuncionalidades(
            @Funcionalidade(CodigoFuncionalidade.LISTA_FUNCIONALIDADES) ListaFuncionalidadesRequisicaoDTO requisicao)
            throws InfraestruturaException, NegocioException {
        return processadorRequisicao.executa(requisicao);
    }

}
