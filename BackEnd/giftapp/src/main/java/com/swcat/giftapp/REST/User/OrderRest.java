package com.swcat.giftapp.REST.User;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.swcat.giftapp.DAO.OrderService;
import com.swcat.giftapp.Models.OrderUserModel;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/api/user/order")
public class OrderRest {
    
    @Autowired
    private OrderService orderService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrder(OrderUserModel order) throws URISyntaxException{      
        order = orderService.createOrder(order);
        return Response.status(201)
        .entity("Đã tạo đơn hàng thành công")
        .contentLocation(new URI("/api/user/order/id/"+order.getCid()))
        .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/uname/{uname}")
    public List<OrderUserModel> getAllOrderByUserName(@PathParam("uname") String uname){
        List<OrderUserModel> orders = orderService.getOrderByUserName(uname);
        return orders;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/id/{id}")
    public Response getOrderById(@PathParam("id") int id) throws URISyntaxException{
        Boolean isOrderExist = orderService.isOrderExist(id);
        if(isOrderExist){
            OrderUserModel order = orderService.getOrderById(id);
            return Response.status(200)
            .entity(order)
            .contentLocation(new URI("/api/user/order/id/"+order.getCid()))
            .build();
        }
        else
        {
            return Response.status(404).entity("Không tìm thấy order").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrder(){
        List<OrderUserModel> orders = orderService.getAllOrder();
        return Response.status(200)
        .entity(orders)
        .build();
    }

}
