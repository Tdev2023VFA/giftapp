package com.swcat.giftapp.REST.account;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.swcat.giftapp.DAO.accountService;
import com.swcat.giftapp.Entities.account;
import com.swcat.giftapp.Models.demoModel;
import com.swcat.giftapp.Models.account.SignIn;
import com.swcat.giftapp.Models.account.SignUp;

import org.springframework.beans.factory.annotation.Autowired;

@Path("/api/accounts")
public class accountsREST {
    @Autowired
    private accountService accService;

    // GET list

    // GET one

    /**
     * POST
     * Sign In
     * @param signinacc
     * @return
     */
    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signin(SignIn signinacc) {
        if(signinacc.getUsername() == null || signinacc.getPassword() == null || signinacc.getAccountType() == null) {
            return Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST.getStatusCode()).build();
        }

        boolean kk = false;
        account acc = null; // new account();
        try {
            acc = accService.get(signinacc.getUsername());
            if(acc.getUname().equals(signinacc.getUsername()) && acc.getPasswd().equals(signinacc.getPassword()) && acc.getAcctype().equals(signinacc.getAccountType())) {
                kk = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        if(kk == false) {
            return Response.status(Status.BAD_REQUEST).entity("Invalid account's infos!").build();
        } else {
            return Response.status(Status.OK).entity(Status.OK.getStatusCode()).build();
        }
    }
    
    /**
     * POST
     * Sign Up
     * @param signupacc
     * @return
     */
    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(SignUp signupacc) {
        if(signupacc.getUsername() == null || signupacc.getPassword() == null) {
            return Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST.getStatusCode()).build();
        }

        account acc = new account();
        acc.setAcctype(1);
        acc.setUname(signupacc.getUsername());
        acc.setPasswd(signupacc.getPassword());

        boolean k = accService.create(acc);

        if(k == false) {
            return Response.status(Status.BAD_REQUEST).entity(Status.BAD_REQUEST.getStatusCode()).build();
        }

        return Response.status(Status.CREATED).entity(Status.CREATED.getStatusCode()).build();
    }

    // PUT
}
