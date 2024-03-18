package org.acme.controller;

import jakarta.inject.Inject;
import org.acme.dao.UsuarioDao;
import org.acme.gen.contract.V1UsuarioApi;
import org.acme.gen.type.UsuarioTypeInput;
import org.acme.gen.type.UsuarioTypeResponse;
import org.acme.service.implemt.UsuarioServiceImpl;
import org.acme.utils.ApplicationException;
import org.acme.utils.UsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

import static org.acme.constant.Constant.ERROR_SERVICIO;

public class UsuarioController implements V1UsuarioApi {
    private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);
    @Inject
    UsuarioServiceImpl usuarioService;
    @Inject
    UsuarioDao usuarioDao;
    @Inject
    UsuarioMapper usuarioMapper;

    @Override
    public List<UsuarioTypeResponse> crearUsuario(UsuarioTypeInput usuarioTypeInput) {
        try {
            LOG.info("Inicia creacion usuario");
            List<UsuarioTypeResponse> usuario = usuarioService.crearUsuario(usuarioTypeInput);
            LOG.info("Fin creacion usuario");
            return usuario;
        } catch (ApplicationException e) {
            LOG.error("Se identifico un error en el servicio " + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Override
    public void eliminarUsuario(Integer idtblUser) {
        try {
            LOG.info("Inicia eliminación usuario");
            usuarioService.eliminarUsuario(idtblUser);
            LOG.info("Finaliza eliminación usuario");
        } catch (ApplicationException e) {
            LOG.error("Se identifico un error en el servicio " + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Override
    public List<UsuarioTypeResponse> listarTodosLosUsuario() {
        try {
            LOG.info("Inicia listar todos los usuarios");
            List<UsuarioTypeResponse> usuarios = usuarioService.listarUsuarios();
            LOG.info("Finaliza listar todos los usuarios");
            return usuarios;
        } catch (ApplicationException e) {
            LOG.error("Se identifico un error en el servicio " + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Override
    public List<UsuarioTypeResponse> listarUsuario(Integer idtblUser) {
        try {
            LOG.info("Inicia listar usuario");
            List<UsuarioTypeResponse> usuario = usuarioService.listarUsuario(idtblUser);
            LOG.info("Finaliza listar usuario");
            return usuario;
        } catch (ApplicationException e) {
            LOG.error("Se identifico un error en el servicio " + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }

    @Override
    public List<UsuarioTypeResponse> actualizarUsuario(Integer idtblUser, UsuarioTypeInput usuarioTypeInput) {
        try {
            LOG.info("Inicia actualizar usuario");
            List<UsuarioTypeResponse> usuario = usuarioService.actualizarUsuario(idtblUser, usuarioTypeInput);
            LOG.info("Inicia actualizar usuario");
            return usuario;
        } catch (ApplicationException e) {
            LOG.error("Se identifico un error en el servicio " + e.getMessage());
            throw new ApplicationException(ERROR_SERVICIO + e.getMessage());
        }
    }
}
