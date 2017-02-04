package co.yellowbricks.boxed.resources;

import com.google.inject.Singleton;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/play")
@Produces(APPLICATION_JSON)
@Singleton
public class PlayResource {

    @POST
    @Path("/{bgg_game_id}")
    public Response logPlay(@PathParam("bgg_game_id") @NotNull Long bggGameId) {
        return Response.ok().build();
    }

    @DELETE
    @Path("/{play_id}")
    public Response deletePlay(@PathParam("play_id") @NotNull Long bggGameId) {
        return Response.ok().build();
    }
}
