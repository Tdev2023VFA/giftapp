package com.swcat.giftapp.JpaRepo;

import java.util.List;

import com.swcat.giftapp.Entities.gorder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface gorderRepo extends JpaRepository<gorder, Integer> {
    List<gorder> findByUname(String uname);
}
