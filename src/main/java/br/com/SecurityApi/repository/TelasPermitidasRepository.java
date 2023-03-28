package br.com.SecurityApi.repository;

import br.com.SecurityApi.Models.TelasPermitidas;
import br.com.SecurityApi.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TelasPermitidasRepository extends JpaRepository<TelasPermitidas, String> {

    List<TelasPermitidas> findAllByUsuarios(Usuario usuarios);
}