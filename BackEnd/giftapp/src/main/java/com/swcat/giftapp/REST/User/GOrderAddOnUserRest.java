package com.swcat.giftapp.REST.User;

import java.net.URI;
import java.net.URISyntaxException;
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

import com.swcat.giftapp.DAO.GOrderAddonService;
import com.swcat.giftapp.Entities.addon;
import com.swcat.giftapp.Entities.gorderAddons;
import com.swcat.giftapp.JpaRepo.addonRepo;
import com.swcat.giftapp.JpaRepo.gorderAddonsRepo;
import com.swcat.giftapp.JpaRepo.gorderRepo;
import com.swcat.giftapp.Models.addon.GOrderAddOnModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;

@Path("/api/gorderaddon")
public class GOrderAddOnUserRest {
    
    @Autowired
    private GOrderAddonService gOrderAddonService;
    @Autowired
    private gorderAddonsRepo gorderAddonsRepo;
    @Autowired
    private addonRepo addonRepo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createGOrderAddOn(GOrderAddOnModel gOrderAddOnModel) throws URISyntaxException{
        //System.out.println(gOrderAddOnModel);
        gOrderAddOnModel = gOrderAddonService.createGOrderAddOn(gOrderAddOnModel);
        return Response.status(201)
        .entity("Lưu thành công gOrderAddOn")
        .contentLocation(new URI("/api/gorderaddon/"+gOrderAddOnModel.getGorderId()))
        .build();
    }

    @PUT
    @Path("/{cid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGOrderAddOn(@PathParam("cid") int cid, GOrderAddOnModel gOrderAddOnModel){
            gOrderAddOnModel.setCid(cid);
            gOrderAddonService.updateGOrderAddOn(gOrderAddOnModel);
          
            return Response.status(200)
            .entity("GOrderAddOn update thành công")
            .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<GOrderAddOnModel> getAllGOrderAddOn(){
        return gOrderAddonService.getAllGOrderAddOn();
    }

    @GET
    @Path("/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGOrderAddOnById(@PathParam("orderId") int orderId) throws URISyntaxException{
        boolean isGOrderAddOnExist = gOrderAddonService.isGOrderAddOnExist(orderId);
        /**
         * Bởi vì không có addOn để test nên dùng tạm addOn giả cái này sau này sẽ bị xóa
         */
        // addon addon = new addon(null, "test1", "dung de test", 10000);
        // addonRepo.save(addon);
        //=>>>>>>>>>>>>>>>>>>>>>>>>>>>>> Note: chỉ dùng để test<<<<<<<<<<<<<<<<<<<===
        if(isGOrderAddOnExist){
            List<GOrderAddOnModel> gOrderAddOn = gOrderAddonService.getGOrderAddOn(orderId);
            return Response.status(200)
            .entity(gOrderAddOn)
            .contentLocation(new URI("/api/gorderaddon/"+orderId))
            .build();
        }
        else
        {
            return Response.status(404).entity("Không có addon bạn có muốn thêm addOn").build();
        }
    }

    @DELETE
    @Path("/{cid}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteGOrderAddOn(@PathParam("cid") int cid){
        // boolean isGOrderAddOnExist = gOrderAddonService.isGOrderAddOnExist(orderId);
        // if(isGOrderAddOnExist){
        //     gOrderAddonService.deleteGOrderAddOnExist(orderId);
        //     return Response.status(200).entity("Đã xóa thành công addon").build();
        // }
        // else
        // {
        //     return Response.status(404).entity("Xóa không thành công addOn").build();
        // }
        try {
            gOrderAddonService.deleteGOrderAddOnExist(cid);
            return Response.status(200).entity("Đã xóa thành công addon").build();
        } catch (Exception e) {
            //TODO: handle exception
            return Response.status(404).entity("Xóa không thành công addOn").build();
        }

    }
}
