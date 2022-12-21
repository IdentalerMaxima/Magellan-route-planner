package com.example.magellan;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Path("magellan")
public class MagellanResource {

    @Inject
    private UsersService usersService;

    private static final Logger logger = LogManager.getLogger(MagellanResource.class);

    @GET
    @Path("getuserloginstatus/{username}")
    public Response getUserLoginStatus(@PathParam("username") String username){
        User user = usersService.getUser(username);
        if(user == null){
            return Response.ok("User not found!").build();
        }
        if(User.loggedInUsers.contains(user.getUsername())){
            return Response.ok("User logged in!").build();
        }else{
            return Response.ok("User not logged in!").build();
        }
    }

    @GET
    @Path("logout/{username}")
    public Response logout(@PathParam("username") String username){
        if(User.loggedInUsers.contains(username)){
            User.loggedInUsers.remove(username);
            return Response.ok("User logged out!").build();
        }else{
            return Response.ok("User was already logged out!").build();
        }
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(final LoginParameterBean loginInfo){
        logger.info("login called");
        User tempUser = usersService.getUser(loginInfo.username);
        if(tempUser==null){return Response.ok("User not found!").build();}
        if(usersService.encryptPassword(loginInfo.password).equals(tempUser.getPassword())){
            User.loggedInUsers.add(tempUser.getUsername());
            return Response.ok("Successful login!").build();
        }
        else{
            return Response.ok("Incorrect password").build();
        }
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(final RegisterParameterBean userInfo){
        User tempUser = usersService.getUser(userInfo.username);
        if(tempUser!=null){
            return Response.ok("username is taken!").build();
        }

        tempUser = new User(userInfo.username, userInfo.email, usersService.encryptPassword(userInfo.password));
        tempUser = usersService.insertUserIntoDB(tempUser);
        User.loggedInUsers.add(tempUser.getUsername());
        return Response.ok("Successful registration!").build();
    }


}

