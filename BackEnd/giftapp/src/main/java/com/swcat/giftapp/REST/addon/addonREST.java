package com.swcat.giftapp.REST.addon;

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

import com.swcat.giftapp.DAO.addonService;
import com.swcat.giftapp.Entities.addon;
import com.swcat.giftapp.Models.addon.addonAdapter;
import com.swcat.giftapp.Models.addon.addonNewModel;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/api/addons")
public class addonREST {
    @Autowired
    private addonService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getalladdon() {
        List<addon> lan = new ArrayList<addon>();

        boolean kk = false;

        try {
            lan = service.getall();

            if (lan.size() != 0) {
                kk = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (kk) {
            return Response.status(Status.OK).entity(lan).build();
        } else {
            return Response.status(Status.OK).entity("Nothing!").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getoneaddon(@PathParam("id") Integer id) {
        addon tmpan = null;

        boolean kk = false;

        try {
            tmpan = service.getone(id);

            if (tmpan != null) {
                kk = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (kk) {
            return Response.status(Status.OK).entity(tmpan).build();
        } else {
            return Response.status(Status.BAD_REQUEST).entity("Nothing!").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createnewaddon(addonNewModel annew) {
        if (annew.getAname() == null || annew.getAdescription() == null) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid Input").build();
        }

        boolean kk = false;

        addon tmpan = null;

        try {
            tmpan = new addonAdapter().convertFromModelToEntity(annew);

            kk = service.create(tmpan);
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (kk) {
            return Response.status(Status.CREATED).entity("Created new addon").build();
        } else {
            return Response.status(Status.BAD_REQUEST).entity("Nothing changed!").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateaddon(@PathParam("id") Integer id, addonNewModel annew) {
        if (annew.getAname() == null || annew.getAdescription() == null) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid Input").build();
        }

        boolean kk = false;

        addon tmpan = null;

        try {
            tmpan = new addonAdapter().convertFromModelToEntity(annew);
            tmpan.setCid(id);

            kk = service.save(tmpan);
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (kk) {
            return Response.status(Status.OK).entity("Updated addon!").build();
        } else {
            return Response.status(Status.BAD_REQUEST).entity("Nothing changed!").build();
        }
    }

    @GET
    @Path("/noaddontypes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getnoant() {
        long tmp = 0;

        tmp = service.numberofaddontypes();

        return Response.status(Status.OK).entity("{ \"noaddontypes\": " + tmp +" }").build();
    }
}
