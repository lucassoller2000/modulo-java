package br.com.cwi.crescer.tcc.service.comentario;


import br.com.cwi.crescer.tcc.dominio.Comentario;
import br.com.cwi.crescer.tcc.repository.IComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RemoverComentarioService {

    @Autowired
    BuscarComentarioPorIdService buscarComentarioPorIdService;

    @Autowired
    IComentarioRepository comentarioRepository;

    public void remover(Long id){
        if(Objects.isNull(id)){
            throw new IllegalArgumentException("O id não pode estar em branco");
        }
        Comentario comentario = buscarComentarioPorIdService.buscar(id);

        comentarioRepository.delete(comentario);
    }
}
