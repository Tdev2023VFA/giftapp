package com.swcat.giftapp.REST;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.swcat.giftapp.DAO.GiftpackItemsService;
import com.swcat.giftapp.DAO.giftpackService;
import com.swcat.giftapp.Entities.giftpackitems;
import com.swcat.giftapp.Models.giftpackitemsModel;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/api/giftpackitems")
public class GiftpackItemsRest {
    @Autowired
    private GiftpackItemsService giftpackItemsService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGiftpackItems(giftpackitemsModel giftpackitemsModel){
        System.out.println(giftpackitemsModel);
        giftpackItemsService.createGiftpackItems(giftpackitemsModel);
        return Response.status(201)
        .entity("Lưu thành công giftpackItem")
        .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGiftpackItems(giftpackitems giftpackitems, @PathParam("id") int id){
        giftpackitems.setCid(id);
        giftpackItemsService.updateGifpackItems(giftpackitems);
        return Response.status(200).entity("Update thành công").build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public giftpackitemsModel getGiftpackItemsById(@PathParam("id") int giftpackId){
        return giftpackItemsService.getGiftpackItemsById(giftpackId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<giftpackitems> getAllGiftpack(){
        return giftpackItemsService.getAllGiftpackItems();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteGiftpackItemsById(@PathParam("id") int id){
        giftpackItemsService.deleteGiftpackItems(id);
        return Response.status(200).entity("Xóa giftpackItem thành công").build();
    }
}
