package br.com.SecurityApi.Infra;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEvents {

    @EventListener
    public void onSucess(AuthenticationSuccessEvent sucess){

    }
    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failure){
        failure.getException().printStackTrace();
    }
}
