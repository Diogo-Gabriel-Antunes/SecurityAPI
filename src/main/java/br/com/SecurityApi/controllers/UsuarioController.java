package br.com.SecurityApi.controllers;

import br.com.SecurityApi.Models.Usuario;
import br.com.SecurityApi.repository.UsuarioRepository;
import br.com.SecurityApi.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping
    public ResponseEntity getAll(){
        List<Usuario> usuarios = usuarioRepository.findByContaNaoEstaAtivaTrue();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("key")
    public ResponseEntity getKey(HttpServletRequest request,@RequestParam("validade") String validade){
        String authorization = request.getHeader("Authorization");
        return usuarioService.getKey(authorization,validade);
    }
    @GetMapping("autenticado")
    public ResponseEntity verificaAutenticacao(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        return usuarioService.verificaAutenticacao(authorization);
    }

}
