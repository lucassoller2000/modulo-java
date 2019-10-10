package br.com.cwi.crescer.tcc.service.usuario;


import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarPorEmailESenhaService {
    @Autowired
    IUsuarioRepository usuarioRepository;

    public Usuario buscar (String email, String senha){
        if(Objects.isNull(email)){
            throw new IllegalArgumentException("O e-mail não pode estar em branco");
        }

        if(Objects.isNull(senha)){
            throw new IllegalArgumentException("A senha não pode estar em branco");
        }

        return usuarioRepository.findByEmailAndSenha(email,senha)
                .orElseThrow(()-> new IllegalArgumentException("Usuário não encontrado"));
    }
}
