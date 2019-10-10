package br.com.cwi.crescer.tcc.service.post;

import br.com.cwi.crescer.tcc.dominio.Post;
import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.dominio.dto.PostDto;
import br.com.cwi.crescer.tcc.dominio.enumerated.PrivacidadePost;
import br.com.cwi.crescer.tcc.repository.IPostRepository;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import br.com.cwi.crescer.tcc.service.usuario.BuscarUsuarioPorEmailService;
import br.com.cwi.crescer.tcc.service.usuario.BuscarUsuarioPorIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class SalvarPostService {
    @Autowired
    IPostRepository postRepository;

    @Autowired
    BuscarPostPorIdService buscarPostPorIdService;

    @Autowired
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;
    public void salvar(PostDto postDto, UserPrincipal userPrincipal){

        if(Objects.isNull(postDto.getPostagem()) || postDto.getPostagem().isEmpty()){
            throw new IllegalArgumentException("O texto não pode ficar em branco");
        }

        Usuario usuario = buscarUsuarioPorIdService.buscar(userPrincipal.getId());

        Post post = new Post();
        post.setDataPostagem(LocalDate.now());
        post.setPostagem(postDto.getPostagem());
        post.setUsuario(usuario);
        post.setPrivacidadePost(PrivacidadePost.PUBLICO);
        postRepository.save(post);
    }

}
