package com.magellan.route;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.List;

@Path("route")
public class RouteController {

    private static final Logger logger = LogManager.getLogger(RouteController.class);

    @Inject
    private RouteService routeService;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRoutes() {
        logger.info("getAllRoutes meghívása.");
        List<Route> routeList = routeService.getAllRoutesFromDatabase();
        if(routeList == null){
            return Response.ok("No routes available! :(").build();
        }

        JSONArray routeArr=new JSONArray();
        for(Route route : routeList){
            String jsonString = routeService.convertRouteToJsonString(route);
            JSONObject routeJson = new JSONObject(jsonString);
            routeArr.put(routeJson);
        }

        return Response.ok(routeArr.toString()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoute(@PathParam("id") int id){
        logger.info("getRoute meghívása.");
        Route route = routeService.getRouteFromDatabase(id);
        if(route == null) return Response.ok("Route not found").build();

        JSONObject jsonRoute = new JSONObject(routeService.convertRouteToJsonString(route));
        return Response.ok(jsonRoute.toString()).build();
    }

    @GET
    @Path("{id}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComment(@PathParam("id") int id){
        logger.info("getAllComment meghívása.");
        List<Comment> comments = routeService.getAllCommentsFromDataBaseForRoute(id);
        if(comments == null ) return Response.ok("Route not found").build();
        JSONArray commentArr = new JSONArray();
        for(Comment comment : comments){
            JSONObject jsonComment = new JSONObject(routeService.convertCommentToJsonString(comment));
            commentArr.put(jsonComment);
        }
        return Response.ok(commentArr.toString()).build();
    }

    @POST
    @Path("{id}/rate")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCommentToRoute(final RatingParamBean rating, @PathParam("id") int routeId){
        logger.info("addCommentToRoute meghívása.");
        Comment tempComment = new Comment(rating.comment, routeId, rating.username, rating.rating);
        routeService.saveCommentToDatabase(tempComment);
        return Response.ok("Comment added!").build();
    }

}
