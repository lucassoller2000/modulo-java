package br.com.cwi.crescer.tcc.web;

import br.com.cwi.crescer.tcc.dominio.dto.UsuarioDto;
import br.com.cwi.crescer.tcc.service.usuario.SalvarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("public/registro")
@RestController
public class RegistroController {

    @Autowired
    private SalvarUsuarioService salvarUsuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody UsuarioDto usuarioDto){
        salvarUsuarioService.salvar(usuarioDto);
    }
}