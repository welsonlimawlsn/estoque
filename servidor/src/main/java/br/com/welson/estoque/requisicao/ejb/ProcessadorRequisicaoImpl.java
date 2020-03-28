package br.com.welson.estoque.requisicao.ejb;

import br.com.welson.estoque.relatorio.ProcessadorRelatorio;
import br.com.welson.estoque.relatorio.RelatorioRequisicaoDTO;
import br.com.welson.estoque.relatorio.dao.RelatorioDAO;
import br.com.welson.estoque.relatorio.entidade.Relatorio;
import br.com.welson.estoque.requisicao.RequisicaoDTO;
import br.com.welson.estoque.requisicao.RespostaDTO;
import br.com.welson.estoque.requisicao.anotacao.seletor.SeletorProcessador;
import br.com.welson.estoque.requisicao.processador.AbstractProcessadorRequisicao;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

@Stateless
public class ProcessadorRequisicaoImpl implements ProcessadorRequisicao
{


    @Inject
    @Any
    private Instance<AbstractProcessadorRequisicao<?, ?>> processadorRequisicaoInstance;

    @EJB
    private GravadorRequisicao gravadorRequisicao;

    @Inject
    private ProcessadorRelatorio processadorRelatorio;

    @Inject
    private RelatorioDAO relatorioDAO;

    @Override
    public <RESPOSTA extends RespostaDTO, REQUISICAO extends RequisicaoDTO<RESPOSTA>> RESPOSTA executa(REQUISICAO requisicao)
            throws InfraestruturaException, NegocioException
    {
        AbstractProcessadorRequisicao<REQUISICAO, RESPOSTA> processador =
                (AbstractProcessadorRequisicao<REQUISICAO, RESPOSTA>) processadorRequisicaoInstance.select(
                        new SeletorProcessador(requisicao.getFuncionalidade().getCodigo())).get();

        try
        {

            RESPOSTA resposta = criaResposta(processador);

            requisicao.setResposta(resposta);

            gravadorRequisicao.gravaNovaRequisicao(requisicao);

            try
            {
                processador.processaRequisicao(requisicao, resposta);
            }
            catch (NegocioException | InfraestruturaException e)
            {
                requisicao.setMotivoFalha(e.getMessage());
                throw e;
            }
            finally
            {
                gravadorRequisicao.atualizaRequisicao(requisicao);
            }
            if (resposta.getClass().isAnnotationPresent(br.com.welson.estoque.relatorio.anotacao.Relatorio.class))
            {
                RelatorioRequisicaoDTO<?> respostaRelatorio = (RelatorioRequisicaoDTO<?>) requisicao;
                processadorRelatorio.processaRelatorio(respostaRelatorio);

                Relatorio relatorio = new Relatorio();

                relatorio.setCliente(requisicao.getCliente());
                relatorio.setInvalidado(false);
                relatorio.setNome(respostaRelatorio.getResposta().getNomeRelatorioGerado());

                relatorioDAO.insere(relatorio);
            }
            return resposta;

        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
        {
            throw new InfraestruturaException(e);
        }
    }

    private <RESPOSTA extends RespostaDTO, REQUISICAO extends RequisicaoDTO<RESPOSTA>> RESPOSTA criaResposta(AbstractProcessadorRequisicao<REQUISICAO, RESPOSTA> processador) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
        ParameterizedType aClass = (ParameterizedType) processador.getClass().getGenericSuperclass();
        Class<RESPOSTA> respostaClass = (Class<RESPOSTA>) aClass.getActualTypeArguments()[1];
        return respostaClass.getConstructor().newInstance();
    }

}
