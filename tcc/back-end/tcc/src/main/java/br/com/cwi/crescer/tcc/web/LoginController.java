package br.com.cwi.crescer.tcc.web;

import br.com.cwi.crescer.tcc.dominio.dto.LoginRequestDto;
import br.com.cwi.crescer.tcc.dominio.dto.LoginResponseDto;
import br.com.cwi.crescer.tcc.service.usuario.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("public/login")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){
        return loginService.logar(loginRequestDto);
    }
}
