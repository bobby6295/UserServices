package com.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.domain.ExchangeCountry;
import com.demo.form.AllExchangeCountry;
import com.demo.form.ExchangeCountryInfo;

import java.io.Serializable;
import java.util.List;

public interface ExchangeCountryRepository extends JpaRepository<ExchangeCountry, Serializable> {


    @Query(value = "select ex.exchange_code as exchangeCode, ex.id as id, ex.is_active as isActive, c.name as name from country c INNER JOIN exchange_country ex ON c.country_id= ex.country_id", nativeQuery = true)
    List<AllExchangeCountry> getExchangeCountries();

    @Query(value = "select c.icon as icon,  ex.id as id, c.iso_code_2 as iso, p.nicename as niceName, p.phonecode as phoneCode ,ex.privacy_url as privacyUrl, ex.terms_url as termsUrl from country as c INNER JOIN phone_code as p ON  c.iso_code_2= p.iso INNER JOIN exchange_country as ex ON c.country_id= ex.country_id where ex.is_active = true order by p.nicename desc", nativeQuery = true)
    List<ExchangeCountryInfo> getActiveExchangeCountries();

}
