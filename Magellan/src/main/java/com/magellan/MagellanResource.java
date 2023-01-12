package com.magellan;

import com.magellan.user.User;
import com.magellan.user.UsersService;
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

}

