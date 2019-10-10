package br.com.cwi.crescer.tcc.service.usuario;

import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.repository.IUsuarioRepository;
import br.com.cwi.crescer.tcc.repository.projection.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuscarUsuarioResponsePorIdService {
    @Autowired
    IUsuarioRepository usuarioRepository;
    public UsuarioResponse buscar(Long id){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("O id não pode estar em branco");
        }
        return usuarioRepository.queryById(id).orElseThrow(()-> new IllegalArgumentException("Usuário não encontrado"));
    }
}
