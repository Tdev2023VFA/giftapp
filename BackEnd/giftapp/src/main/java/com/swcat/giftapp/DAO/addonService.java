package com.swcat.giftapp.DAO;

import java.util.List;

import com.swcat.giftapp.Entities.addon;
import com.swcat.giftapp.JpaRepo.addonRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class addonService {
    @Autowired
    private addonRepo repo;

    public List<addon> getall() {
        return repo.findAll();
    }

    public addon getone(Integer id) {
        return repo.findById(id).get();
    }

    public boolean create(addon an) {
        an.setCid(0);

        boolean kk = false;

        try {
            repo.save(an);

            kk = true;
        } catch (Exception e) {
            //TODO: handle exception
        }

        return kk;
    }

    public boolean save(addon an) {
        addon tmpan = null;

        boolean kk = false;

        try {
            tmpan = getone(an.getCid());

            if(tmpan != null) {

                repo.save(an);

                kk = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        return kk;
    }

    public long numberofaddontypes() {
        long tmp = 0;

        try {
            tmp = repo.count();
        } catch (Exception e) {
            //TODO: handle exception
        }

        return tmp;
    }
}
