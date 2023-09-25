package com.swcat.giftapp.JpaRepo;

import com.swcat.giftapp.Entities.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface accountRepo extends JpaRepository<account, String> {
    
}
