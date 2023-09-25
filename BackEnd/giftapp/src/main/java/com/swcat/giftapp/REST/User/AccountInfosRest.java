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

import com.swcat.giftapp.DAO.AccountInfoService;
import com.swcat.giftapp.Entities.accountInfo;
import com.swcat.giftapp.Models.accountInfo.accountInfoModel;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/api/accountinfos")
public class AccountInfosRest {
    
    @Autowired
    private AccountInfoService accountInfoService;
    /**
     * 
     * POST
     * Lưu accountInfo vào database
     * @param accountInfoModel
     * @return
     * @throws URISyntaxException
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccountInfo(accountInfoModel accountInfoModel) throws URISyntaxException{
        accountInfoModel = accountInfoService.createAccountInfo(accountInfoModel);
        return Response.status(201)
        .entity("Lưu thành công accountInforService")
        .contentLocation(new URI("api/accountinfos/" + accountInfoModel.getUname()))
        .build();
    }

    /**
     * PUT
     * update accountInfo
     * @param uname
     * @param accountInfoModel
     * @return
     * @throws URISyntaxException
     */
    @PUT
    @Path("/{uname}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAccountInfo(@PathParam("uname") String uname, accountInfoModel accountInfoModel) throws URISyntaxException{
        boolean isAccountInfoExist = accountInfoService.isAccountInfoExist(uname);
        if(isAccountInfoExist){
            accountInfoModel.setUname(uname);
            accountInfoService.updateAccountInfo(accountInfoModel);
            return Response.status(200)
            .entity("AccountInfo đã được update")
            .build();
        }
        else
        {
            return Response.status(404)
            .entity("AccountInfo không được tìm thấy")
            .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<accountInfoModel> getAllAccountInfoModels(){
        return accountInfoService.getAccountInfos();
    }

    @GET
    @Path("/{uname}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountInfoByName(@PathParam("uname") String uname) throws URISyntaxException{
        boolean isAccountInfoExist = accountInfoService.isAccountInfoExist(uname);
        if(isAccountInfoExist){
            accountInfoModel accountInfoModel = accountInfoService.getAccountInfo(uname);
            return Response.status(200)
            .entity(accountInfoModel)
            .contentLocation(new URI("api/accountinfos/" + accountInfoModel.getUname()))
            .build();
        }
        else{
            return Response.status(404)
            .entity("không tìm thấy AccountInfo")
            .build();
        }
    }

    @DELETE
    @Path("/{uname}")
    public Response deleteAccountInfo(@PathParam("uname") String uname){
        boolean isAccountInfoExist = accountInfoService.isAccountInfoExist(uname);
        if(isAccountInfoExist){
            accountInfoService.deleteAccountInfo(uname);
            return Response.status(200).entity("Xóa accountInfo thành công").build();
        }
        else{
            return Response.status(404)
            .entity("không tìm thấy AccountInfo")
            .build();
        }
    }


}
