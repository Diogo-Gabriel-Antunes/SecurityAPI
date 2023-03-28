package br.com.SecurityApi.repository;

import br.com.SecurityApi.Models.ApiKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiKeyRepository extends JpaRepository<ApiKey, String> {
}