package com.swcat.giftapp.REST.giftpacks;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.swcat.giftapp.DAO.giftpackService;
import com.swcat.giftapp.Entities.giftpack;
import com.swcat.giftapp.Models.giftpack.giftpackAdapter;
import com.swcat.giftapp.Models.giftpack.giftpackNewModel;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/api/giftpacks")
public class giftpacksREST {
    @Autowired
    private giftpackService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getallgiftpack() {
        List<giftpack> lgp = new ArrayList<giftpack>();

        boolean kk = false;

        try {
            lgp = service.getall();

            if (lgp.size() != 0) {

                kk = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (kk) {
            return Response.status(Status.OK).entity(lgp).build();
        } else {
            return Response.status(Status.OK).entity("Nothing!").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getonegp(@PathParam("id") Integer id) {
        giftpack tmpgp = null;

        boolean kk = false;

        try {
            tmpgp = service.getone(id);

            if(tmpgp != null) {

                kk = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (kk) {
            return Response.status(Status.OK).entity(tmpgp).build();
        } else {
            return Response.status(Status.BAD_REQUEST).entity("Nothing!").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createnewgp(giftpackNewModel gpnew) {
        if (gpnew.getGname() == null || gpnew.getGdescription() == null || gpnew.getThumbnailimg() == null) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid Input!").build();
        }

        boolean kk = false;

        giftpack tmpgp = new giftpack();

        try {
            tmpgp = new giftpackAdapter().convertModelToEntity(gpnew);

            kk = service.create(tmpgp);
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (kk) {
            return Response.status(Status.CREATED).entity("Created new giftpack").build();
        } else {
            return Response.status(Status.BAD_REQUEST).entity("Nothing changed!").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createnewgp(@PathParam("id") Integer id, giftpackNewModel gpnew) {
        if (gpnew.getGname() == null || gpnew.getGdescription() == null || gpnew.getThumbnailimg() == null) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid Input!").build();
        }

        boolean kk = false;

        giftpack tmpgp = new giftpack();

        try {
            tmpgp = new giftpackAdapter().convertModelToEntity(gpnew);
            tmpgp.setCid(id);

            kk = service.save(tmpgp);
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (kk) {
            return Response.status(Status.OK).entity("Updated giftpack").build();
        } else {
            return Response.status(Status.BAD_REQUEST).entity("Nothing changed!").build();
        }
    }

    @GET
    @Path("/nogiftpacktypes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getnogptypes() {
        long nogpt = 0;

        nogpt = service.getnogptypes();

        return Response.status(Status.OK).entity("{ \"numberofgiftpacktypes\": " + nogpt + " }").build();
    }
}
