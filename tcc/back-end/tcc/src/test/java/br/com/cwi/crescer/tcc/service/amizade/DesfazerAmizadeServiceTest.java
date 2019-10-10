//package br.com.cwi.crescer.tcc.service.amizade;
//
//import br.com.cwi.crescer.tcc.dominio.Amizade;
//import br.com.cwi.crescer.tcc.dominio.Usuario;
//import br.com.cwi.crescer.tcc.repository.IAmizadeRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import java.util.Optional;
//
//@RunWith(MockitoJUnitRunner.class)
//public class DesfazerAmizadeServiceTest {
//    @Mock
//    IAmizadeRepository amizadeRepository;
//
//    @Mock
//    BuscarAmizadePorIdService buscarAmizadePorIdService;
//
//    @InjectMocks
//    DesfazerAmizadeService tested;
//
////    @Test
////    public void deveRemoverAmizade(){
////        Long id = 1L;
////        Usuario usuarioRemetente = new Usuario();
////        Usuario usuarioDestinatario = new Usuario();
////        Amizade amizade = new Amizade();
////        Mockito.when(amizadeRepository.queryByUsuarioRemetenteAndUsuarioDestinatario(usuarioRemetente, usuarioDestinatario)).thenReturn(Optional.of(amizade));
////
////        tested.remover(id);
////        Mockito.verify(buscarAmizadePorIdService, Mockito.times(1)).buscar(id);
////        Mockito.verify(amizadeRepository, Mockito.times(1)).delete(null);
////    }
////
////    @Test(expected = IllegalArgumentException.class)
////    public void idNuloGeraErro(){
////
////        tested.remover(null);
////    }
//
//}