package br.com.cwi.crescer.tcc.service.usuario;

import br.com.cwi.crescer.tcc.repository.IUsuarioRepository;
import br.com.cwi.crescer.tcc.repository.projection.UsuarioResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BuscarUsuarioResponsePorIdServiceTest {
    @Mock
    IUsuarioRepository usuarioRepository;

    @InjectMocks
    BuscarUsuarioResponsePorIdService tested;

    @Test(expected = IllegalArgumentException.class)
    public void idNuloGeraErro(){
        tested.buscar(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveGerarErroSeNaoEncontrarUsuarioPorId(){

       Long id = 1L;

        // Cenário - Mocks
        Mockito.when(usuarioRepository.queryById(id)).thenReturn(Optional.empty());

        tested.buscar(id);
    }
}