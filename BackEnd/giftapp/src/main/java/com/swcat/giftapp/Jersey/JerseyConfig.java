package com.swcat.giftapp.Jersey;

import javax.ws.rs.ApplicationPath;

import com.swcat.giftapp.REST.GiftpackItemsRest;
import com.swcat.giftapp.REST.demoREST;
import com.swcat.giftapp.REST.User.AccountInfosRest;
import com.swcat.giftapp.REST.User.GOrderAddOnUserRest;
import com.swcat.giftapp.REST.User.OrderRest;
import com.swcat.giftapp.REST.account.accountsREST;
import com.swcat.giftapp.REST.addon.addonREST;
import com.swcat.giftapp.REST.comments.commentsREST;
import com.swcat.giftapp.REST.delivery.deliveryREST;
import com.swcat.giftapp.REST.giftpacks.giftpacksREST;
import com.swcat.giftapp.REST.items.itemsREST;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
@ApplicationPath("/")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(ServletResponseFilter.class);

        // register REST class here
        register(demoREST.class); // demo
        // stable
        register(deliveryREST.class);
        register(commentsREST.class);
        register(accountsREST.class);
        register(AccountInfosRest.class);
        register(GOrderAddOnUserRest.class);
        register(OrderRest.class);
        register(itemsREST.class);
        register(giftpacksREST.class);
        register(addonREST.class);
        register(GiftpackItemsRest.class);
    }
}
