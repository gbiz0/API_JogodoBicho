package com.gbizo.API_JogodoBicho.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity(name = "contraventor")
@Table(name = "contraventor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class contraventor  implements UserDetails {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id_cont;
    String nome_cont, tipo_cont;
    @Column(unique = true, nullable = false)
    private String login, password;
    String cpf_cont;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(Objects.equals(tipo_cont, "admin")) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
