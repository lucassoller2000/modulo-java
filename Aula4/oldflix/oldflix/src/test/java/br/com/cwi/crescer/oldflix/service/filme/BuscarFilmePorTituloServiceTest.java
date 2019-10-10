package br.com.cwi.crescer.oldflix.service.filme;

import br.com.cwi.crescer.oldflix.dominio.Filme;
import br.com.cwi.crescer.oldflix.exception.filme.FilmeNaoCadastradoException;
import br.com.cwi.crescer.oldflix.repository.IClienteRepository;
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
public class BuscarFilmePorTituloServiceTest {

    @InjectMocks
    private BuscarFilmePorTituloService buscarFilmePorTituloService;

    @Mock
    private IFilmeRepository repository;

    @Test
    public void deveRetornarUmFilmeExistente(){
        //cenario
        String titulo = "123";
        Filme filmeNovo = new Filme();
        filmeNovo.setTitulo(titulo);

        //cenario - mocks
        Mockito.when(repository.findByTitulo(titulo)).thenReturn(Optional.of(filmeNovo));

        //metodo que deve ser testado
        Filme filme = buscarFilmePorTituloService.buscar(titulo);

        //validacoes
        Assert.assertNotNull(filme);
        Assert.assertEquals(titulo, filme.getTitulo());
    }

    @Test(expected = FilmeNaoCadastradoException.class)
    public void tituloInvalidoGeraErro(){
        buscarFilmePorTituloService.buscar(null);
    }

}