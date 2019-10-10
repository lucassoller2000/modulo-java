package br.com.cwi.crescer.tcc.service.curtida;

import br.com.cwi.crescer.tcc.dominio.Curtida;
import br.com.cwi.crescer.tcc.repository.ICurtidaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RemoverCurtidaServiceTest {
    @Mock
    ICurtidaRepository curtidaRepository;

    @Mock
    BuscarCurtidaPorIdService buscarCurtidaPorIdService;

    @InjectMocks
    RemoverCurtidaService tested;

    @Test
    public void deveRemoverCurtida(){
        Long id = 1L;

        tested.remover(id);

        Mockito.verify(buscarCurtidaPorIdService, Mockito.times(1)).buscar(id);
        Curtida curtida = new Curtida();
        Mockito.when(curtidaRepository.findById(id)).thenReturn(Optional.of(curtida));
        Mockito.verify(curtidaRepository, Mockito.times(1)).delete(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void idNuloGeraErro(){

        tested.remover(null);
    }

}