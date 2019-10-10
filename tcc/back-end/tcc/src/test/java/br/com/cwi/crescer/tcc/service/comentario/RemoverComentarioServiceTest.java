package br.com.cwi.crescer.tcc.service.comentario;

import br.com.cwi.crescer.tcc.dominio.Comentario;
import br.com.cwi.crescer.tcc.repository.IComentarioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RemoverComentarioServiceTest {
    @Mock
    IComentarioRepository comentarioRepository;

    @Mock
    BuscarComentarioPorIdService buscarComentarioPorIdService;

    @InjectMocks
    RemoverComentarioService tested;

    @Test
    public void deveRemoverComentario(){
        Long id = 1L;

        tested.remover(id);

        Mockito.verify(buscarComentarioPorIdService, Mockito.times(1)).buscar(id);
        Comentario comentario = new Comentario();
        Mockito.when(comentarioRepository.findById(id)).thenReturn(Optional.of(comentario));
        Mockito.verify(comentarioRepository, Mockito.times(1)).delete(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void idNuloGeraErro(){

        tested.remover(null);
    }

}