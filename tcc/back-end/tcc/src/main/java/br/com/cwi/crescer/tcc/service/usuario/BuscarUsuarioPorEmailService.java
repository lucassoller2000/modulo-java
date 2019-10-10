package br.com.cwi.crescer.tcc.service.usuario;

import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarUsuarioPorEmailService {
    @Autowired
    IUsuarioRepository usuarioRepository;

    public Usuario buscar(String email){
        if(Objects.isNull(email)){
            throw new IllegalArgumentException("O e-mail não pode estar em branco");
        }

        return usuarioRepository.findByEmail(email)
                .orElseThrow(()->new IllegalArgumentException("Usuário não encontrado"));
    }
}
