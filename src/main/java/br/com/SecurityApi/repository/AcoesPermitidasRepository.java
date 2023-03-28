package br.com.SecurityApi.repository;

import br.com.SecurityApi.Models.AcoesPermitidas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcoesPermitidasRepository extends JpaRepository<AcoesPermitidas, String> {
}