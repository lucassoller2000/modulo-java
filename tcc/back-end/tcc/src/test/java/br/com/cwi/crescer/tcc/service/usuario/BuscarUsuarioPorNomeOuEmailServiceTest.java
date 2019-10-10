package br.com.cwi.crescer.tcc.service.usuario;

import br.com.cwi.crescer.tcc.repository.IUsuarioRepository;
import br.com.cwi.crescer.tcc.repository.projection.UsuarioResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BuscarUsuarioPorNomeOuEmailServiceTest {
    @Mock
    IUsuarioRepository usuarioRepository;

    @InjectMocks
    BuscarUsuarioPorNomeOuEmailService tested;


    @Test(expected = IllegalArgumentException.class)
    public void nomeNuloGeraErro(){
        tested.buscar(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveGerarErroSeNaoEncontrarUsuariosPeloNome(){

        String nomeCompleto = "Lucas";

        // Método que deve ser testado
        List<UsuarioResponse> usuariosRetornados = tested.buscar(nomeCompleto);
    }

}