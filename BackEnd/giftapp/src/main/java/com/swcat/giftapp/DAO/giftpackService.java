package com.swcat.giftapp.DAO;

import java.util.List;

import com.swcat.giftapp.Entities.giftpack;
import com.swcat.giftapp.JpaRepo.giftpackRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class giftpackService {
    @Autowired
    private giftpackRepo repo;

    public List<giftpack> getall() {
        return repo.findAll();
    }

    public giftpack getone(Integer id) {
        return repo.findById(id).get();
    }

    public boolean create(giftpack gp) {
        gp.setCid(0);

        boolean kk = false;

        try {
            repo.save(gp);

            kk = true;
        } catch (Exception e) {
            //TODO: handle exception
        }

        return kk;
    }

    public boolean save(giftpack gp) {
        boolean kk = false;

        giftpack tmpgp = null;

        try {
            tmpgp = getone(gp.getCid());

            if(tmpgp != null) {
                repo.save(gp);

                kk = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        return kk;
    }

    public long getnogptypes() {
        long tmp = 0;

        try {
            tmp = repo.count();
        } catch (Exception e) {
            //TODO: handle exception
        }

        return tmp;
    }
}
