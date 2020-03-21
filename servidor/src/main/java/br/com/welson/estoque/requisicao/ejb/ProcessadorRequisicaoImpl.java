package br.com.welson.estoque.requisicao.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

import br.com.welson.estoque.constante.TipoResposta;
import br.com.welson.estoque.relatorio.ProcessadorRelatorio;
import br.com.welson.estoque.relatorio.RelatorioRequisicaoDTO;
import br.com.welson.estoque.relatorio.anotacao.Relatorio;
import br.com.welson.estoque.requisicao.RequisicaoDTO;
import br.com.welson.estoque.requisicao.RespostaDTO;
import br.com.welson.estoque.requisicao.anotacao.seletor.SeletorProcessador;
import br.com.welson.estoque.requisicao.processador.AbstractProcessadorRequisicao;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

@Stateless
public class ProcessadorRequisicaoImpl implements ProcessadorRequisicao {

    public static final String CONTENT_DISPOSITION = "Content-Disposition";

    @Inject
    @Any
    private Instance<AbstractProcessadorRequisicao<?, ?>> processadorRequisicaoInstance;

    @EJB
    private GravadorRequisicao gravadorRequisicao;

    @Inject
    private ProcessadorRelatorio processadorRelatorio;

    @Override
    public <RESPOSTA extends RespostaDTO, REQUISICAO extends RequisicaoDTO<RESPOSTA>> Response executa(REQUISICAO requisicao)
            throws InfraestruturaException, NegocioException {
        AbstractProcessadorRequisicao<REQUISICAO, RESPOSTA> processador =
                (AbstractProcessadorRequisicao<REQUISICAO, RESPOSTA>) processadorRequisicaoInstance.select(
                        new SeletorProcessador(requisicao.getFuncionalidade().getCodigo())).get();

        try {

            RESPOSTA resposta = criaResposta(processador);

            requisicao.setResposta(resposta);

            gravadorRequisicao.gravaNovaRequisicao(requisicao);

            try {
                processador.processaRequisicao(requisicao, resposta);
            } catch (NegocioException | InfraestruturaException e) {
                requisicao.setMotivoFalha(e.getMessage());
                throw e;
            } finally {
                gravadorRequisicao.atualizaRequisicao(requisicao);
            }
            if (resposta.getClass().isAnnotationPresent(Relatorio.class)) {
                return geraRespostaComRelatorio((RelatorioRequisicaoDTO<?>) requisicao);
            }
            return Response.status(Response.Status.OK).entity(resposta).build();

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new InfraestruturaException(e);
        }
    }

    private Response geraRespostaComRelatorio(RelatorioRequisicaoDTO<?> requisicao) throws InfraestruturaException {

        processadorRelatorio.processaRelatorio(requisicao);

        return Response.ok(requisicao.getResposta().getRelatorio())
                .type(TipoResposta.APPLICATION_PDF)
                .header(CONTENT_DISPOSITION, getValueContentDisposition(requisicao))
                .build();
    }

    private String getValueContentDisposition(RelatorioRequisicaoDTO<?> requisicao) {
        String nomeArquivo = requisicao.getResposta().getClass().getAnnotation(Relatorio.class).nomeArquivoFinal();
        return "attachment; filename=\"" + (nomeArquivo.isEmpty() ? System.currentTimeMillis() : nomeArquivo) + requisicao.getFormato().getExtensao();
    }

    private <RESPOSTA extends RespostaDTO, REQUISICAO extends RequisicaoDTO<RESPOSTA>> RESPOSTA criaResposta(AbstractProcessadorRequisicao<REQUISICAO, RESPOSTA> processador) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        ParameterizedType aClass = (ParameterizedType) processador.getClass().getGenericSuperclass();
        Class<RESPOSTA> respostaClass = (Class<RESPOSTA>) aClass.getActualTypeArguments()[1];
        return respostaClass.getConstructor().newInstance();
    }

}
