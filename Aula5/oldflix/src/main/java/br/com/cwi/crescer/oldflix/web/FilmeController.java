package br.com.cwi.crescer.oldflix.web;
import br.com.cwi.crescer.oldflix.dominio.Filme;
import br.com.cwi.crescer.oldflix.exception.filme.FilmeNaoCadastradoException;
import br.com.cwi.crescer.oldflix.repository.IFilmeRepository;
import br.com.cwi.crescer.oldflix.service.filme.BuscarFilmePorIdService;
import br.com.cwi.crescer.oldflix.service.filme.SalvarFilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/filme")
@RestController
public class FilmeController {

    @Autowired
    private IFilmeRepository repository;
    @Autowired
    private BuscarFilmePorIdService buscarFilmePorIdService;
    @Autowired
    private SalvarFilmeService salvarFilmeService;

    @GetMapping
    public List<Filme> listar(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Filme buscarPorId(@PathVariable("id") Long id){
        return buscarFilmePorIdService.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void incluir(@RequestBody Filme filme){
        salvarFilmeService.salvar(filme);
    }

    @PutMapping("/{id}")
    public Filme editar(@PathVariable("id") Long id, @RequestBody Filme filme){
        repository.save(filme);
        return repository
                .findById(id)
                .orElseThrow(()-> new FilmeNaoCadastradoException());

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id){

        Filme filme = repository
                .findById(id)
                .orElseThrow(()-> new FilmeNaoCadastradoException());

        repository.delete(filme);
    }


}
