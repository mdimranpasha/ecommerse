package com.inventory.demo.repository;

import com.inventory.demo.model.Hub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HubRepository extends JpaRepository<Hub, Long> {
//    List<Hub> findAllByhubId(Long id);
//
//    List<Hub> findAllByProductId(Long productId);
}
