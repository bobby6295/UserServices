package com.demo.repository;

import com.demo.domain.User;
import com.demo.domain.VerificationToken;
import org.hibernate.hql.internal.QueryExecutionRequestException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Serializable> {


    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);



    @Modifying
    @Query("delete from VerificationToken t where t.expiryDate <= ?1")
     void deleteAllExpiredSince(Date now) ;

}
