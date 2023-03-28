package br.com.SecurityApi.Config.errorConfig;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfiguracaoHandleError implements WebMvcConfigurer {

    @Autowired
    private ErrorAttributes errorAttributes;

    @Bean
    public  CustomErrorController customErrorController(){
        return new CustomErrorController(errorAttributes);
    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable("Teste");
    }


}
