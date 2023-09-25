package com.swcat.giftapp.JpaRepo;

import java.util.List;

import com.swcat.giftapp.Entities.giftpackitems;
import com.swcat.giftapp.Models.giftpackitemsModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface giftpackitemsRepo extends JpaRepository<giftpackitems, Integer> {
    List<giftpackitems> findByGiftpackId(Integer giftpackId);
    void deleteByGiftpackId(Integer giftpackId);
}
