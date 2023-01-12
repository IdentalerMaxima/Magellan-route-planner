package com.magellan.user;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Path("user")
public class UserController {
    @Inject
    private UsersService usersService;

    private static final Logger logger = LogManager.getLogger(UserController.class);

    @GET
    @Path("loginstatus/{username}")
    public Response getUserLoginStatus(@PathParam("username") String username) {
        logger.info("getUserLoginStaus meghívása.");
        User user = usersService.getUserFromDatabase(username);
        if (user == null) {
            return Response.ok("User not found!").build();
        }
        if (usersService.isUserLoggedIn(user)) {
            return Response.ok("User logged in and user is admin: " + user.isAdmin() + "!").build();
        } else {
            return Response.ok("User not logged in!").build();
        }
    }

    @GET
    @Path("logout/{username}")
    public Response userLogout(@PathParam("username") String username) {
        logger.info("userLogout meghívása.");
        User user = usersService.getUserFromDatabase(username);
        if (user == null) {
            return Response.ok("user not found!").build();
        }
        if (usersService.isUserLoggedIn(user)) {
            User.loggedInUsers.remove(usersService.getLoggedInUserInstanceIndexInArray(user));
            return Response.ok("User logged out!").build();
        } else {
            return Response.ok("User was already logged out!").build();
        }
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response userLogin(final LoginParameterBean loginInfo) {
        logger.info("userLogin meghívása.");
        User tempUser = usersService.getUserFromDatabase(loginInfo.username);
        String responseText;

        if (tempUser == null) {
            return Response.ok("User not found!").build();
        }

        if (!usersService.encryptPassword(loginInfo.password).equals(tempUser.getPassword())) {
            responseText = "Incorrect password";
        } else {
            responseText = usersService.logUserIn(tempUser) ? "Login successful!" : "User already logged in!";
        }
        return Response.ok(responseText).build();
    }

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(final RegisterParameterBean userInfo) {
        logger.info("registerUser meghívása.");
        User tempUser = usersService.getUserFromDatabase(userInfo.username);
        if (tempUser != null) {
            return Response.ok("username is taken!").build();
        }

        tempUser = new User(userInfo.username, false, userInfo.email, usersService.encryptPassword(userInfo.password));
        tempUser = usersService.insertUserIntoDatabase(tempUser);
        User.loggedInUsers.add(tempUser);
        return Response.ok("Successful registration!").build();
    }
}


