package edu.uce.ec.comprobante.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import edu.uce.ec.comprobante.dto.DtoComprobanteCorreo;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.InputStream;

@Component
public class PdfGenerador {

    public String generarPdf(DtoComprobanteCorreo comprobanteCorreo) {

        try {
            String rutaArchivo =
                    "comprobante_" + comprobanteCorreo.getCodigoComprobante() + ".pdf";

            Document document = new Document(PageSize.A4, 50, 50, 40, 40);

            PdfWriter.getInstance(
                    document,
                    new FileOutputStream(rutaArchivo)
            );

            document.open();

            // Logo
            try {
                InputStream logoStream = getClass()
                        .getResourceAsStream("/static/UCE_LOGO.png");

                if (logoStream != null) {
                    Image logo = Image.getInstance(logoStream.readAllBytes());
                    logo.scaleToFit(90, 90);
                    logo.setAlignment(Image.ALIGN_CENTER);
                    document.add(logo);
                }
            } catch (Exception e) {
                System.out.println("No se pudo cargar el logo: " + e.getMessage());
            }

            Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Font subtituloFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font textoFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL);

            Paragraph universidad = new Paragraph(
                    "UNIVERSIDAD CENTRAL DEL ECUADOR",
                    tituloFont
            );
            universidad.setAlignment(Element.ALIGN_CENTER);
            document.add(universidad);

            Paragraph titulo = new Paragraph(
                    "COMPROBANTE DE MATRÍCULA",
                    subtituloFont
            );
            titulo.setAlignment(Element.ALIGN_CENTER);
            titulo.setSpacingAfter(20);
            document.add(titulo);

            LineSeparatorCustom.addLine(document);

            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(100);
            tabla.setSpacingBefore(15);
            tabla.setWidths(new float[]{35, 65});

            agregarFila(tabla, "Código Comprobante:", comprobanteCorreo.getCodigoComprobante(), subtituloFont, textoFont);
            agregarFila(tabla, "Nro. Matrícula:", String.valueOf(comprobanteCorreo.getMatriculaId()), subtituloFont, textoFont);
            agregarFila(tabla, "Estudiante:", comprobanteCorreo.getNombreEstudiante(), subtituloFont, textoFont);
            agregarFila(tabla, "Materia:", comprobanteCorreo.getMateria(), subtituloFont, textoFont);
            agregarFila(tabla, "Código Materia:", comprobanteCorreo.getCodigoMateria(), subtituloFont, textoFont);
            agregarFila(tabla, "Tipo:", comprobanteCorreo.getTipoComprobante(), subtituloFont, textoFont);
            agregarFila(tabla, "Fecha Emisión:", String.valueOf(comprobanteCorreo.getFechaEmision()), subtituloFont, textoFont);

            document.add(tabla);

            Paragraph nota = new Paragraph(
                    "\nEste documento certifica que el estudiante ha realizado el proceso de matrícula académica correspondiente.",
                    textoFont
            );
            nota.setAlignment(Element.ALIGN_JUSTIFIED);
            nota.setSpacingBefore(20);
            document.add(nota);

            Paragraph firma = new Paragraph(
                    "\n\n______________________________\nSecretaría Académica",
                    textoFont
            );
            firma.setAlignment(Element.ALIGN_CENTER);
            firma.setSpacingBefore(40);
            document.add(firma);

            document.close();

            return rutaArchivo;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al generar PDF: " + e.getMessage()
            );
        }
    }

    private void agregarFila(
            PdfPTable tabla,
            String campo,
            String valor,
            Font campoFont,
            Font valorFont) {

        PdfPCell celdaCampo = new PdfPCell(new Phrase(campo, campoFont));
        celdaCampo.setPadding(8);

        PdfPCell celdaValor = new PdfPCell(new Phrase(valor != null ? valor : "", valorFont));
        celdaValor.setPadding(8);

        tabla.addCell(celdaCampo);
        tabla.addCell(celdaValor);
    }

    static class LineSeparatorCustom {
        static void addLine(Document document) throws DocumentException {
            Paragraph line = new Paragraph("____________________________________________________________");
            line.setAlignment(Element.ALIGN_CENTER);
            line.setSpacingAfter(10);
            document.add(line);
        }
    }
}