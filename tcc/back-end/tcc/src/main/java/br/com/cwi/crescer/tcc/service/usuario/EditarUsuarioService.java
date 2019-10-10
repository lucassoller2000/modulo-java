package br.com.cwi.crescer.tcc.service.usuario;

import br.com.cwi.crescer.tcc.dominio.Usuario;
import br.com.cwi.crescer.tcc.dominio.dto.EdicaoDto;
import br.com.cwi.crescer.tcc.repository.IUsuarioRepository;
import br.com.cwi.crescer.tcc.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EditarUsuarioService {
    @Autowired
    IUsuarioRepository usuarioRepository;

    @Autowired
    BuscarUsuarioPorIdService buscarUsuarioPorIdService;


    public void editar(UserPrincipal userPrincipal, EdicaoDto edicaoDto){
        if(Objects.isNull(edicaoDto)){
            throw new IllegalArgumentException();
        }

        Usuario usuarioParaEditar = buscarUsuarioPorIdService.buscar(userPrincipal.getId());

        if(!Objects.isNull(edicaoDto.getNomeCompleto()) && !edicaoDto.getNomeCompleto().isEmpty()){
            usuarioParaEditar.setNomeCompleto(edicaoDto.getNomeCompleto());
        }

        if(!Objects.isNull(edicaoDto.getApelido()) && !edicaoDto.getApelido().isEmpty()){
            usuarioParaEditar.setApelido(edicaoDto.getApelido());
        }
        if(!Objects.isNull(edicaoDto.getImagem()) && !edicaoDto.getImagem().isEmpty()){
            usuarioParaEditar.setImagem(edicaoDto.getImagem());
        }
        
        usuarioRepository.save(usuarioParaEditar);
    }
}
