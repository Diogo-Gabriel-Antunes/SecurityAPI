package br.com.SecurityApi.repository;

import br.com.SecurityApi.Models.ApiKey;
import br.com.SecurityApi.Models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    @Transactional
    @Modifying
    @Query("update Usuario u set u.apiKeys = ?1 where u.id = ?2")
    int updateApiKeysById(ApiKey apiKeys, @NonNull String id);
    @Transactional
    @Modifying
    @Query("update Usuario u set u.apiKeys = ?1")
    void updateApiKeysBy(ApiKey apiKeys);

    List<Usuario> findByContaNaoEstaAtivaTrue();
    UserDetails findByEmail(String email);


}
