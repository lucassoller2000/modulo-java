package br.com.cwi.crescer.tcc.web;

import br.com.cwi.crescer.tcc.dominio.Post;
import br.com.cwi.crescer.tcc.dominio.dto.PostDto;
import br.com.cwi.crescer.tcc.dominio.dto.PrivacidadeDto;
import br.com.cwi.crescer.tcc.dominio.enumerated.PrivacidadePost;
import br.com.cwi.crescer.tcc.repository.IPostRepository;
import br.com.cwi.crescer.tcc.repository.projection.PostResponse;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import br.com.cwi.crescer.tcc.service.post.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RestController
public class PostController {

    @Autowired
    SalvarPostService salvarPostService;

    @Autowired
    BuscarPostPorIdService buscarPostPorIdService;

    @Autowired
    RemoverPostService removerPostService;

    @Autowired
    EditarPostService editarPostService;

    @Autowired
    BuscarPostsPorUsuario buscarPostsPorUsuario;

    @Autowired
    IPostRepository postRepository;

    @Autowired
    EditarPrivacidadePostService editarPrivacidadePostService;

    @Autowired
    BuscarPostUsuarioLogado buscarPostUsuarioLogado;



    @GetMapping("/amigos")
    public List<PostResponse> listarPostDeAmigos(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return postRepository.findAll(userPrincipal.getId());
    }

    @GetMapping("/usuario/{id}")
    public List<PostResponse> listarPostsDoUsuario(@PathVariable("id") Long id, @AuthenticationPrincipal UserPrincipal userPrincipal){
        return buscarPostsPorUsuario.buscar(id);
    }

    @GetMapping
    public List<PostResponse> listarPostUsuarioLogado(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return buscarPostUsuarioLogado.buscar(userPrincipal);
    }

    @GetMapping("/{id}")
    public PostResponse buscarPorId(@PathVariable("id") Long id, @AuthenticationPrincipal UserPrincipal userPrincipal){
        return postRepository.queryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void incluir(@RequestBody PostDto postDto, @AuthenticationPrincipal UserPrincipal userPrincipal){
        salvarPostService.salvar(postDto, userPrincipal);
    }


    @PutMapping("/{id}")
    public Post editar(@PathVariable("id") Long id, @RequestBody PostDto postDto, @AuthenticationPrincipal UserPrincipal userPrincipal){
        return editarPostService.editar(id, postDto);
    }

    @PutMapping("/{id}/privacidade")
    public Post editarPrivacidade(@PathVariable("id") Long id, @RequestBody PrivacidadeDto privacidadeDto, @AuthenticationPrincipal UserPrincipal userPrincipal){
        return editarPrivacidadePostService.editar(id, privacidadeDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("id") Long id, @AuthenticationPrincipal UserPrincipal userPrincipal){
        removerPostService.remover(id);
    }
}
