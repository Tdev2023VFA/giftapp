package com.swcat.giftapp.REST;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.swcat.giftapp.Models.demoModel;

@Path("/demo")
public class demoREST {
    demoModel dM = new demoModel(1, "Ginger Cat", "Meow");

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public demoModel getAll() {
        return dM;
    }
}
