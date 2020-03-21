package br.com.welson.estoque.relatorio;

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

import javax.ws.rs.core.StreamingOutput;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import br.com.welson.estoque.relatorio.anotacao.Relatorio;
import br.com.welson.estoque.util.exception.InfraestruturaException;

public class ProcessadorRelatorioJasperReportsImpl implements ProcessadorRelatorio {

    @Override
    public void processaRelatorio(RelatorioRequisicaoDTO<?> respostaRelatorio) throws InfraestruturaException {
        try (InputStream jasperFile = carregaTemplate(respostaRelatorio.getResposta())) {

            JasperPrint jasperPrint = processaTemplate(respostaRelatorio.getResposta(), jasperFile);

            Object relatorio = exporta(jasperPrint, respostaRelatorio.getFormato());

            respostaRelatorio.getResposta().setRelatorio(relatorio);
        } catch (JRException | IOException e) {
            throw new InfraestruturaException(e);
        }
    }

    private JasperPrint processaTemplate(RelatorioRespostaDTO<?> respostaRelatorio, InputStream jasperFile) throws JRException {
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(respostaRelatorio.getLista());
        return JasperFillManager.fillReport(jasperReport, respostaRelatorio.getParametros(), dataSource);
    }

    private InputStream carregaTemplate(RelatorioRespostaDTO<?> respostaRelatorio) {
        return getClass().getClassLoader().getResourceAsStream(respostaRelatorio.getClass().getAnnotation(Relatorio.class).value());
    }

    private File exportaPDF(JasperPrint jasperPrint) throws IOException, JRException {
        File relatorio = File.createTempFile("relatorio", ".pdf");
        try (OutputStream outputStream = new FileOutputStream(relatorio)) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        }
        return relatorio;
    }

    private Object exporta(JasperPrint jasperPrint, FormatoRelatorio formatoRelatorio) throws JRException, IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            switch (formatoRelatorio) {
                case PDF:
                    JasperExportManager.exportReportToPdfStream(jasperPrint, byteArrayOutputStream);
                    break;
                case XLS:
                    JRXlsExporter exporterXls = new JRXlsExporter();
                    exporterXls.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporterXls.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
                    exporterXls.exportReport();
                    break;
                case RTF:
                    JRRtfExporter exporterRtf = new JRRtfExporter();
                    exporterRtf.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporterRtf.setExporterOutput(new SimpleWriterExporterOutput(byteArrayOutputStream));
                    exporterRtf.exportReport();
            }
            return (StreamingOutput) byteArrayOutputStream::writeTo;
        }
    }

}
