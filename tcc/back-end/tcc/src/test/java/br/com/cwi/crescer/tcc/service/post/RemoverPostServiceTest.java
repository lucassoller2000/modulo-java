package br.com.cwi.crescer.tcc.service.post;

import br.com.cwi.crescer.tcc.dominio.Post;
import br.com.cwi.crescer.tcc.repository.IPostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RemoverPostServiceTest {
    @Mock
    IPostRepository postRepository;

    @Mock
    BuscarPostPorIdService buscarPostPorIdService;

    @InjectMocks
    RemoverPostService tested;

    @Test
    public void deveRemoverPost(){
        Long id = 1L;

        tested.remover(id);

        Mockito.verify(buscarPostPorIdService, Mockito.times(1)).buscar(id);
        Post post = new Post();
        Mockito.when(postRepository.findById(id)).thenReturn(Optional.of(post));
        Mockito.verify(postRepository, Mockito.times(1)).delete(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void idNuloGeraErro(){

        tested.remover(null);
    }

}