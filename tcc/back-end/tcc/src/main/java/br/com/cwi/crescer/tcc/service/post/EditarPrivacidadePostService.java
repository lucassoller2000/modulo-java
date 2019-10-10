package br.com.cwi.crescer.tcc.service.post;

import br.com.cwi.crescer.tcc.dominio.Post;
import br.com.cwi.crescer.tcc.dominio.dto.PrivacidadeDto;
import br.com.cwi.crescer.tcc.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EditarPrivacidadePostService {
    @Autowired
    BuscarPostPorIdService buscarPostPorIdService;

    @Autowired
    IPostRepository postRepository;

    public Post editar(Long id, PrivacidadeDto privacidadeDto){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("O id não pode estar em branco");
        }

        if(Objects.isNull(privacidadeDto.getPrivacidadePost())){
            throw new IllegalArgumentException("A privacidade não pode estar em branco");
        }

        Post post = buscarPostPorIdService.buscar(id);
        post.setPrivacidadePost(privacidadeDto.getPrivacidadePost());

        return postRepository.save(post).get();
    }
}
