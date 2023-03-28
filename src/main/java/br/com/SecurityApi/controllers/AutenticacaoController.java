package br.com.SecurityApi.controllers;

import br.com.SecurityApi.Models.DTO.AutenticacaoDTO;
import br.com.SecurityApi.Models.DTO.RetornoDTO;
import br.com.SecurityApi.Models.Usuario;
import br.com.SecurityApi.service.AutenticaoService;
import br.com.SecurityApi.service.JWTService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager maneger;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AutenticaoService autenticaoService;
    @PostMapping
    public ResponseEntity efetuarLogin(@Valid @RequestBody AutenticacaoDTO autenticacaoDTO){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(autenticacaoDTO.getEmail(), autenticacaoDTO.getSenha());
        Authentication authenticate = maneger.authenticate(usernamePasswordAuthenticationToken);
        Usuario usuario = (Usuario) authenticate.getPrincipal();
        String token = jwtService.gerarToken(usuario);
        RetornoDTO retorno = new RetornoDTO();
        retorno.setToken(token);
        retorno.setUsuario(usuario);
        autenticaoService.gerarAcesso(usuario);
        return ResponseEntity.ok(retorno);
    }

}
