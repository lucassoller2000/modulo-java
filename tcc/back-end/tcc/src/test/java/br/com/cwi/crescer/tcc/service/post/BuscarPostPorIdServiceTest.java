package br.com.cwi.crescer.tcc.service.post;

import br.com.cwi.crescer.tcc.dominio.Post;
import br.com.cwi.crescer.tcc.repository.IPostRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarPostPorIdServiceTest {
    @Mock
    IPostRepository postRepository;

    @InjectMocks
    BuscarPostPorIdService tested;

    @Test
    public void deveRetornarPostExistente(){
        // Cenário
        Long id = 1L;
        Post post = new Post();
        post.setId(id);

        // Cenário - Mocks
        Mockito.when(postRepository.findById(id)).thenReturn(Optional.of(post));

        // Método que deve ser testado
        Post postRetornado = tested.buscar(id);

        // Verificações
        Assert.assertNotNull(postRetornado);
        Assert.assertEquals(id, postRetornado.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoEncontrarPostGeraErro(){
        // Cenário
        Long id = 1L;
        // Cenário - Mocks
        Mockito.when(postRepository.findById(id)).thenReturn(Optional.empty());

        // Método que deve ser testado
        tested.buscar(id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void idNuloGeraErro(){
        tested.buscar(null);
    }
}