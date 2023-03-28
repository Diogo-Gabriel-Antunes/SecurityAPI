package br.com.SecurityApi.service;

import br.com.SecurityApi.Exceptions.SystemException;
import br.com.SecurityApi.Models.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTService {

    public String gerarToken(Usuario usuario) {
        Boolean usuarioBloqueado = !usuario.getContaNaoEstaAtiva() || !usuario.getContaNaoEstaBloqueada() || !usuario.getContaNaoEstaCredenciaisExpiradas() || !usuario.getContaNaoEstaExpirada();
        try {
            Algorithm algorithm = Algorithm.HMAC256("teste");
            return JWT.create()
                    .withIssuer("teste")
                    .withClaim("idUsuario",usuario.getId())
                    .withClaim("Nome",usuario.getNome())
                    .withClaim("Role",usuario.getRole().toString())
                    .withClaim("UsuarioBloquado",usuarioBloqueado)
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(getExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException jwtCreationException) {
            throw new RuntimeException("Erro ao gerar token",jwtCreationException);
        }
    }

    public String gerarToken(Usuario usuario,Integer tempoDeExpiracao){
        Boolean usuarioBloqueado = !usuario.getContaNaoEstaAtiva() || !usuario.getContaNaoEstaBloqueada() || !usuario.getContaNaoEstaCredenciaisExpiradas() || !usuario.getContaNaoEstaExpirada();
        try {
            Algorithm algorithm = Algorithm.HMAC256("teste");
            return JWT.create()
                    .withIssuer("teste")
                    .withClaim("idUsuario",usuario.getId())
                    .withClaim("Nome",usuario.getNome())
                    .withClaim("Role",usuario.getRole().toString())
                    .withClaim("UsuarioBloquado",usuarioBloqueado)
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(getExpiracao(tempoDeExpiracao))
                    .sign(algorithm);
        } catch (JWTCreationException jwtCreationException) {
            SystemException exception = new SystemException();
            exception.add("Erro ao gerar Token",400,"tente novamente se o erro persistir contate o suporte");
            exception.lancarErro();
            return null;
        }
    }
    public String getSubject(String tokenJWT){
        Algorithm algoritmo = Algorithm.HMAC256("teste");
        return JWT.require(algoritmo)
                .withIssuer("teste")
                .build()
                .verify(tokenJWT)
                .getSubject();
    }
    public String replaceBearer(String token){
        return token.replace("Bearer ", "");
    }

    private Instant getExpiracao() {
        return LocalDateTime.now().plusHours(24).toInstant(ZoneOffset.of("-03:00"));
    }
    private Instant getExpiracao(Integer expiracao) {
        if(expiracao != null){
            return LocalDateTime.now().plusHours(expiracao).toInstant(ZoneOffset.of("-03:00"));
        }else{
            return getExpiracao();
        }
    }
}
