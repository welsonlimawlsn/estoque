package br.com.welson.estoque.relatorio;

import br.com.welson.estoque.relatorio.anotacao.Relatorio;
import br.com.welson.estoque.util.exception.InfraestruturaException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ProcessadorRelatorioJasperReportsImpl implements ProcessadorRelatorio
{

    @Override
    public void processaRelatorio(RelatorioRequisicaoDTO<?> respostaRelatorio) throws InfraestruturaException
    {
        try (InputStream jasperFile = carregaTemplate(respostaRelatorio.getResposta()))
        {
            JasperPrint jasperPrint = processaTemplate(respostaRelatorio.getResposta(), jasperFile);

            Relatorio annotation = respostaRelatorio.getResposta().getClass().getAnnotation(Relatorio.class);

            File relatorio = exporta(jasperPrint, respostaRelatorio.getFormato(), annotation.nomeArquivoFinal());

            respostaRelatorio.getResposta().setNomeRelatorioGerado(relatorio.getName());
        }
        catch (JRException | IOException e)
        {
            throw new InfraestruturaException(e);
        }
    }

    private JasperPrint processaTemplate(RelatorioRespostaDTO<?> respostaRelatorio, InputStream jasperFile) throws JRException
    {
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(respostaRelatorio.getLista());
        return JasperFillManager.fillReport(jasperReport, respostaRelatorio.getParametros(), dataSource);
    }

    private InputStream carregaTemplate(RelatorioRespostaDTO<?> respostaRelatorio)
    {
        return getClass().getClassLoader().getResourceAsStream(respostaRelatorio.getClass().getAnnotation(Relatorio.class).template());
    }

    private File exportaPDF(JasperPrint jasperPrint) throws IOException, JRException
    {
        File relatorio = File.createTempFile("relatorio", ".pdf");
        try (OutputStream outputStream = new FileOutputStream(relatorio))
        {
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        }
        return relatorio;
    }

    private File exporta(JasperPrint jasperPrint, FormatoRelatorio formatoRelatorio, String nomeArquivo) throws JRException, IOException
    {
        File tempFile = File.createTempFile(nomeArquivo + "_", formatoRelatorio.getExtensao());
        try (FileOutputStream fileOutputStream = new FileOutputStream(tempFile))
        {
            switch (formatoRelatorio)
            {
                case PDF:
                    JasperExportManager.exportReportToPdfStream(jasperPrint, fileOutputStream);
                    break;
                case XLS:
                    JRXlsExporter exporterXls = new JRXlsExporter();
                    exporterXls.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporterXls.setExporterOutput(new SimpleOutputStreamExporterOutput(fileOutputStream));
                    exporterXls.exportReport();
                    break;
                case RTF:
                    JRRtfExporter exporterRtf = new JRRtfExporter();
                    exporterRtf.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporterRtf.setExporterOutput(new SimpleWriterExporterOutput(fileOutputStream));
                    exporterRtf.exportReport();
            }
        }
        return tempFile;
    }

}
