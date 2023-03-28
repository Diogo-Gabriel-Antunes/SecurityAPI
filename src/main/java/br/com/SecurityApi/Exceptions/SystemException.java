package br.com.SecurityApi.Exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SystemException extends AuthenticationException {

    List<Mensagem> mensagens = new ArrayList<>();

    public SystemException(List<Mensagem> mensagens) {
        super(mensagens.toString());
    }
    public SystemException(String mensagem) {
        super(mensagem);
    }

    public SystemException() {
        super(null);

    }

    public void add(String mensagem, Integer statusCode, String solucao){
        Mensagem mensagemObject = new Mensagem();
        mensagemObject.setMensagem(mensagem);
        mensagemObject.setStatusCode(statusCode);
        mensagemObject.setSolucao(solucao);
        mensagens.add(mensagemObject);
    }

    public void lancarErro()   {
        if(!mensagens.isEmpty()){
            throw new SystemException(mensagens);
        }
    }
}
