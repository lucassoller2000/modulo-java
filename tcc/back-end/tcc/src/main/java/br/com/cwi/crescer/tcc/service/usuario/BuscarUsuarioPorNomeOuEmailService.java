package br.com.cwi.crescer.tcc.service.usuario;

import br.com.cwi.crescer.tcc.repository.IUsuarioRepository;
import br.com.cwi.crescer.tcc.repository.projection.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BuscarUsuarioPorNomeOuEmailService {
    @Autowired
    IUsuarioRepository usuarioRepository;

    public List<UsuarioResponse> buscar(String nomeCompletoOuEmail){

        if(Objects.isNull(nomeCompletoOuEmail) || nomeCompletoOuEmail.isEmpty()){
            throw new IllegalArgumentException("O nome não pode estar em branco");
        }

        List<UsuarioResponse> usuarios = usuarioRepository.findByNomeCompletoOrEmail(nomeCompletoOuEmail);
        if(usuarios.isEmpty()){
            throw new IllegalArgumentException("Nenhum resultado encontrado");
        }

        return usuarios;
    }
}
