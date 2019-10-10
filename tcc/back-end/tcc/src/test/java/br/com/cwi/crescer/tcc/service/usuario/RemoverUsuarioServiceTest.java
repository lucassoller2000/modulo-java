package br.com.cwi.crescer.tcc.service.usuario;

import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.repository.IUsuarioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RemoverUsuarioServiceTest {
    @Mock
    IUsuarioRepository usuarioRepository;

    @Mock
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    @InjectMocks
    RemoverUsuarioService tested;

    @Test
    public void deveRemoverUsuario(){
        Long id = 1L;

        tested.remover(id);

        Mockito.verify(buscarUsuarioPorIdService, Mockito.times(1)).buscar(id);
        Usuario usuario = new Usuario();
        Mockito.when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        Mockito.verify(usuarioRepository, Mockito.times(1)).delete(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void idNuloGeraErro(){

        tested.remover(null);
    }

}