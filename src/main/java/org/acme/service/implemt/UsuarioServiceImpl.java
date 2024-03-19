package org.acme.service.implemt;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.dao.UsuarioDao;
import org.acme.entity.Usuario;
import org.acme.gen.type.UsuarioTypeInput;
import org.acme.gen.type.UsuarioTypeResponse;
import org.acme.service.contrat.IUsuarioService;
import org.acme.utils.ApplicationException;
import org.acme.utils.UsuarioMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.List;

import static org.acme.constant.Constant.ERROR_SERVICIO;

@ApplicationScoped
public class UsuarioServiceImpl implements IUsuarioService {
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    @Inject
    UsuarioMapper usuarioMapper;
    @Inject
    UsuarioDao usuarioDao;

    @Transactional
    public UsuarioTypeResponse crearUsuario(UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Inicio creación usuario implementación");
        try {
            Usuario usuario = usuarioMapper.usuarioTypeToEntity(usuarioTypeInput);
            usuarioDao.persist(usuario);
            LOG.info("Fin creación usuario implementación");
            return usuarioMapper.usuarioEntityToTypeResponse(usuario);
        } catch (ApplicationException e) {
            LOG.error("Se identifico un error en el servicio crearUsuario" + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }

    }

    @Transactional
    public void eliminarUsuario(Integer idtblUser) {
        LOG.info("Inicio eliminación usuario implementación");
        try {
            Long id = Long.valueOf(idtblUser);
            usuarioDao.deleteById(id);
            LOG.info("Fin eliminación usuario implementación");
        } catch (ApplicationException e) {
            LOG.error("Se identifico un error en el servicio eliminarUsuario" + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Transactional
    public UsuarioTypeResponse actualizarUsuario(Integer idtblUser, UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Inicio actualización usuario implementación");
        try {
            Usuario usuario_bd = usuarioDao.findById(Long.valueOf(idtblUser));
            Usuario usuario_input = usuarioMapper.usuarioTypeToEntity(usuarioTypeInput);
            usuario_bd.name = usuario_input.name;
            usuario_bd.lastname = usuario_input.lastname;
            usuario_bd.createat = usuario_input.createat;
            LOG.info("Fin actualización usuario implementación");
            return usuarioMapper.usuarioEntityToTypeResponse(usuario_input);
        } catch (ApplicationException e) {
            LOG.error("Se identifico un error en el servicio actualizarUsuario" + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    public List<UsuarioTypeResponse> listarUsuarios() {
        LOG.info("Inicio listar usuarios implementación");
        try {
            List<Usuario> usuarios = usuarioDao.listAll();
            LOG.info("Fin listar usuarios implementación");
            return usuarioMapper.usuariosTypeListEntityToTypeResponse(usuarios);
        } catch (ApplicationException e) {
            LOG.error("Se identifico un error en el servicio listarUsuarios" + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    public UsuarioTypeResponse listarUsuario(Integer idtblUser) {
        LOG.info("Inicio listar usuario implementación");
        try {
            Usuario usuario = usuarioDao.findById(Long.valueOf(idtblUser));
            LOG.info("Fin listar usuario implementación");
            return usuarioMapper.usuarioEntityToTypeResponse(usuario);
        } catch (ApplicationException e) {
            LOG.error("Se identifico un error en el servicio listarUsuario" + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }
}
