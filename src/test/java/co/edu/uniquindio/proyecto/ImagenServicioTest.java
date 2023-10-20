package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.servicios.interfaces.ImagenesServicios;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
    @Transactional
    public class ImagenServicioTest {

        @Autowired
        private ImagenesServicios imagenesServicio;

        @Test
        public void subirImagenTest() throws Exception {
            imagenesServicio.subirImagen(null);
            Object file = new Object();
            Cloudinary cloudinary = null;
            cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", "projectClinica"));
        }

        @Test
        public void eliminarImagenTest() throws Exception {
            imagenesServicio.eliminarImagen("projectClinica/1");
        }
    }

