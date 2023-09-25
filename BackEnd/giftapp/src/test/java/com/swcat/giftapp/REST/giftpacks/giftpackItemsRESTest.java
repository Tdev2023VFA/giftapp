package com.swcat.giftapp.REST.giftpacks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.swcat.giftapp.DAO.GiftpackItemsService;
import com.swcat.giftapp.Entities.giftpackitems;
import com.swcat.giftapp.REST.GiftpackItemsRest;

import org.apache.tomcat.util.json.JSONParser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(GiftpackItemsRest.class)
public class giftpackItemsRESTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GiftpackItemsService giftpackItemsService;

    @Test
    public void testGetAllGiftpack() throws Exception{
        List<giftpackitems> listGiftpack = new ArrayList<>();
        listGiftpack.add(new giftpackitems(1, 1, 1));
        listGiftpack.add(new giftpackitems(2, 2, 2));
        Mockito.when(giftpackItemsService.getAllGiftpackItems()).thenReturn(listGiftpack);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/api/giftpackitems");

        MvcResult mvcResult =  mockMvc.perform(requestBuilder).andReturn();

        String response = mvcResult.getResponse().getContentAsString();

        Integer id = JsonPath.parse(response).read("$[0].cid");

        assertEquals(1, id);

    }
    
    
}
