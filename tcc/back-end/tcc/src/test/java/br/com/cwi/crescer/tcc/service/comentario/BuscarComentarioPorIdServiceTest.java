package br.com.cwi.crescer.tcc.service.comentario;

import br.com.cwi.crescer.tcc.dominio.Comentario;
import br.com.cwi.crescer.tcc.repository.IComentarioRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarComentarioPorIdServiceTest {
    @Mock
    IComentarioRepository comentarioRepository;

    @InjectMocks
    BuscarComentarioPorIdService tested;

    @Test
    public void deveRetornarComentarioExistente(){
        // Cenário
        Long id = 1L;
        Comentario comentario = new Comentario();
        comentario.setId(id);

        // Cenário - Mocks
        Mockito.when(comentarioRepository.findById(id)).thenReturn(Optional.of(comentario));

        // Método que deve ser testado
        Comentario comentarioRetornado = tested.buscar(id);

        // Verificações
        Assert.assertNotNull(comentarioRetornado);
        Assert.assertEquals(id, comentarioRetornado.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoEncontrarComentarioGeraErro(){
        // Cenário
        Long id = 1L;
        // Cenário - Mocks
        Mockito.when(comentarioRepository.findById(id)).thenReturn(Optional.empty());

        // Método que deve ser testado
        tested.buscar(id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void idNuloGeraErro(){
        tested.buscar(null);
    }
}