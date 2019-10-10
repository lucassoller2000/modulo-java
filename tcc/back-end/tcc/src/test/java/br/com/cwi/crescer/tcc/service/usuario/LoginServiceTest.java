package br.com.cwi.crescer.tcc.service.usuario;

import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.dominio.dto.LoginRequestDto;
import br.com.cwi.crescer.tcc.dominio.dto.LoginResponseDto;
import br.com.cwi.crescer.tcc.security.AuthenticationService;
import br.com.cwi.crescer.tcc.security.password.Criptografia;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {
    @Mock
    AuthenticationService authenticationService;

    @Mock
    BuscarUsuarioPorEmailService buscarUsuarioPorEmailService;

    @InjectMocks
    LoginService loginService;

    @Captor
    ArgumentCaptor<String> captadorToken;

    @Test
    public void deveLogar(){
        String email = "lucas";
        String senha = "123";

        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(email);
        loginRequestDto.setSenha(senha);

        String token = "12345";

        Usuario usuario = new Usuario();
        usuario.setEmail(email);

        Criptografia criptografia = new Criptografia();
        String senhaCriptografada = criptografia.criptografarSenha(senha);

        usuario.setSenha(senhaCriptografada);

        Mockito.when(buscarUsuarioPorEmailService.buscar(email)).thenReturn(usuario);
        Mockito.when(authenticationService.authenticate(email,usuario.getSenha())).thenReturn(token);

        LoginResponseDto loginResponseDto = loginService.logar(loginRequestDto);

        Mockito.verify(authenticationService,Mockito.times(1)).authenticate(email,usuario.getSenha());
        Mockito.verify(buscarUsuarioPorEmailService, Mockito.times(1)).buscar(email);

        Assert.assertEquals(email, loginResponseDto.getEmail());
        Assert.assertEquals(token, loginResponseDto.getToken());
    }

    @Test(expected = IllegalArgumentException.class)
    public void senhaIncorretaGeraErro(){
        String email = "lucas";
        String senha = "123";

        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(email);
        loginRequestDto.setSenha(senha);

        Usuario usuario = new Usuario();
        usuario.setEmail(email);

        Criptografia criptografia = new Criptografia();

        usuario.setSenha(criptografia.criptografarSenha("abc"));

        Mockito.when(buscarUsuarioPorEmailService.buscar(email)).thenReturn(usuario);

        loginService.logar(loginRequestDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void senhaNulaGeraErro(){
        String email = "lucas";

        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(email);
        loginService.logar(loginRequestDto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emailNuloGeraErro(){
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginService.logar(loginRequestDto);
    }
}