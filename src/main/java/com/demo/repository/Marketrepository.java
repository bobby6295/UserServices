package com.demo.repository;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.domain.Market;

public interface Marketrepository extends JpaRepository<Market, Serializable> {


    @Query("select m from Market m")
    Page<Market> getMarkets(Pageable pageable);


}
