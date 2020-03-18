package br.com.welson.estoque.relatorio;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.enterprise.inject.Default;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import br.com.welson.estoque.relatorio.anotacao.Relatorio;
import br.com.welson.estoque.util.exception.InfraestruturaException;

public class ProcessadorRelatorioJasperReportsImpl implements ProcessadorRelatorio {

    @Override
    public void processaRelatorio(RespostaRelatorio<?> respostaRelatorio) throws InfraestruturaException {
        try {
            InputStream jasperFile = carregaTemplate(respostaRelatorio);

            JasperPrint jasperPrint = processaTemplate(respostaRelatorio, jasperFile);

            File relatorio = exportaPDF(jasperPrint);

            respostaRelatorio.setRelatorio(relatorio);
        } catch (JRException | IOException e) {
            throw new InfraestruturaException(e);
        }
    }

    private JasperPrint processaTemplate(RespostaRelatorio<?> respostaRelatorio, InputStream jasperFile) throws JRException {
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(respostaRelatorio.getLista());
        return JasperFillManager.fillReport(jasperReport, respostaRelatorio.getParametros(), dataSource);
    }

    private InputStream carregaTemplate(RespostaRelatorio<?> respostaRelatorio) {
        return getClass().getClassLoader().getResourceAsStream(respostaRelatorio.getClass().getAnnotation(Relatorio.class).value());
    }

    private File exportaPDF(JasperPrint jasperPrint) throws IOException, JRException {
        File relatorio = File.createTempFile("relatorio", ".pdf");
        try (OutputStream outputStream = new FileOutputStream(relatorio)) {
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        }
        return relatorio;
    }

}
