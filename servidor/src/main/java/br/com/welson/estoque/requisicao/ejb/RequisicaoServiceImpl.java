package br.com.welson.estoque.requisicao.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.funcionalidade.anotacao.Funcionalidade;
import br.com.welson.estoque.requisicao.RequisicaoService;
import br.com.welson.estoque.requisicao.relatorio.EmiteRelatorioRequisicaoRequisicaoDTO;
import br.com.welson.estoque.requisicao.relatorio.EmiteRelatorioRequisicaoRespostaDTO;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Stateless
@br.com.welson.estoque.requisicao.anotacao.RequisicaoService
public class RequisicaoServiceImpl implements RequisicaoService {

    @EJB
    private ProcessadorRequisicao processadorRequisicao;

    @Override
    public EmiteRelatorioRequisicaoRespostaDTO emiteRelatorioRequisicoes(
            @Funcionalidade(CodigoFuncionalidade.EMITE_RELATORIO_REQUISICAO) EmiteRelatorioRequisicaoRequisicaoDTO requisicao) throws InfraestruturaException, NegocioException {
        return processadorRequisicao.executa(requisicao);
    }

}
