package br.com.cwi.crescer.tcc.web;

import br.com.cwi.crescer.tcc.dominio.Amizade;
import br.com.cwi.crescer.tcc.dominio.dto.AmizadeDto;
import br.com.cwi.crescer.tcc.repository.IAmizadeRepository;
import br.com.cwi.crescer.tcc.repository.projection.AmizadeReponse;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import br.com.cwi.crescer.tcc.service.amizade.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/amizade")
@RestController
public class AmizadeController {
    @Autowired
    SalvarAmizadeService salvarAmizadeService;

    @Autowired
    AceitarAmizadeService aceitarAmizadeService;

    @Autowired
    DesfazerAmizadeService desfazerAmizadeService;

    @Autowired
    BuscarAmizadePendenteService buscarAmizadePendenteService;

    @Autowired
    BuscarAmizadeAceitaService buscarAmizadeAceitaService;

    @Autowired
    BuscarAmizade buscarAmizade;

    @Autowired
    IAmizadeRepository amizadeRepository;

    @Autowired
    BuscarAmigosPorNomeCompletoOuEmail buscarAmigosPorNomeCompletoOuEmail;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void incluir(@RequestBody AmizadeDto amizadeDto, @AuthenticationPrincipal UserPrincipal userPrincipal){
        salvarAmizadeService.salvar(amizadeDto, userPrincipal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id){
        desfazerAmizadeService.remover(id);
    }

    @PutMapping("/{id}")
    public void aceitar(@PathVariable("id") Long id){
        aceitarAmizadeService.aceitar(id);
    }

    @GetMapping
    public List<Amizade> buscarAmizades(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return amizadeRepository.findAll();
    }


    @GetMapping("/pendente")
    public List<AmizadeReponse> buscarAmizadePendente(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return buscarAmizadePendenteService.buscar(userPrincipal);
    }

    @GetMapping("/buscar/amigos/{nomeCompletoOuEmail}")
    public List<AmizadeReponse> buscarAmigosPorNomeOuEmail(@PathVariable("nomeCompletoOuEmail") String nomeCompletoOuEmail,@AuthenticationPrincipal UserPrincipal userPrincipal){
        return buscarAmigosPorNomeCompletoOuEmail.buscar(nomeCompletoOuEmail, userPrincipal);
    }

    @GetMapping("/usuario/{id}")
    public AmizadeReponse buscarAmizade(@PathVariable("id") Long id ,@AuthenticationPrincipal UserPrincipal userPrincipal){
        return buscarAmizade.buscar(id, userPrincipal);
    }


    @GetMapping("/aceita/amigos")
    public List<AmizadeReponse> buscarAmigos(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return buscarAmizadeAceitaService.buscar(userPrincipal);
    }
}
