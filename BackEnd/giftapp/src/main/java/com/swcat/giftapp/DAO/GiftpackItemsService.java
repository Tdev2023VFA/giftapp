package com.swcat.giftapp.DAO;

import java.util.ArrayList;
import java.util.List;

import com.swcat.giftapp.Entities.giftpack;
import com.swcat.giftapp.Entities.giftpackitems;
import com.swcat.giftapp.JpaRepo.giftpackitemsRepo;
import com.swcat.giftapp.Models.giftpackitemsModel;
import com.swcat.giftapp.Models.giftpack.giftpackNewModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class GiftpackItemsService {
    @Autowired
    private giftpackitemsRepo giftpackitemsRepo;

    public void createGiftpackItems(giftpackitemsModel giftpackitemsModel){
        giftpackitemsModel.getItemid().stream().forEach(id -> {
            giftpackitemsRepo.save(convertModelToEntity(giftpackitemsModel.getGiftpackid(), id));
        });
    }

    public void updateGifpackItems(giftpackitems giftpackItem){
        giftpackitemsRepo.save(giftpackItem);
    }

    public List<giftpackitems> getAllGiftpackItems(){
        return giftpackitemsRepo.findAll();
    }

    public giftpackitemsModel getGiftpackItemsById(int giftpackId){
        List<giftpackitems> giftpackitems = giftpackitemsRepo.findByGiftpackId(giftpackId);
        List<Integer> listItemId = new ArrayList<>(); 
        giftpackitems.stream().forEach(giftpackItem -> {
            listItemId.add(giftpackItem.getItemId());
        });

        giftpackitemsModel giftpackitemsModel = new giftpackitemsModel(giftpackId, listItemId);
        return giftpackitemsModel;
    }

    public void deleteGiftpackItems(int id){
        giftpackitemsRepo.deleteByGiftpackId(id);
    }


    public giftpackitems convertModelToEntity(int giftpackid, int itemid){
        giftpackitems giftpackitems = new giftpackitems(null, giftpackid, itemid);
        return giftpackitems;
    }

    

}
