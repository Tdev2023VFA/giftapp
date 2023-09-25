package com.swcat.giftapp.REST.delivery;

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

import com.swcat.giftapp.DAO.deliveryService;
import com.swcat.giftapp.Models.delivery.orderProcessModel;

import org.springframework.beans.factory.annotation.Autowired;

/**COntrol delivery System to comfirm shipment and report the shipment state */
@Path("/api/delivery")
public class deliveryREST {
    @Autowired
    private deliveryService deliveryService;

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<orderProcessModel> getAllOrderProcess()
    {
        System.out.print("Going");
        return deliveryService.listOrderProcess();
    }

    @GET
    @Path("/Listdeliveried")
    @Produces(MediaType.APPLICATION_JSON)
    public List<orderProcessModel> getAllOrderProcessDeliveried()
    {
        int pdelivered=1;
        return deliveryService.listOrderProcessDeliveried(pdelivered);
    }


    @GET
    @Path("/Listcanceled")
    @Produces(MediaType.APPLICATION_JSON)
    public List<orderProcessModel> getAllOrderProcessCanceled()
    {
        int canceled=1;
        return deliveryService.listOrderProcessCanceled(canceled);
    }

    @POST
    @Path("/addOrderProcess")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrderProcessing(orderProcessModel orderProcessModel) throws URISyntaxException
    {
       
        boolean exist=deliveryService.ispOrderProcessExist(orderProcessModel.getGorderId());
        if(!exist)
        {
            orderProcessModel=deliveryService.addOrderProcessing(orderProcessModel);
            return Response.status(201).entity("Add success").build();
        }
        else
        {
            return Response.status(404).entity("Add unsucess").build();
        }
    }

    @PUT
    @Path("/update/{orderID}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrderProcess(@PathParam("orderID") int orderID,orderProcessModel orderProcessModel)
    {
        boolean exist=deliveryService.ispOrderProcessExist(orderID);
        if(exist)
        {
            System.out.print("Run into updateProcess");
            orderProcessModel.setGorderId(orderID);
            deliveryService.updateOrderProcess(orderProcessModel);
            return Response.status(200).entity("Update success").build();
        }
        else
        {
            return Response.status(404).entity("Update unsucess").build();
        }
    }
    @DELETE
    @Path("/delete/{orderID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrderProcess(@PathParam("orderID") int orderID)
    {
        boolean exist=deliveryService.ispOrderProcessExist(orderID);
        if(exist)
        {
            orderProcessModel orderProcessModel=deliveryService.getOrderProcessbyID(orderID);
            orderProcessModel.setPcanceled(1);
            orderProcessModel.setPstate("Canceled");
            deliveryService.updateOrderProcess(orderProcessModel);
            return Response.status(200).entity("Delete success").build();
        }
        return Response.status(404).entity("Delete unsuccess").build();
    }
}
