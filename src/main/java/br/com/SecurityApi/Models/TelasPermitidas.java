package br.com.SecurityApi.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class TelasPermitidas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToMany
    private List<Telas> telas;

    private Boolean permitido;
    @ManyToMany
    private Set<Usuario> usuarios;
    @OneToMany
    private List<AcoesPermitidas> acoesPermitidas;


}
