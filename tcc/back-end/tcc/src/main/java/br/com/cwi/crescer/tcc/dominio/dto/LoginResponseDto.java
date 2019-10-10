package br.com.cwi.crescer.tcc.dominio.dto;

public class LoginResponseDto {

    private String email;

    private String token;

    private Long id;

    public LoginResponseDto(String email, String token, Long id) {
        this.email = email;
        this.token = token;
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
