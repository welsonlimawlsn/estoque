package br.com.welson.estoque.relatorio.ejb;

import br.com.welson.estoque.funcionalidade.CodigoFuncionalidade;
import br.com.welson.estoque.funcionalidade.anotacao.Funcionalidade;
import br.com.welson.estoque.relatorio.DownloadRelatorioRequisicaoDTO;
import br.com.welson.estoque.relatorio.RelatorioService;
import br.com.welson.estoque.relatorio.dao.RelatorioDAO;
import br.com.welson.estoque.relatorio.entidade.Relatorio;
import br.com.welson.estoque.requisicao.anotacao.RequisicaoService;
import br.com.welson.estoque.util.EstoqueErro;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import br.com.welson.estoque.util.exception.NegocioException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Stateless
@RequisicaoService
public class RelatorioServiceImpl implements RelatorioService
{
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir") + File.separatorChar;

    private static final String CONTENT_DISPOSITION = "Content-Disposition";

    @Inject
    private RelatorioDAO relatorioDAO;

    @Override
    public Response downloadRelatorio(
            @Funcionalidade(CodigoFuncionalidade.DOWNLOAD_RELATORIO) DownloadRelatorioRequisicaoDTO requisicao)
            throws NegocioException, InfraestruturaException
    {
        if (requisicao.getCliente() == null)
        {
            throw new NegocioException(EstoqueErro.FUNCIONALIDADE_REQUER_AUTENTICACAO);
        }

        Relatorio dadosRelatorio = relatorioDAO.buscaRelatorioValidoPorNomeECliente(requisicao.getNomeRelatorio(), requisicao.getCliente())
                .orElseThrow(() -> new NegocioException(EstoqueErro.NENHUM_RELATORIO_ENCONTRADO));

        dadosRelatorio.setInvalidado(true);
        try
        {
            FileInputStream fileInputStream = new FileInputStream(TEMP_DIR + dadosRelatorio.getNome());

            return Response
                    .status(Response.Status.OK)
                    .type(MediaType.APPLICATION_OCTET_STREAM_TYPE)
                    .header(CONTENT_DISPOSITION, "attachment; filename=\"" + dadosRelatorio.getNome() + "\"")
                    .entity(fileInputStream)
                    .build();
        }
        catch (IOException e)
        {
            throw new InfraestruturaException(e);
        }
    }
}
