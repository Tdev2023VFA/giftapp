package com.swcat.giftapp.JpaRepo;

import com.swcat.giftapp.Entities.accountInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface accountInfoRepo extends JpaRepository<accountInfo, String> {
    
}
