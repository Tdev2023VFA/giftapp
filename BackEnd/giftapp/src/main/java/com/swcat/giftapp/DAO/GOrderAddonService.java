package com.swcat.giftapp.DAO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.swcat.giftapp.Entities.addon;
import com.swcat.giftapp.Entities.gorderAddons;
import com.swcat.giftapp.JpaRepo.addonRepo;
import com.swcat.giftapp.JpaRepo.gorderAddonsRepo;
import com.swcat.giftapp.Models.addon.GOrderAddOnModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GOrderAddonService {
    @Autowired
    private gorderAddonsRepo gorderAddonsRepo;
    @Autowired
    private addonRepo addonRepo;
    
    public GOrderAddOnModel createGOrderAddOn(GOrderAddOnModel gOrderAddOnModel){
        gorderAddons gorderAddonsEntity = convertGOrderAddOnModelToGOrderAddOnEntity(gOrderAddOnModel);
        return convertGOrderAddOnEntityToGOrderAddOnModel(gorderAddonsRepo.save(gorderAddonsEntity));
    }

    public void updateGOrderAddOn(GOrderAddOnModel gOrderAddOnModel){
        gorderAddons gorderAddonsEntity = convertGOrderAddOnModelToGOrderAddOnEntity(gOrderAddOnModel);
        gorderAddonsRepo.save(gorderAddonsEntity);
    }


    public List<GOrderAddOnModel> getAllGOrderAddOn(){
        List<GOrderAddOnModel> gOrderAddOnModels = gorderAddonsRepo.findAll()
        .stream()
        .map(gOrderAddOnEntity -> {
        Optional<addon> addOnEntityOp = addonRepo.findById(gOrderAddOnEntity.getAddonId());
        addon addOnEntity = addOnEntityOp.get();
        GOrderAddOnModel gOrderAddOnModel = new GOrderAddOnModel(gOrderAddOnEntity.getCid(), 
        gOrderAddOnEntity.getGorderId(), 
        gOrderAddOnEntity.getAddonId(), 
        gOrderAddOnEntity.getContent(), 
        addOnEntity.getAname(), 
        addOnEntity.getAdescription(), 
        addOnEntity.getPrice());
        return gOrderAddOnModel;
        })
        .collect(Collectors.toList());

        return gOrderAddOnModels;
    }

    public List<GOrderAddOnModel> getGOrderAddOn(int gOrderId){
        List<gorderAddons> gOrderAddOnEntities = gorderAddonsRepo.findByGorderId(gOrderId);
        List<GOrderAddOnModel> gOrderAddOnModels = gOrderAddOnEntities.stream()
        .map(gOrderAddOnEntity -> {
        Optional<addon> addOnEntityOp = addonRepo.findById(gOrderAddOnEntity.getAddonId());
        addon addOnEntity = addOnEntityOp.get();
        GOrderAddOnModel gOrderAddOnModel = new GOrderAddOnModel(gOrderAddOnEntity.getCid(), 
        gOrderAddOnEntity.getGorderId(), 
        gOrderAddOnEntity.getAddonId(), 
        gOrderAddOnEntity.getContent(), 
        addOnEntity.getAname(), 
        addOnEntity.getAdescription(), 
        addOnEntity.getPrice());
        return gOrderAddOnModel;
        })
        .collect(Collectors.toList());
        return gOrderAddOnModels;
    }

    public boolean isGOrderAddOnExist(int orderId){
        return gorderAddonsRepo.existsByGorderId(orderId);
    }

    public void deleteGOrderAddOnExist(int cid){
        gorderAddonsRepo.deleteById(cid);
    }

    public gorderAddons convertGOrderAddOnModelToGOrderAddOnEntity(GOrderAddOnModel gOrderAddOnModel){
        gorderAddons gorderAddons = new gorderAddons(gOrderAddOnModel.getCid()
        , gOrderAddOnModel.getGorderId()
        , gOrderAddOnModel.getAddonId()
        , gOrderAddOnModel.getContent());
        // System.out.println(gOrderAddOnModel);
        // System.out.println(gorderAddons);
        return gorderAddons;
    }

    public GOrderAddOnModel convertGOrderAddOnEntityToGOrderAddOnModel(gorderAddons gorderAddons){
        GOrderAddOnModel gOrderAddOnModel = new GOrderAddOnModel(gorderAddons.getCid()
        , gorderAddons.getGorderId()
        , gorderAddons.getAddonId()
        , gorderAddons.getContent()
        , null, null, null);
        return gOrderAddOnModel;
    }
}
