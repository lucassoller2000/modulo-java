package br.com.cwi.crescer.tcc.service.post;

import br.com.cwi.crescer.tcc.dominio.Post;
import br.com.cwi.crescer.tcc.dominio.dto.PrivacidadeDto;
import br.com.cwi.crescer.tcc.dominio.enumerated.PrivacidadePost;
import br.com.cwi.crescer.tcc.repository.IPostRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EditarPrivacidadePostServiceTest {
    @Mock
    private IPostRepository repository;

    @Mock
    private BuscarPostPorIdService buscarPostPorIdService;


    @Captor
    ArgumentCaptor<Post> captadorDePost;

    @InjectMocks
    private EditarPrivacidadePostService tested;

    @Test(expected = IllegalArgumentException.class)
    public void deveDarErroQuandoReceberIdNulo() {
        tested.editar(null,null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveDarErroQuandoReceberPrivacidadeNula() {
        PrivacidadeDto privacidadeDto = new PrivacidadeDto();
        tested.editar(1L,privacidadeDto);
    }


//    @Test
//    public void deveEditarPrivacidade() {

//        PrivacidadeDto privacidadeDto = new PrivacidadeDto();
//
//
//        // Cenário
//        Long id = 1L;
//
//        privacidadeDto.setPrivacidadePost(PrivacidadePost.PUBLICO);
//
//        Post post = new Post();
//        post.setId(id);
//        Mockito.when(buscarPostPorIdService.buscar(id)).thenReturn(post);
//
//        // Execução
//        tested.editar(id, privacidadeDto);
//
//        // Validações
//
//        Mockito.verify(buscarPostPorIdService, Mockito.times(1)).buscar(id);
//        Mockito.verify(repository, Mockito.times(1)).save(captadorDePost.capture());
//
//        Post postCapturado = captadorDePost.getValue();
//
//        Assert.assertEquals(PrivacidadePost.PUBLICO, postCapturado.getPrivacidadePost());
//    }
}
