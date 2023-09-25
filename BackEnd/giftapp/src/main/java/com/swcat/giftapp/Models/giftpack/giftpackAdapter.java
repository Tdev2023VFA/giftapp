package com.swcat.giftapp.Models.giftpack;

import com.swcat.giftapp.Entities.giftpack;

public class giftpackAdapter {
    public giftpack convertModelToEntity(giftpackNewModel gpnew) {
        giftpack tmpgp = new giftpack();

        tmpgp.setCid(0);
        tmpgp.setGname(gpnew.getGname());
        tmpgp.setGdescription(gpnew.getGdescription());
        tmpgp.setThumbnailimg(gpnew.getThumbnailimg());
        tmpgp.setPrice(gpnew.getPrice());
        tmpgp.setAverageStar(gpnew.getAverageStar());
        tmpgp.setActiveState(gpnew.getActiveState());

        return tmpgp;
    }
}
