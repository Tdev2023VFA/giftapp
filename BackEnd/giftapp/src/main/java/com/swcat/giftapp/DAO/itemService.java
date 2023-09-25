package com.swcat.giftapp.DAO;

import java.util.List;

import com.swcat.giftapp.Entities.item;
import com.swcat.giftapp.JpaRepo.itemRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class itemService {
    @Autowired
    private itemRepo repo;

    public item get(Integer id) {
        return repo.findById(id).get();
    }

    public List<item> getall() {
        return repo.findAll();
    }

    public boolean create(item it) {
        it.setCid(0);

        boolean kk = false;

        try {
            repo.save(it);

            kk = true;
        } catch (Exception e) {
            //TODO: handle exception
        }

        return kk;
    }

    public boolean save(item it) {
        item tmpi = null;

        boolean kk = false;

        try {
            tmpi = get(it.getCid());

            if(tmpi != null) {

                repo.save(it);

                kk = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        return kk;
    }

    public long getnoitemtype() {
        long noit = 0;

        try {
            noit = repo.count();
        } catch (Exception e) {
            //TODO: handle exception
        }

        return noit;
    }
}
