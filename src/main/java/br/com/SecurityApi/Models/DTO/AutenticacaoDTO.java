package br.com.SecurityApi.Models.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutenticacaoDTO {
    @NotNull(message = "email deve ser passado")
    @NotBlank(message = "Por favor digite um email valido")
    @NotEmpty(message = "Eemail não pode estar vazio")
    private String email;
    @NotNull(message = "Senha deve ser passada")
    @NotBlank(message = "Por favor digite uma senha valida")
    @NotEmpty(message = "Senha não pode estar vazia")
    private String senha;
}
