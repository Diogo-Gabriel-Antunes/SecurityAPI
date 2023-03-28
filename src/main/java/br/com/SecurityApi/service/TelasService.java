package br.com.SecurityApi.service;


import br.com.SecurityApi.Models.TelasPermitidas;
import br.com.SecurityApi.Models.Usuario;
import br.com.SecurityApi.repository.TelasPermitidasRepository;
import br.com.SecurityApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelasService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TelasPermitidasRepository telasPermitidasRepository;

    public ResponseEntity getTelas(String tokenAuthorization) {
        String tokenReplaced = jwtService.replaceBearer(tokenAuthorization);
        String subject = jwtService.getSubject(tokenReplaced);
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(subject);
        List<TelasPermitidas> telasPermitidas = telasPermitidasRepository.findAllByUsuarios(usuario);
        return ResponseEntity.ok(telasPermitidas);
    }
}
