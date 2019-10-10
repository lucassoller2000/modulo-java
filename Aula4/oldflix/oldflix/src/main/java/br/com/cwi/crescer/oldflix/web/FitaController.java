package br.com.cwi.crescer.oldflix.web;
import br.com.cwi.crescer.oldflix.dominio.Fita;
import br.com.cwi.crescer.oldflix.enums.SituacaoFita;
import br.com.cwi.crescer.oldflix.exception.fita.FitaNaoCadastradaException;
import br.com.cwi.crescer.oldflix.repository.IFitaRepository;
import br.com.cwi.crescer.oldflix.service.fita.BuscarFitaPorIdService;
import br.com.cwi.crescer.oldflix.service.fita.SalvarFitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/fita")
@RestController
public class FitaController {

    @Autowired
    private IFitaRepository repository;

    @Autowired
    private SalvarFitaService salvarFitaService;

    @Autowired
    private BuscarFitaPorIdService buscarFitaPorIdService;

    @GetMapping
    public List<Fita> listar(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Fita buscarPorId(@PathVariable("id") Long id){
        return buscarFitaPorIdService.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void incluir(@RequestBody Fita fita){
        salvarFitaService.salvar(fita);
    }

    @PutMapping("/{id}")
    public Fita editar(@PathVariable("id") Long id, @RequestBody Fita fita){
        repository.save(fita);
        return repository
                .findById(id)
                .orElseThrow(()-> new FitaNaoCadastradaException());

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id){

        Fita fita = repository
                .findById(id)
                .orElseThrow(()-> new FitaNaoCadastradaException());

        repository.delete(fita);
    }
}
