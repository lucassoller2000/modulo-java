package br.com.cwi.crescer.tcc.web;

import br.com.cwi.crescer.tcc.dominio.dto.CurtidaDto;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import br.com.cwi.crescer.tcc.service.curtida.RemoverCurtidaService;
import br.com.cwi.crescer.tcc.service.curtida.SalvarCurtidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/curtida")
@RestController
public class CurtidaController {
    @Autowired
    SalvarCurtidaService salvarCurtidaService;

    @Autowired
    RemoverCurtidaService removerCurtidaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void incluir(@RequestBody CurtidaDto curtida, @AuthenticationPrincipal UserPrincipal userPrincipal){
        salvarCurtidaService.salvar(curtida, userPrincipal);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id, @AuthenticationPrincipal UserPrincipal userPrincipal){
        removerCurtidaService.remover(id);
    }
}
