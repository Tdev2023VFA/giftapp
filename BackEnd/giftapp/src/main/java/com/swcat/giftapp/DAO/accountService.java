package com.swcat.giftapp.DAO;

import com.swcat.giftapp.Entities.account;
import com.swcat.giftapp.JpaRepo.accountRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class accountService {
    @Autowired
    private accountRepo repo;

    public account get(String username) {
        return repo.findById(username).get();
    }

    public void save(account acc) {
        repo.save(acc);
    }

    public boolean create(account acc) {
        account temp_acc = null;

        try {
            temp_acc = get(acc.getUname());
        } catch (Exception e) {
            //TODO: handle exception
        }

        if (temp_acc != null) {
            return false;
        }

        repo.save(acc);
        return true;
    }
}
