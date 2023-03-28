package br.com.SecurityApi.Config.errorConfig;

import br.com.SecurityApi.Exceptions.Mensagem;
import br.com.SecurityApi.Infra.ResponseFactory;
import br.com.SecurityApi.Models.Builders.MensagemBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class CustomErrorController extends AbstractErrorController {
    public CustomErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }
    @Override
    protected ModelAndView resolveErrorView(HttpServletRequest request, HttpServletResponse response, HttpStatus status, Map<String, Object> model) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            response.sendRedirect("/error");
            return new ModelAndView().addObject(handleError(request));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @RequestMapping("/error")
    public ResponseEntity handleError(HttpServletRequest request){

        MensagemBuilder mensagemBuilder = new MensagemBuilder();
        Mensagem mensagem = mensagemBuilder.setMensagem("Acesso negado, Tente novamente")
                .setSolucao("Faça login novamente ou tente uma nova chave api")
                .setStatusCode(400)
                .getMensagem();
        return ResponseFactory.returnBadRequest(mensagem);
    }
}
