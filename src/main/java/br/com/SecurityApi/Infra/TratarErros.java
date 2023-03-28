package br.com.SecurityApi.Infra;

import br.com.SecurityApi.Exceptions.Mensagem;
import br.com.SecurityApi.Exceptions.SystemException;
import br.com.SecurityApi.Models.Builders.MensagemBuilder;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratarErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity tratarTokenException(SystemException exception) {
        String message = exception.getMessage();
        List<Mensagem> mensagens = exception.getMensagens();
        System.out.println(mensagens.toString());
        System.out.println(message);
        return ResponseEntity.status(400).body(exception.getMensagens());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity tratarAcessoNegado(AccessDeniedException exception){
        MensagemBuilder mensagemBuilder = new MensagemBuilder();
        Mensagem mensagem = mensagemBuilder.setMensagem("Acesso negado")
                .setStatusCode(400)
                .setSolucao("Tente fazer login novamente ou gerar uma nova chave, se o erro persistir contate o suporte")
                .getMensagem();
        return ResponseFactory.returnOk(mensagem);


    }
}

