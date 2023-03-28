package br.com.SecurityApi.Infra;

import br.com.SecurityApi.Exceptions.Mensagem;
import br.com.SecurityApi.Models.DTO.VerificaAutenticacaoDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {

    public static ResponseEntity returnOk(Object object){
        return ResponseEntity.ok(object);
    }

    public static ResponseEntity returnBadRequest(Mensagem mensagem){
        return ResponseEntity.badRequest().body(mensagem);
    }
    public static ResponseEntity returnBadRequestVerificaAutenticacao(VerificaAutenticacaoDTO verificaAutenticacaoDTO){
        return ResponseEntity.badRequest().body(verificaAutenticacaoDTO);
    }
    public static ResponseEntity returnBadRequest(){
        return ResponseEntity.badRequest().build();
    }

    public static ResponseEntity returnNotFound(){
        Mensagem mensagem = new Mensagem();
        mensagem.setStatusCode(404);
        mensagem.setMensagem("Informação solicitada não foi encontrada");
        mensagem.setSolucao("Confirme a informaçoes que foram passadas e se persistir contate o suporte");
        return ResponseEntity.status(404).body(mensagem);
    }
}
