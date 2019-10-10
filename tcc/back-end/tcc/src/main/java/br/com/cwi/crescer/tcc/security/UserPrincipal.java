package br.com.cwi.crescer.tcc.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import br.com.cwi.crescer.tcc.dominio.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrincipal implements UserDetails {

    private static final String DEFAULT_ROLE = "ROLE_USER";

    private Long id;

    private String nome;

    private String apelido;

    private String email;

    @JsonIgnore
    private String senha;

    private Collection<? extends GrantedAuthority> authorities;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return this.apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UserPrincipal(Long id, String nome, String apelido, String email, String senha, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Usuario usuario) {
        List<GrantedAuthority> authorities = Arrays.asList(
            new SimpleGrantedAuthority(usuario.getRole().orElse(DEFAULT_ROLE))
        );

        return new UserPrincipal(
            usuario.getId(),
            usuario.getNomeCompleto(),
            usuario.getApelido(),
            usuario.getEmail(),
            usuario.getSenha(),
            authorities
        );
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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