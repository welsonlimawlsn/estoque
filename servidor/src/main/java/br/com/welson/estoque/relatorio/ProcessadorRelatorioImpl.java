package br.com.welson.estoque.relatorio;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import javax.inject.Inject;
import java.io.File;
import java.io.StringWriter;

import br.com.welson.estoque.pdf.GeradorPDFService;
import br.com.welson.estoque.relatorio.anotacao.Relatorio;
import br.com.welson.estoque.util.exception.InfraestruturaException;

public class ProcessadorRelatorioImpl implements ProcessadorRelatorio {

    @Inject
    private GeradorPDFService pdfService;

    @Override
    public void processaRelatorio(RespostaRelatorio respostaRelatorio) throws InfraestruturaException {

        Relatorio annotation = respostaRelatorio.getClass().getAnnotation(Relatorio.class);

        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADERS, "classpath");
        velocityEngine.setProperty("resource.loader.classpath.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        VelocityContext velocityContext = new VelocityContext();

        velocityContext.put("relatorio", respostaRelatorio);

        Template template = velocityEngine.getTemplate(annotation.vm());

        StringWriter stringWriter = new StringWriter();

        template.merge(velocityContext, stringWriter);

        File file = pdfService.geraPDF(stringWriter.toString());

        respostaRelatorio.setRelatorio(file);
    }

}
