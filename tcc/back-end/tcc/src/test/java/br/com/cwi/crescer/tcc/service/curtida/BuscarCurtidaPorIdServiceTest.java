package br.com.cwi.crescer.tcc.service.curtida;

import br.com.cwi.crescer.tcc.dominio.Curtida;
import br.com.cwi.crescer.tcc.repository.ICurtidaRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class BuscarCurtidaPorIdServiceTest {
    @Mock
    ICurtidaRepository curtidaRepository;

    @InjectMocks
    BuscarCurtidaPorIdService tested;

    @Test
    public void deveRetornarCurtidaExistente(){
        // Cenário
        Long id = 1L;
        Curtida curtida = new Curtida();
        curtida.setId(id);

        // Cenário - Mocks
        Mockito.when(curtidaRepository.findById(id)).thenReturn(Optional.of(curtida));

        // Método que deve ser testado
        Curtida curtidaRetornado = tested.buscar(id);

        // Verificações
        Assert.assertNotNull(curtidaRetornado);
        Assert.assertEquals(id, curtidaRetornado.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void naoEncontrarCurtidaGeraErro(){
        // Cenário
        Long id = 1L;
        // Cenário - Mocks
        Mockito.when(curtidaRepository.findById(id)).thenReturn(Optional.empty());

        // Método que deve ser testado
        tested.buscar(id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void idNuloGeraErro(){
        tested.buscar(null);
    }
}