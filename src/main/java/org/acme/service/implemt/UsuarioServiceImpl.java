package org.acme.service.implemt;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.controller.UsuarioController;
import org.acme.dao.UsuarioDao;
import org.acme.entity.Usuario;
import org.acme.gen.type.UsuarioTypeInput;
import org.acme.gen.type.UsuarioTypeResponse;
import org.acme.service.contrat.IUsuarioService;
import org.acme.utils.ApplicationException;
import org.acme.utils.UsuarioMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


import java.util.Collections;
import java.util.List;

import static org.acme.constant.Constant.ERROR_SERVICIO;

@ApplicationScoped //Comvierte la clase en BIN
public class UsuarioServiceImpl implements IUsuarioService {
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);
    @Inject
    UsuarioMapper usuarioMapper;
    @Inject
    UsuarioDao usuarioDao;

    @Transactional
    public List<UsuarioTypeResponse> crearUsuario(UsuarioTypeInput usuarioTypeInput) {
        try {
            Usuario usuario = usuarioMapper.usuarioTypeToEntity(usuarioTypeInput);
            LOG.info("Guardando usuario...");
            usuarioDao.persist(usuario);
            LOG.info("Fin creacion usuario");
            return usuarioMapper.usuarioEntityToTypeResponse(usuario);
        } catch (ApplicationException e) {
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Transactional
    public void eliminarUsuario(Integer idtblUser) {
        try {
            Long id = Long.valueOf(idtblUser);
            usuarioDao.deleteById(id);
        } catch (ApplicationException e) {
            LOG.error("Se presento un error al listar usuario por id" + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Transactional
    public List<UsuarioTypeResponse> listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioDao.listAll();
            return usuarioMapper.usuariosTypeListEntityToTypeResponse(usuarios);
        } catch (ApplicationException e) {
            LOG.error("Se presento un error al listar usuarios " + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Transactional
    public List<UsuarioTypeResponse> listarUsuario(Integer idtblUser) {
        try {
            Usuario usuario = usuarioDao.findById(Long.valueOf(idtblUser));
            return usuarioMapper.usuarioEntityToTypeResponse(usuario);
        } catch (ApplicationException e) {
            LOG.error("Se presento un error al listar usuarios " + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Transactional
    public List<UsuarioTypeResponse> actualizarUsuario(Integer idtblUser, UsuarioTypeInput usuarioTypeInput) {
        Usuario usuario_bd = usuarioDao.findById(Long.valueOf(idtblUser));
        Usuario usuario_input = usuarioMapper.usuarioTypeToEntity(usuarioTypeInput);
        usuario_bd.name = usuario_input.name;
        usuario_bd.lastname = usuario_input.lastname;
        usuario_bd.createat = usuario_input.createat;
        return usuarioMapper.usuarioEntityToTypeResponse(usuario_input);
    }
}
