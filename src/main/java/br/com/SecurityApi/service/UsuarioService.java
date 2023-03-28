package br.com.SecurityApi.service;

import br.com.SecurityApi.Exceptions.Mensagem;
import br.com.SecurityApi.Exceptions.SystemException;
import br.com.SecurityApi.Infra.ResponseFactory;
import br.com.SecurityApi.Models.ApiKey;
import br.com.SecurityApi.Models.Builders.MensagemBuilder;
import br.com.SecurityApi.Models.DTO.VerificaAutenticacaoDTO;
import br.com.SecurityApi.Models.Usuario;
import br.com.SecurityApi.repository.ApiKeyRepository;
import br.com.SecurityApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

@Service
public class UsuarioService {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public ResponseEntity getKey(String authorization,String validade) {
        Usuario usuario = getUsuario(authorization);
        if(usuario != null){
            gerarApiKey(usuario,validade);
            return ResponseFactory.returnOk(usuario);
        }else{
            Mensagem mensagem = new Mensagem();
            mensagem.setMensagem("Usuario não encontrado");
            mensagem.setStatusCode(400);
            mensagem.setSolucao("Verifique o token passado e se persistir contate o suporte");
            return ResponseFactory.returnBadRequest(mensagem);
        }


    }

    private Usuario getUsuario(String authorization) {
        if(authorization != null){
            String token = jwtService.replaceBearer(authorization);
            String subject = jwtService.getSubject(token);
            if(subject != null){
                Usuario usuario = (Usuario) usuarioRepository.findByEmail(subject);
                return usuario;
            }
        }
        return null;
    }

    private void gerarApiKey(Usuario usuario, String validade) {
        LocalDateTime dataDeExpiracao = gerarTempoDeExpiracao(validade);
        String newToken = jwtService.gerarToken(usuario,dataDeExpiracao.getHour());
        ApiKey apiKey = new ApiKey();
        apiKey.setApiKey(newToken);
        apiKey.setValidade(dataDeExpiracao);
        apiKey.setDataDeGeracao(LocalDateTime.now());
        if(usuario.getApiKeys() == null){
            usuario.setApiKeys(new ArrayList<>());
        }


        ApiKey apiKeySaved = apiKeyRepository.save(apiKey);
        usuario.getApiKeys().add(apiKeySaved);
        usuarioRepository.save(usuario);
    }

    public LocalDateTime gerarTempoDeExpiracao(String validade) {
        try{
            return LocalDateTime.parse(validade);
        }catch (DateTimeParseException exception){
            SystemException systemException = new SystemException();
            MensagemBuilder mensagemBuilder = new MensagemBuilder();
            Mensagem dataDeValidadeIncorreta = mensagemBuilder.setStatusCode(400)
                    .setSolucao("Tente por outra data de validade")
                    .setMensagem("Data de validade incorreta")
                    .getMensagem();
            systemException.getMensagens().add(dataDeValidadeIncorreta);
            systemException.lancarErro();
            return null;
        }
    }

    public ResponseEntity verificaAutenticacao(String authorization) {
        String token = jwtService.replaceBearer(authorization);
        String subject = jwtService.getSubject(token);
        if(subject != null){
            Usuario usuario = (Usuario) usuarioRepository.findByEmail(subject);
            VerificaAutenticacaoDTO verificaAutenticacaoDTO = new VerificaAutenticacaoDTO();
            verificaAutenticacaoDTO.setAutenticado(true);
            verificaAutenticacaoDTO.setUsuario(usuario);
            return ResponseFactory.returnOk(verificaAutenticacaoDTO);
        }else{
            VerificaAutenticacaoDTO verificaAutenticacaoDTO = new VerificaAutenticacaoDTO();
            verificaAutenticacaoDTO.setAutenticado(false);
            return ResponseFactory.returnBadRequestVerificaAutenticacao(verificaAutenticacaoDTO);
        }
    }
}
