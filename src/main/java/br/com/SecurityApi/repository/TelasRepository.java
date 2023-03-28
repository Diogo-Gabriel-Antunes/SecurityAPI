package br.com.SecurityApi.repository;

import br.com.SecurityApi.Models.Telas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelasRepository extends JpaRepository<Telas, String> {

    Telas findTelasByNomeTela (String nomeDaTela);
}