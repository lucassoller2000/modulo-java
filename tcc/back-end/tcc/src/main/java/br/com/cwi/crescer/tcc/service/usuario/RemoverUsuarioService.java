package br.com.cwi.crescer.tcc.service.usuario;

import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RemoverUsuarioService {
    @Autowired
    IUsuarioRepository usuarioRepository;

    @Autowired
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;

    public void remover(Long id){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("O id não pode estar em branco");
        }

        Usuario usuarioParaRemover = buscarUsuarioPorIdService.buscar(id);
        usuarioRepository.delete(usuarioParaRemover);
    }
}
