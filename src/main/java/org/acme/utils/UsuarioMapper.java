package org.acme.utils;

import org.acme.entity.Usuario;
import org.acme.gen.type.UsuarioTypeInput;
import org.acme.gen.type.UsuarioTypeResponse;
import org.modelmapper.ModelMapper;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class UsuarioMapper {
    public Usuario usuarioTypeToEntity(UsuarioTypeInput usuarioTypeInput) {
        return new ModelMapper().map(usuarioTypeInput, Usuario.class);
    }

    public UsuarioTypeResponse usuarioEntityToTypeResponse(Usuario usuario) {
        return new ModelMapper().map(usuario, UsuarioTypeResponse.class);
    }

    public List<UsuarioTypeResponse> usuariosTypeListEntityToTypeResponse(List<Usuario> usuarios) {
        List<UsuarioTypeResponse> responses = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            UsuarioTypeResponse response = new ModelMapper().map(usuario, UsuarioTypeResponse.class);
            responses.add(response);
        }
        return responses;
    }
}
