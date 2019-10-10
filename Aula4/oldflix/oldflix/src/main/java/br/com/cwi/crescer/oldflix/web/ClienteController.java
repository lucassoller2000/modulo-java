package br.com.cwi.crescer.oldflix.web;

import br.com.cwi.crescer.oldflix.dominio.Cliente;
import br.com.cwi.crescer.oldflix.exception.cliente.ClienteNaoCadastradoException;
import br.com.cwi.crescer.oldflix.exception.cliente.CpfInvalidoException;
import br.com.cwi.crescer.oldflix.repository.IClienteRepository;
import br.com.cwi.crescer.oldflix.service.cliente.BuscarClientePorIdService;
import br.com.cwi.crescer.oldflix.service.cliente.SalvarClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/cliente")
@RestController
public class ClienteController {

    @Autowired
    private IClienteRepository repository;

    @Autowired
    private BuscarClientePorIdService  buscarClientePorIdService;

    @Autowired
    private SalvarClienteService salvarClienteService;

    @GetMapping
    public List<Cliente> listar(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable("id") Long id){
        return buscarClientePorIdService.buscar(id);
    }

    @GetMapping("cpf/{cpf}")
    public Cliente buscarPorCpf(@PathVariable("cpf") String cpf){
        return repository.findByCpf(cpf).orElseThrow(()-> new CpfInvalidoException());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void incluir(@RequestBody Cliente cliente){
        salvarClienteService.salvar(cliente);
    }

    @PutMapping("/{id}")
    public Cliente editar(@PathVariable("id") Long id, @RequestBody Cliente cliente){
        repository.save(cliente);
        return repository
                .findById(id)
                .orElseThrow(()-> new ClienteNaoCadastradoException());

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id){

        Cliente cliente = repository
                .findById(id)
                .orElseThrow(()-> new ClienteNaoCadastradoException());

        repository.delete(cliente);
    }
}
