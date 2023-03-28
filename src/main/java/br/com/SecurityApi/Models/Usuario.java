package br.com.SecurityApi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String senha;
    private String nome;
    @ManyToMany
    private Set<Acesso> acessos;
    @OneToMany
    private List<ApiKey> apiKeys;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Role> role;
    private Boolean contaNaoEstaExpirada;
    private Boolean contaNaoEstaBloqueada;
    private Boolean contaNaoEstaCredenciaisExpiradas;
    private Boolean contaNaoEstaAtiva;
    @ManyToMany(mappedBy = "usuarios")
    private Set<TelasPermitidas> telasPermitidas;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.contaNaoEstaExpirada;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.contaNaoEstaBloqueada;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.contaNaoEstaCredenciaisExpiradas;
    }

    @Override
    public boolean isEnabled() {
        return this.contaNaoEstaAtiva;
    }
}
