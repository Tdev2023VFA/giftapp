package com.swcat.giftapp.DAO;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.swcat.giftapp.Entities.giftpackitems;
import com.swcat.giftapp.JpaRepo.giftpackitemsRepo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class TestGiftpackItemService {

    @MockBean
    private giftpackitemsRepo giftpackitemsRepo;

    @InjectMocks
    private GiftpackItemsService giftpackItemsService;

    @Test
    public void testGetAllGiftpackItems(){
        List<giftpackitems> listGiftpack = new ArrayList<>();
        listGiftpack.add(new giftpackitems(1, 1, 1));
        listGiftpack.add(new giftpackitems(2, 2, 2));
        
        Mockito.when(giftpackitemsRepo.findAll()).thenReturn(listGiftpack);

        List<giftpackitems> giftpackitems = giftpackItemsService.getAllGiftpackItems();

        assertEquals(listGiftpack.get(1).getCid(), giftpackitems.get(1).getCid());
    }

}
