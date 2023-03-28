package br.com.SecurityApi.Models.DTO;

import br.com.SecurityApi.Models.Usuario;
import lombok.Data;

@Data
public class RetornoDTO {

    private String token;
    private Usuario usuario;
}
