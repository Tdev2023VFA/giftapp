package com.swcat.giftapp.JpaRepo;

import com.swcat.giftapp.Entities.item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface itemRepo extends JpaRepository<item, Integer> {
    
}
