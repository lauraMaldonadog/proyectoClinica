import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.servicios.interfaces.EmailServicios;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component

public class EmailServicioTest {

@Autowired
    private EmailServicios emailServicios;

    private final JavaMailSender javaMailSender = new JavaMailSenderImpl();





    @Test
    public void enviarCorreo() throws Exception {

        EmailDTO emailDTO = new EmailDTO("juanm.londonom@uqvirtual.edu.co", "Prueba 1", "Hola");

        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);
        helper.setSubject(emailDTO.asunto());
        helper.setText(emailDTO.mensaje(), true);
        helper.setTo(emailDTO.para());
        helper.setFrom("clinicasaludyvida515@gmail.com");
        javaMailSender.send(mensaje);
    }


}
