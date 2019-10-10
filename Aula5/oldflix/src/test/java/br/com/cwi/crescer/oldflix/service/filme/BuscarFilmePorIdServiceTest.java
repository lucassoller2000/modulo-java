package br.com.cwi.crescer.oldflix.service.filme;

import br.com.cwi.crescer.oldflix.dominio.Filme;
import br.com.cwi.crescer.oldflix.exception.filme.FilmeNaoCadastradoException;
import br.com.cwi.crescer.oldflix.repository.IFilmeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class BuscarFilmePorIdServiceTest {

    @InjectMocks
    private BuscarFilmePorIdService buscarFilmePorIdService;

    @Mock
    private IFilmeRepository repository;

    @Test
    public void deveRetornarUmFilmeExistente() {
        //cenario
        Long id = 1L;
        Filme filmeNovo = new Filme();
        filmeNovo.setId(id);

        //cenario - mocks
        Mockito.when(repository.findById(id)).thenReturn(Optional.of(filmeNovo));

        //metodo que deve ser testado
        Filme filme = buscarFilmePorIdService.buscar(id);

        //validacoes
        Assert.assertNotNull(filme);
        Assert.assertEquals(id, filme.getId());
    }

    @Test(expected = FilmeNaoCadastradoException.class)
    public void idInvalidoGeraErro() {
        buscarFilmePorIdService.buscar(null);
    }
}