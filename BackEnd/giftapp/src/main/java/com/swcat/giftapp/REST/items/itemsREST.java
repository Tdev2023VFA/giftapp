package com.swcat.giftapp.REST.items;

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

import com.swcat.giftapp.DAO.itemService;
import com.swcat.giftapp.Entities.item;
import com.swcat.giftapp.Models.item.itemAdapter;
import com.swcat.giftapp.Models.item.itemNewModel;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/api/items")
public class itemsREST {
    @Autowired
    private itemService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getallitem() {
        List<item> itms = new ArrayList<item>();

        boolean kk = false;

        try {
            itms = service.getall();

            if(itms.size() != 0) {

                kk = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        if(kk) {
            return Response.status(Status.OK).entity(itms).build();
        } else {
            return Response.status(Status.OK).entity("Nothing!").build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getone(@PathParam("id") Integer id) {
        item itmp = null;

        boolean kk = false;

        try {
            itmp = service.get(id);

            if (itmp != null) {

                kk = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (kk) {
            return Response.status(Status.OK).entity(itmp).build();
        } else {
            return Response.status(Status.BAD_REQUEST).entity("Nothing!").build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response createnewitem(itemNewModel itemnew) {
        if(itemnew.getItemName() == null || itemnew.getItemDescription() == null || itemnew.getItemDescription() == null || itemnew.getThumbnailimg() == null) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid Info").build();
        }

        boolean kk = false;

        item itmp = new item();

        try {
            itmp = new itemAdapter().convertModelToEntity(itemnew);

            kk = service.create(itmp);
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (kk) {
            return Response.status(Status.CREATED).entity("Created new item.").build();
        } else {
            return Response.status(Status.BAD_REQUEST).entity("Nothing changed!").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateitem(@PathParam("id") Integer id, itemNewModel itemnew) {
        if(itemnew.getItemName() == null || itemnew.getItemDescription() == null || itemnew.getItemDescription() == null || itemnew.getThumbnailimg() == null) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid Info").build();
        }

        boolean kk = false;

        item itmp = new item();

        try {
            itmp = new itemAdapter().convertModelToEntity(itemnew);
            itmp.setCid(id);

            kk = service.save(itmp);
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (kk) {
            return Response.status(Status.OK).entity("Update item.").build();
        } else {
            return Response.status(Status.BAD_REQUEST).entity("Nothing changed!").build();
        }
    }

    @GET
    @Path("/noitemtypes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getnotype() {
        long noit = 0;

        noit = service.getnoitemtype();

        return Response.status(Status.OK).entity("{ \"numberofitemtypes\": " + noit + " }").build();
    }
}
