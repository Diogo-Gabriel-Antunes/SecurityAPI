package br.com.SecurityApi.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Telas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true)
    private String nomeTela;
    @Column(unique = true)
    private String slug;

    public Telas(String nomeTela, String slug) {
        this.nomeTela = nomeTela;
        this.slug = slug;
    }

    public Telas() {

    }
}
