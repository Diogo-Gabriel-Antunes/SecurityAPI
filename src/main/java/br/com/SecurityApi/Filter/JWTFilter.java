package br.com.SecurityApi.Filter;

import br.com.SecurityApi.Exceptions.Mensagem;
import br.com.SecurityApi.Exceptions.SystemException;
import br.com.SecurityApi.Models.Builders.MensagemBuilder;
import br.com.SecurityApi.repository.UsuarioRepository;
import br.com.SecurityApi.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!request.getRequestURL().toString().contains("/login")){
            var tokenJWT = recuperarToken(request);
            if(tokenJWT.contains("Token invalido")){
                throw new SystemException(tokenJWT);
            }
            var subject = jwtService.getSubject(tokenJWT);
            var usuario = usuarioRepository.findByEmail(subject);
            var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request,response);
        }else{
            filterChain.doFilter(request,response);
        }
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        var tokenApiKey = request.getHeader("API-KEY");
        if(authorizationHeader == null && tokenApiKey == null){
            MensagemBuilder mensagemBuilder = new MensagemBuilder();
            return mensagemBuilder.setSolucao("Tente fazer login novamente ou gere uma nova chave api")
                    .setStatusCode(400)
                    .setMensagem("Token invalido")
                    .getJson();
        }
        if(tokenApiKey != null){
            return tokenApiKey;
        }else{
            return jwtService.replaceBearer(authorizationHeader);
        }
    }
}
