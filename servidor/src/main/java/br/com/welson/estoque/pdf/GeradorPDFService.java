package br.com.welson.estoque.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import br.com.welson.estoque.util.exception.InfraestruturaException;

public class GeradorPDFService {

    public static final int MARGIN = 10;

    private static final Logger LOGGER = Logger.getLogger(GeradorPDFService.class);

    public File geraPDF(String html) throws InfraestruturaException {
        FileOutputStream file = null;
        try {
            File arquivo = File.createTempFile(String.valueOf(System.currentTimeMillis()), ".br.com.welson.estoque.pdf");
            file = new FileOutputStream(arquivo);
            Document document = new Document(PageSize.A4, MARGIN, MARGIN, MARGIN, MARGIN);
            PdfWriter pdf = PdfWriter.getInstance(document, file);
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(pdf, document, htmlToOutputStream(html));
            document.close();
            return arquivo;
        } catch (DocumentException | IOException e) {
            throw new InfraestruturaException(e);
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    LOGGER.trace(e);
                }
            }
        }
    }

    private InputStream htmlToOutputStream(String html) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(html.getBytes());
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

}
