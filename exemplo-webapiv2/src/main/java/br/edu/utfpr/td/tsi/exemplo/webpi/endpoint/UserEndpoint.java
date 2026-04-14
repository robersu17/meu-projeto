package br.edu.utfpr.td.tsi.exemplo.webpi.endpoint;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.td.tsi.exemplo.webpi.modelo.User;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("usuarios")
public class UserEndpoint {

    private static List<User> usuarios = new ArrayList<>();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(User user) {
        usuarios.add(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        return Response.ok(usuarios).build();
    }
}
