package com.example.magellan;

import jakarta.json.Json;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;

@Path("teszt_get")
public class HelloResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {
        return Response.ok("teszt1").build();
    }
    @GET
    @Path("int_back")
    @Produces(MediaType.APPLICATION_JSON)

    public Response hello2() {
        int szam = 5;
        return Response.ok(szam).build();
    }

   @POST
   @Path("writeBack")
   @Consumes(MediaType.TEXT_PLAIN)
   @Produces(MediaType.TEXT_PLAIN)
    public String writeBack(String word){
return word+" teszt";
    }

}

