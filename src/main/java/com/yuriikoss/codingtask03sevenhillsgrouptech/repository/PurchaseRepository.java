package com.yuriikoss.codingtask03sevenhillsgrouptech.repository;

import com.yuriikoss.codingtask03sevenhillsgrouptech.entity.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    List<Purchase> findAllByUser(Long userId);

}
