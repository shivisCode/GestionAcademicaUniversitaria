package edu.uce.ec.comprobante.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import edu.uce.ec.comprobante.dto.DtoComprobanteCorreo;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;

@Component
public class PdfGenerador {

    public String generarPdf(DtoComprobanteCorreo comprobanteCorreo) {

        try {

            String rutaArchivo =
                    "comprobante_" + comprobanteCorreo.getCodigoComprobante() + ".pdf";

            Document document = new Document();

            PdfWriter.getInstance(
                    document,
                    new FileOutputStream(rutaArchivo)
            );

            document.open();

            document.add(new Paragraph("COMPROBANTE DE MATRÍCULA"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(
                    "Código: " + comprobanteCorreo.getCodigoComprobante()
            ));
            document.add(new Paragraph(
                    "Nro. Matrícula: " + comprobanteCorreo.getMatriculaId()
            ));
            document.add(new Paragraph(
                    "Estudiante: " + comprobanteCorreo.getNombreEstudiante()
            ));
            document.add(new Paragraph(
                    "Materia: " + comprobanteCorreo.getMateria()
            ));
            document.add(new Paragraph(
                    "Tipo: " + comprobanteCorreo.getTipoComprobante()
            ));
            document.add(new Paragraph(
                    "Fecha emisión: " + comprobanteCorreo.getFechaEmision()
            ));

            document.close();

            return rutaArchivo;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al generar PDF: " + e.getMessage()
            );
        }
    }
}
