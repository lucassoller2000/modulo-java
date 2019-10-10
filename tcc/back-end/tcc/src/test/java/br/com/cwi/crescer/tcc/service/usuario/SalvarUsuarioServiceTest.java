package br.com.cwi.crescer.tcc.service.usuario;

import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.dominio.dto.UsuarioDto;
import br.com.cwi.crescer.tcc.repository.IUsuarioRepository;
import br.com.cwi.crescer.tcc.security.password.Criptografia;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class SalvarUsuarioServiceTest {
    @Mock
    private IUsuarioRepository repository;

    @Mock
    private BuscarEmailUsadoService buscarEmailUsadoService;


    @Captor
    ArgumentCaptor<Usuario> captadorDeUsuario;

    @InjectMocks
    private SalvarUsuarioService tested;

    @Test(expected = IllegalArgumentException.class)
    public void deveDarErroQuandoReceberUsuarioNulo() {
        tested.salvar(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveDarErroQuandoReceberNomeNulo() {
        UsuarioDto usuario = new UsuarioDto();
        tested.salvar(usuario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveDarErroQuandoReceberEmailNulo() {
        UsuarioDto usuario = new UsuarioDto();
        usuario.setNomeCompleto("Lucas");
        tested.salvar(usuario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveDarErroQuandoReceberSenhaNula() {
        UsuarioDto usuario = new UsuarioDto();
        usuario.setNomeCompleto("Lucas");
        usuario.setEmail("Lucas");
        tested.salvar(usuario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deveDarErroQuandoReceberDataNula() {
        UsuarioDto usuario = new UsuarioDto();
        usuario.setNomeCompleto("Lucas");
        usuario.setEmail("Lucas");
        usuario.setSenha("Lucas");
        tested.salvar(usuario);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cadastrarEmailExistenteGeraErro() {
        // Cenário
        Long id = 1L;
        String nomeCompleto = "Lucas";
        String email ="Lucas123";
        String senha= "senha";
        LocalDate dataNascimento = LocalDate.of(2000,9,15);

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(id);
        usuarioDto.setNomeCompleto(nomeCompleto);
        usuarioDto.setEmail(email);
        usuarioDto.setSenha(senha);
        usuarioDto.setDataNascimento(dataNascimento);

        Usuario usuario = new Usuario();
        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));
        // Execução
        tested.salvar(usuarioDto);

        // Validações
        Mockito.verify(repository, Mockito.times(1)).save(captadorDeUsuario.capture());


        Usuario usuarioCapturado = captadorDeUsuario.getValue();

        Criptografia criptografia = new Criptografia();

        String criptografada = criptografia.criptografarSenha(senha);

        Assert.assertEquals(nomeCompleto, usuarioCapturado.getNomeCompleto());
        Assert.assertEquals(email, usuarioCapturado.getEmail());
        Assert.assertEquals(dataNascimento, usuarioCapturado.getDataNascimento());
        Assert.assertTrue(criptografia.senhaIgual(senha, criptografada));
    }

    @Test
    public void deveIncluirUsuario() {
        // Cenário
        Long id = 1L;
        String nomeCompleto = "Lucas";
        String email ="Lucas123";
        String senha= "senha";
        LocalDate dataNascimento = LocalDate.of(2000,9,15);

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(id);
        usuarioDto.setNomeCompleto(nomeCompleto);
        usuarioDto.setEmail(email);
        usuarioDto.setSenha(senha);
        usuarioDto.setDataNascimento(dataNascimento);

        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.empty());

        // Execução
        tested.salvar(usuarioDto);

        Usuario usuario = new Usuario();

        // Validações
        Mockito.verify(repository, Mockito.times(1)).save(captadorDeUsuario.capture());


        Usuario usuarioCapturado = captadorDeUsuario.getValue();

        Criptografia criptografia = new Criptografia();

        String criptografada = criptografia.criptografarSenha(senha);

        Assert.assertEquals(nomeCompleto, usuarioCapturado.getNomeCompleto());
        Assert.assertEquals(email, usuarioCapturado.getEmail());
        Assert.assertEquals(dataNascimento, usuarioCapturado.getDataNascimento());
        Assert.assertTrue(criptografia.senhaIgual(senha, criptografada));
    }


    @Test
    public void testarCriptografiaDeSenha(){
        String senha = "123";
        Criptografia criptografia = new Criptografia();

        String senhaCriptograda = criptografia.criptografarSenha(senha);

        Assert.assertNotEquals(senha, senhaCriptograda);
        Assert.assertTrue(criptografia.senhaIgual(senha,senhaCriptograda));
    }
}