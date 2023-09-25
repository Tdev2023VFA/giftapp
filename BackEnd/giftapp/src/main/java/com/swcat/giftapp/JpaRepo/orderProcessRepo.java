package com.swcat.giftapp.JpaRepo;

import java.util.List;
import java.util.Optional;

import com.swcat.giftapp.Entities.orderProcess;

import org.springframework.data.jpa.repository.JpaRepository;

public interface orderProcessRepo extends JpaRepository<orderProcess, Integer> {

     List<orderProcess> findByPdelivered(Integer pdelivered);
     List<orderProcess> findByPcanceled(Integer pcanceled);
    // List<orderProcess> findAllCancel(Integer pcanceled);
    // Optional<orderProcess> getOrderProcessById(Integer gorderid);
}
