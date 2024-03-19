package org.acme.service.contrat;

import org.acme.gen.type.UsuarioTypeInput;
import org.acme.gen.type.UsuarioTypeResponse;

import java.util.List;

public interface IUsuarioService {
    UsuarioTypeResponse crearUsuario(UsuarioTypeInput usuarioTypeInput);
    void eliminarUsuario(Integer idtblUser);
    List<UsuarioTypeResponse> listarUsuarios();
    UsuarioTypeResponse listarUsuario(Integer idtblUser);
    UsuarioTypeResponse actualizarUsuario(Integer idtblUser, UsuarioTypeInput usuarioTypeInput);

}
