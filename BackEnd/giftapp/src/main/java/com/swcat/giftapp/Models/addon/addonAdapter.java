package com.swcat.giftapp.Models.addon;

import com.swcat.giftapp.Entities.addon;

public class addonAdapter {
    public addon convertFromModelToEntity(addonNewModel annew) {
        addon tmpan = new addon();

        tmpan.setCid(0);
        tmpan.setAname(annew.getAname());
        tmpan.setAdescription(annew.getAdescription());
        tmpan.setPrice(annew.getPrice());

        return tmpan;
    }
}
