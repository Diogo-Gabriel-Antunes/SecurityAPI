package br.com.SecurityApi.repository;

import br.com.SecurityApi.Models.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcessoRepository extends JpaRepository<Acesso, String> {
}