package br.com.SecurityApi.service;

import br.com.SecurityApi.Models.Acesso;
import br.com.SecurityApi.Models.Usuario;
import br.com.SecurityApi.repository.AcessoRepository;
import br.com.SecurityApi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AutenticaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AcessoRepository acessoRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email);
    }

    public void gerarAcesso(Usuario usuario) {
        Acesso acesso = new Acesso();
        acesso.setHoraDoUltimoAcesso(LocalDateTime.now());
        Acesso acessoSaved = acessoRepository.save(acesso);
        usuario.getAcessos().add(acessoSaved);
        usuarioRepository.save(usuario);

    }
}
