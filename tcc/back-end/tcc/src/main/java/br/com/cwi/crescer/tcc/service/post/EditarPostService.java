package br.com.cwi.crescer.tcc.service.post;

import br.com.cwi.crescer.tcc.dominio.Post;
import br.com.cwi.crescer.tcc.dominio.dto.PostDto;
import br.com.cwi.crescer.tcc.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EditarPostService {
    @Autowired
    IPostRepository postRepository;

    @Autowired
    BuscarPostPorIdService buscarPostPorIdService;
    public Post editar(Long id, PostDto postDto){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("O id não pode estar em branco");
        }

        if(Objects.isNull(postDto.getPostagem()) || postDto.getPostagem().isEmpty()){
            throw new IllegalArgumentException("O texto não pode ficar em branco");
        }

        Post post = buscarPostPorIdService.buscar(id);
        post.setPostagem(postDto.getPostagem());

        return postRepository.save(post).get();
    }
}
