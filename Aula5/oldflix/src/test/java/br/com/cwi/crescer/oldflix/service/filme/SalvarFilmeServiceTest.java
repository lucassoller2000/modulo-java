package br.com.cwi.crescer.oldflix.service.filme;

import br.com.cwi.crescer.oldflix.dominio.Filme;
import br.com.cwi.crescer.oldflix.enums.CategoriaFilme;
import br.com.cwi.crescer.oldflix.exception.filme.CategoriaInvalidaException;
import br.com.cwi.crescer.oldflix.exception.filme.TituloInvalidoException;
import br.com.cwi.crescer.oldflix.repository.IClienteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class SalvarFilmeServiceTest {

    @InjectMocks
    private SalvarFilmeService salvarFilmeService;

    @Mock
    private IClienteRepository repository;

    @Test(expected = TituloInvalidoException.class)
    public void tituloInvalidoGeraErro(){
        Filme filme = new Filme();
        filme.setCategoriaFilme(CategoriaFilme.DOURADA);
        salvarFilmeService.salvar(filme);
    }

    @Test(expected = CategoriaInvalidaException.class)
    public void categoriaInvalidaGeraErro(){
        Filme filme = new Filme();
        filme.setTitulo("filme");
        salvarFilmeService.salvar(filme);
    }
}