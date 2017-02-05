package co.yellowbricks.boxed.resources;

import co.yellowbricks.boxed.api.PlayV1;
import co.yellowbricks.boxed.service.PlayService;
import co.yellowbricks.boxed.session.RequiresSession;
import co.yellowbricks.boxed.session.SessionManager;
import com.google.inject.Inject;
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

    private final PlayService playService;
    private final SessionManager sessionManager;

    @Inject
    public PlayResource(PlayService playService, SessionManager sessionManager) {
        this.playService = playService;
        this.sessionManager = sessionManager;
    }

    @POST
    @Path("/{bgg_game_id}")
    @RequiresSession
    public Response logPlay(@PathParam("bgg_game_id") @NotNull Long bggGameId) {
        String userId = sessionManager.getLoggedUser().userId;

        PlayV1 play = PlayV1.fromDomain(playService.logPlay(bggGameId, userId));

        return Response.ok(play).build();
    }

    @DELETE
    @Path("/{play_id}")
    @RequiresSession
    public Response deletePlay(@PathParam("play_id") @NotNull String playId) {
        String userId = sessionManager.getLoggedUser().userId;

        playService.deletePlay(playId, userId);
        return Response.ok().build();
    }
}
