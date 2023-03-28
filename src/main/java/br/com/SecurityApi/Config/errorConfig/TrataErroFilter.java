package br.com.SecurityApi.Config.errorConfig;

import br.com.SecurityApi.Exceptions.Mensagem;
import br.com.SecurityApi.Infra.ResponseFactory;
import br.com.SecurityApi.Models.Builders.MensagemBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.coyote.Response;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
@RestControllerAdvice
public class TrataErroFilter implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        MensagemBuilder mensagemBuilder = new MensagemBuilder();
        Mensagem mensagem = mensagemBuilder.setMensagem("Acesso negado")
                .setStatusCode(400)
                .setSolucao("Tente fazer login novamente ou gerar uma nova chave, se o erro persistir contate o suporte")
                .getMensagem();
        modelAndView.addObject(ResponseFactory.returnBadRequest(mensagem));
        return modelAndView;
    }
}
