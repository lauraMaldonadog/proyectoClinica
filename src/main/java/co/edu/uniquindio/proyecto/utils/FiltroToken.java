package co.edu.uniquindio.proyecto.utils;

import io.jsonwebtoken.*;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class FiltroToken extends OncePerRequestFilter {

    private final JWTUtils jwtUtils;


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
// Configuración de cabeceras para CORS
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        res.addHeader("Access-Control-Allow-Headers", "Origin, Accept, Content-Type, Authorization");
        res.addHeader("Access-Control-Allow-Credentials", "true");
        if (req.getMethod().equals("OPTIONS")) {
            res.setStatus(HttpServletResponse.SC_OK);
        }else {
            String requestURI = req.getRequestURI();
            String token = getToken(req);
            boolean error = true;
//Acá va lo mismo que teníamos en el método anterior. Lo de las rutas y validaciones.


            try {
                if (token != null) {
                    Jws<Claims> jws = jwtUtils.parseJwt(token);
                    //Acá haremos las validaciones de autorización de acuerdo al rol del usuario
                    System.out.println(jws.getBody().getSubject());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            filterChain.doFilter(req, res);
        }


    }







    private String getToken(HttpServletRequest req) {
        String header = req.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer "))
            return header.replace("Bearer ", "");
        return null;
    }

}
