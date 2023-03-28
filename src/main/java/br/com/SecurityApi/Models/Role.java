package br.com.SecurityApi.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private Integer poderHierarquico;
    private String cargo;

    @Override
    public String getAuthority() {
        return this.cargo;
    }

    @Override
    public String toString() {
        return "Role{" +
                "poderHierarquico=" + poderHierarquico +
                '}';
    }
}
