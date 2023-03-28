package br.com.SecurityApi.repository;

import br.com.SecurityApi.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}