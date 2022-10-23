package com.yuriikoss.codingtask03sevenhillsgrouptech.repository;

import com.yuriikoss.codingtask03sevenhillsgrouptech.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "select BONUS_AMOUNT from USERT where USER_ID = :userId", nativeQuery = true)
    Long getBonusByUserId(Long userId);
}
