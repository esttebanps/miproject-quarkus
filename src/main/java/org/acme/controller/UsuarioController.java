package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.acme.constant.Constant;
import org.acme.dao.UsuarioDao;
import org.acme.gen.contract.V1UsuarioApi;
import org.acme.gen.type.UsuarioTypeInput;
import org.acme.gen.type.UsuarioTypeResponse;
import org.acme.service.implemt.UsuarioServiceImpl;
import org.acme.utils.ApplicationException;
import org.acme.utils.UsuarioMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public Response crearUsuario(UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Inicia creacion usuario controller");
        UsuarioTypeResponse usuario = null;
        try {
            usuario = usuarioService.crearUsuario(usuarioTypeInput);
            LOG.info("Fin creacion usuario controller");
        } catch (ApplicationException e) {
            LOG.error("Se identifico un error en el servicio " + e.getMessage() + "crearUsuarioController");
            return Response.status(Response.Status.BAD_REQUEST).entity(usuario).build();
        }
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    @Override
    public Response eliminarUsuario(Integer idtblUser) {
        try {
            LOG.info("Inicia eliminación usuario controlles");
            usuarioService.eliminarUsuario(idtblUser);
            LOG.info("Finaliza eliminación usuario controlles");
        } catch (ApplicationException e) {
            LOG.error("Se identifico un error en el servicio " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Override
    public Response listarTodosLosUsuario() {
        LOG.info("Inicia listar todos los usuarios controller");
        List<UsuarioTypeResponse> usuarios = null;
        try {
            usuarios = usuarioService.listarUsuarios();
            LOG.info("Finaliza listar todos los usuarios controller");
        } catch (ApplicationException e) {
            LOG.error("Se identifico un error en el servicio " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(usuarios).build();
        }
        return Response.status(Response.Status.OK).entity(usuarios).build();
    }

    @Override
    public Response listarUsuario(Integer idtblUser) {
        LOG.info("Inicia listar usuario controller");
        UsuarioTypeResponse usuario = null;
        try {
            usuario = usuarioService.listarUsuario(idtblUser);
            LOG.info("Finaliza listar usuario controller");
        } catch (ApplicationException e) {
            LOG.error("Se identifico un error en el servicio " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(usuario).build();
        }
        return Response.status(Response.Status.OK).entity(usuario).build();
    }

    @Override
    public Response actualizarUsuario(Integer idtblUser, UsuarioTypeInput usuarioTypeInput) {
        LOG.info("Inicia actualizar usuario controller");
        UsuarioTypeResponse usuario = null;
        try {
            usuario = usuarioService.actualizarUsuario(idtblUser, usuarioTypeInput);
            LOG.info("Inicia actualizar usuario controller");
        } catch (ApplicationException e) {
            LOG.error("Se identifico un error en el servicio " + e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(usuario).build();
        }
        return Response.status(Response.Status.OK).entity(usuario).build();
    }
}
