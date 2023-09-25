package com.swcat.giftapp.Models.item;

import com.swcat.giftapp.Entities.item;

public class itemAdapter {
    public item convertModelToEntity(itemNewModel itemnmodel) {
        item itmp = new item();

        itmp.setCid(0);
        itmp.setItemName(itemnmodel.getItemName());
        itmp.setItemDescription(itemnmodel.getItemDescription());
        itmp.setThumbnailimg(itemnmodel.getThumbnailimg());
        itmp.setPrice(itemnmodel.getPrice());
        itmp.setActiveState(itemnmodel.getActiveState());

        return itmp;
    }
}
