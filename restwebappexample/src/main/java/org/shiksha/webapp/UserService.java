package org.shiksha.webapp;

import java.io.IOException;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletResponse;

@Path("/UserService")
public class UserService
{
    UserDao userDao = new UserDao(DevMode.DEV_MODE);


    private static final String SUCCESS_RESULT = "{\"result\":\"success\"}";
    private static final String FAILURE_RESULT = "{\"result\":\"failure\"}";

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User2> getUsers()
    {
        return userDao.getAllUsers();

    }

    @GET
    @Path("/users/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public User2 getUser(@PathParam("userid") int userid)
    {
        return userDao.getUser(userid);
    }

    @POST
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String createUser(@FormParam("id") int id, @FormParam("name") String name, @FormParam("profession") String profession, @Context HttpServletResponse servletResponse) throws IOException
    {
        User2 user = new User2(id, name, profession);
        int result = userDao.addUser(user);
        if(result == 0)
            return SUCCESS_RESULT;

        return FAILURE_RESULT;
    }

    @PUT
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String updateUser(@FormParam("id") int id, @FormParam("name") String name, @FormParam("profession") String profession, @Context HttpServletResponse servletResponse) throws IOException
    {
        User2 user = new User2(id, name, profession);
        int result = userDao.updateUser(user);
        if(0 == result)
            return SUCCESS_RESULT;

        return FAILURE_RESULT;
    }

    @DELETE
    @Path("/users/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("userid") int id)
    {
        int result = userDao.deleteUser(id);
        if(0 == result)
            return SUCCESS_RESULT;

        return FAILURE_RESULT;
    }


}
