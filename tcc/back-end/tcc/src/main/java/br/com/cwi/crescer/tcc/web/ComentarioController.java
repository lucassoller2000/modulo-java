package br.com.cwi.crescer.tcc.web;

import br.com.cwi.crescer.tcc.dominio.dto.ComentarioDto;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import br.com.cwi.crescer.tcc.service.comentario.RemoverComentarioService;
import br.com.cwi.crescer.tcc.service.comentario.SalvarComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/comentario")
@RestController
public class ComentarioController {
    @Autowired
    SalvarComentarioService salvarComentarioService;

    @Autowired
    RemoverComentarioService removerComentarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void incluir(@RequestBody ComentarioDto comentario, @AuthenticationPrincipal UserPrincipal userPrincipal){
        salvarComentarioService.salvar(comentario, userPrincipal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id, @AuthenticationPrincipal UserPrincipal userPrincipal){
        removerComentarioService.remover(id);
    }
}
