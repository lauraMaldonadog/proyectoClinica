package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicios;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@SpringBootTest
public class EmailServicioTest {

    @Autowired
    private EmailServicios emailServicios;

    @Test
    public void enviarCorreo() throws Exception {

        EmailDTO emailDTO = new EmailDTO("estebanj.ortegat@uqvirtual.edu.co", "Prueba 1", "HolaMundode hoy");

        emailServicios.enviarCorreo(emailDTO);
    }


}
