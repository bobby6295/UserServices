package com.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "exchangeCountry")
public class ExchangeCountry {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Integer countryid;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "exchangeCountry")
    @JsonManagedReference
    private List<Market> marketList = new ArrayList<Market>();


    private String privacyUrl;

    private String termsUrl;

    private String exchangeCode;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCountryid() {
        return countryid;
    }


    public void setCountryid(Integer countryid) {
        this.countryid = countryid;
    }


    public List<Market> getMarketList() {
        return marketList;
    }


    public void setMarketList(List<Market> marketList) {
        this.marketList = marketList;
    }


    public String getPrivacyUrl() {
        return privacyUrl;
    }


    public void setPrivacyUrl(String privacyUrl) {
        this.privacyUrl = privacyUrl;
    }


    public String getTermsUrl() {
        return termsUrl;
    }


    public void setTermsUrl(String termsUrl) {
        this.termsUrl = termsUrl;
    }


    public String getExchangeCode() {
        return exchangeCode;
    }


    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }


    public ExchangeCountry() {

    }


}
