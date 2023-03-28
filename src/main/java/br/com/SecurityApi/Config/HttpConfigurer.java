package br.com.SecurityApi.Config;

import br.com.SecurityApi.Filter.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class HttpConfigurer extends AbstractHttpConfigurer<HttpConfigurer, HttpSecurity> {
//    @Autowired
//    JWTFilter jwtFilter;
//    @Override
//    public void init(HttpSecurity http) throws Exception {
//
//
//
//        http.csrf().disable()
//                .cors().disable()
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests().requestMatchers("/**").permitAll();
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        ApplicationContext context = http.getSharedObject(ApplicationContext.class);
//
//        JWTFilter myFilter = context.getBean(JWTFilter.class);
//        context.
//        http.addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class);
//    }




}
