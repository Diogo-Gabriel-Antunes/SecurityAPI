package br.com.SecurityApi.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AcoesPermitidas {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String acao;
    private Boolean permitido;
}
