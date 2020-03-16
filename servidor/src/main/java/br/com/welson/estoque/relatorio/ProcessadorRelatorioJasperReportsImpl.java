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

import br.com.welson.estoque.relatorio.anotacao.Relatorio;
import br.com.welson.estoque.util.exception.InfraestruturaException;

@Default
public class ProcessadorRelatorioJasperReportsImpl implements ProcessadorRelatorio {

    @Override
    public void processaRelatorio(RespostaRelatorio respostaRelatorio) throws InfraestruturaException {
        FileOutputStream outputStream = null;

        try {
            InputStream jasperFile = carregaTemplate(respostaRelatorio);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(respostaRelatorio.getLista());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

            File relatorio = File.createTempFile("relatorio", ".pdf");
            outputStream = new FileOutputStream(relatorio);
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            respostaRelatorio.setRelatorio(relatorio);
        } catch (JRException | IOException e) {
            throw new InfraestruturaException(e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private InputStream carregaTemplate(RespostaRelatorio respostaRelatorio) {
        return getClass().getClassLoader().getResourceAsStream(respostaRelatorio.getClass().getAnnotation(Relatorio.class).value());
    }

}
