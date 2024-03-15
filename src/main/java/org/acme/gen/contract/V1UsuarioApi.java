package org.acme.gen.contract;

import org.acme.gen.type.UsuarioTypeInput;
import org.acme.gen.type.UsuarioTypeResponse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;




import java.io.InputStream;
import java.util.Map;
import java.util.List;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;


@Path("/v1/es")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2024-03-15T17:26:26.340831300-05:00[America/Bogota]")
public interface V1UsuarioApi {

    @PUT
    @Path("/actualizar/usuario/{idtbl_user}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    List<UsuarioTypeResponse> actualizarUsuario(@PathParam("idtbl_user") @Min(1) Integer idtblUser,@Valid UsuarioTypeInput usuarioTypeInput);

    @POST
    @Path("/usuario")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    List<UsuarioTypeResponse> crearUsuario(@Valid UsuarioTypeInput usuarioTypeInput);

    @DELETE
    @Path("/eliminar/usuario/{idtbl_user}")
    void eliminarUsuario(@PathParam("idtbl_user") @Min(1) Integer idtblUser);

    @GET
    @Path("/usuarios")
    @Produces({ "application/json" })
    List<UsuarioTypeResponse> listarTodosLosUsuario();

    @GET
    @Path("/usuario/{idtbl_user}")
    @Produces({ "application/json" })
    List<UsuarioTypeResponse> listarUsuario(@PathParam("idtbl_user") @Min(1) Integer idtblUser);
}
