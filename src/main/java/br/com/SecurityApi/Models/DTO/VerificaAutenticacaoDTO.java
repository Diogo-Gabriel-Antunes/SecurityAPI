package br.com.SecurityApi.Models.DTO;

import br.com.SecurityApi.Models.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificaAutenticacaoDTO {

    private Boolean autenticado;
    private Usuario usuario;

}
