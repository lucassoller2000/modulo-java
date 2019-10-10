package br.com.cwi.crescer.tcc.web;
import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.dominio.dto.EdicaoDto;
import br.com.cwi.crescer.tcc.repository.IUsuarioRepository;
import br.com.cwi.crescer.tcc.repository.projection.UsuarioResponse;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import br.com.cwi.crescer.tcc.service.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioRepository repository;

    @Autowired
    private BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @Autowired
    private SalvarUsuarioService salvarUsuarioService;

    @Autowired
    private EditarUsuarioService editarUsuarioService;

    @Autowired
    private RemoverUsuarioService removerUsuarioService;

    @Autowired
    private BuscarUsuarioPorNomeOuEmailService buscarUsuarioPorNomeOuEmailService;

    @Autowired
    IUsuarioRepository usuarioRepository;

    @Autowired
    BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @Autowired
    BuscarUsuarioResponsePorIdService buscarUsuarioResponsePorIdService;


    @GetMapping
    public List<Usuario> listar(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable("id") Long id){
        return buscarUsuarioPorIdService.buscar(id);
    }

    @GetMapping("/logado")
    public UsuarioResponse buscarUsuarioLogadoPorId(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return buscarUsuarioResponsePorIdService.buscar(userPrincipal.getId());
    }

    @GetMapping("/buscar/{nomeCompletoOuEmail}")
    public List<UsuarioResponse> buscarPorNomeOuEmail(@PathVariable("nomeCompletoOuEmail") String nomeCompletoOuEmail){
        return buscarUsuarioPorNomeOuEmailService.buscar(nomeCompletoOuEmail);
    }

    @PutMapping
    public void editar(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody EdicaoDto edicaoDto){
        editarUsuarioService.editar(userPrincipal, edicaoDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id){
        removerUsuarioService.remover(id);
    }
}
