package edu.uce.ec.comprobante.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

import java.io.File;

@Service
public class EmailServicio {

    @Autowired
    private JavaMailSender mailSender;

    public void enviarComprobante(
            String destinatario,
            String asunto,
            String mensaje,
            String rutaPdf) {

        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, true);

            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(mensaje);

            FileSystemResource archivo =
                    new FileSystemResource(new File(rutaPdf));

            helper.addAttachment(
                    archivo.getFilename(),
                    archivo
            );

            mailSender.send(mimeMessage);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error al enviar correo: " + e.getMessage()
            );
        }
    }
}