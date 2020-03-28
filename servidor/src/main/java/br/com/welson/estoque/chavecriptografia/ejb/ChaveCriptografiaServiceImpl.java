package br.com.welson.estoque.chavecriptografia.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;

import br.com.welson.estoque.chavecriptografia.ChaveCriptografiaService;
import br.com.welson.estoque.chavecriptografia.gerachave.GeraNovaChaveRequisicaoDTO;
import br.com.welson.estoque.chavecriptografia.gerachave.GeraNovaChaveRespostaDTO;
import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.funcionalidade.anotacao.Funcionalidade;
import br.com.welson.estoque.requisicao.anotacao.RequisicaoService;
import br.com.welson.estoque.requisicao.ejb.ProcessadorRequisicao;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Stateless
@RequisicaoService
public class ChaveCriptografiaServiceImpl implements ChaveCriptografiaService {

    @EJB
    private ProcessadorRequisicao processadorRequisicao;

    @Override
    public GeraNovaChaveRespostaDTO geraChave(
            @Funcionalidade(CodigoFuncionalidade.GERA_NOVA_CHAVE_CRIPTOGRAFIA_TOKEN) GeraNovaChaveRequisicaoDTO requisicao)
            throws InfraestruturaException, NegocioException {
        return processadorRequisicao.executa(requisicao);
    }

}
