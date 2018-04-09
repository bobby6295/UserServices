package com.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Market {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean isActive = true;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "exchange_id")
    private ExchangeCountry exchangeCountry;


    private Long ExchangeCountryId;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public boolean isActive() {
        return isActive;
    }


    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }


    public Market() {

    }


    public ExchangeCountry getExchangeCountry() {
        return exchangeCountry;
    }


    public void setExchangeCountry(ExchangeCountry exchangeCountry) {
        this.exchangeCountry = exchangeCountry;
    }


    public Long getExchangeCountryId() {
        return ExchangeCountryId;
    }


    public void setExchangeCountryId(Long exchangeCountryId) {
        this.ExchangeCountryId = exchangeCountryId;
    }


}
