package br.com.cwi.crescer.tcc.service.post;

import br.com.cwi.crescer.tcc.dominio.Post;
import br.com.cwi.crescer.tcc.repository.IPostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RemoverPostService {
    @Autowired
    IPostRepository postRepository;

    @Autowired
    BuscarPostPorIdService buscarPostPorIdService;

    public void remover(Long id){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("O id não pode estar em branco");
        }

        Post post = buscarPostPorIdService.buscar(id);

        postRepository.delete(post);
    }
}
