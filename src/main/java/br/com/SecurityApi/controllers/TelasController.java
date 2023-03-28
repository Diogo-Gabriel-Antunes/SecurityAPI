package br.com.SecurityApi.controllers;

import br.com.SecurityApi.service.JWTService;
import br.com.SecurityApi.service.TelasService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("telas")
public class TelasController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private TelasService telasService;


    @GetMapping
    public ResponseEntity getTelas(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        return telasService.getTelas(token);
    }
}
