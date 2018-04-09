package com.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.constant.Message;
import com.demo.repository.ExchangeCountryRepository;
import com.demo.domain.ExchangeCountry;

@Service
public class ExchangeCountryService {

	
	
	@Autowired
	public ExchangeCountryRepository exchangecountryrespository;	
	
	

	
	Logger LOGGER = LoggerFactory.getLogger(MarketService.class);

	
	
    public Map<String, Object> saveExchange(ExchangeCountry exchangeCountry) {
        Map<String, Object> result = new HashMap<String, Object>();
        Boolean isSuccess = false       
        ;
        try{ 
        	ExchangeCountry exc=new ExchangeCountry();
        	exc.setCountryid(exchangeCountry.getCountryid());
        	exc.setExchangeCode(exchangeCountry.getExchangeCode());
        	exc.setPrivacyUrl(exchangeCountry.getPrivacyUrl());
        	exc.setTermsUrl(exchangeCountry.getTermsUrl());
              ExchangeCountry savedExchangeCountry = exchangecountryrespository.save(exc);
                LOGGER.info("Exchange country saved successfully {}", savedExchangeCountry);
                isSuccess = true;
			    result.put("exchange", exc);
                result.put("isSuccess", isSuccess);
              
                return result;
      
        }catch(NumberFormatException e)
        {
            LOGGER.warn(e.getMessage());
            result.put("isSuccess", isSuccess);
            result.put("message", "numberFormateException");
             }
        catch (Exception e) {
			// TODO: handle exception
    		
    		result.put("error", e.getMessage());
			LOGGER.warn(e.getMessage());
			 result.put("message", "internel server error");
		}

        return result;
    }
	
    
    
    
    
    public  Map<String, Object>  getExchange(String exchangeId)
    {
    	
    	Map<String,Object> result=new HashMap<>();
    	Long id=Long.parseLong(exchangeId);
    	ExchangeCountry exchangeCountry=exchangecountryrespository.findOne(id);
    	boolean isSuccess=false;
    	
    	try {
			if (exchangeCountry!=null) {
				isSuccess=true;
	    		result.put("issuccess", isSuccess);
	            
	            result.put("message","Sucesss");
	            result.put("exchange", exchangeCountry);
	            return result;			
			} else {
				
				
				result.put("issuccess", false);
    			result.put("message", "Not exist");

    		    return result;	
			}		
		} catch (NullPointerException e) {

			result.put("error", e.getMessage());
			LOGGER.warn(e.getMessage());
			 result.put("message", "internel server error");
		}
    	catch (NumberFormatException e) {

			
			result.put("error", e.getMessage());
			LOGGER.warn(e.getMessage());
			 result.put("message", "NumberFormatException at getexchange");
		}
    	catch (Exception e) {

    		result.put("error", e.getMessage());
			LOGGER.warn(e.getMessage());
			 result.put("message", "internel server error");
		}
    	
    	 return result;	
    	
    }
    
    
    
    
    public Map<String, Object> updateExchange(String exchangeId,ExchangeCountry exchangeCountry) {
        Map<String, Object> result = new HashMap<String, Object>();
        Boolean isSuccess = false;
       
		try {
			ExchangeCountry ecountry = exchangecountryrespository.findOne(Long.parseLong(exchangeId));
			 ExchangeCountry savedExchangeCountry=null;
			if (ecountry != null) {

				ecountry.setCountryid(exchangeCountry.getCountryid());
				ecountry.setExchangeCode(exchangeCountry.getExchangeCode());
				ecountry.setPrivacyUrl(exchangeCountry.getPrivacyUrl());
				ecountry.setTermsUrl(exchangeCountry.getTermsUrl());
				   savedExchangeCountry =exchangecountryrespository.save(ecountry);
				LOGGER.info("Exchange updated saved successfully ");
				isSuccess = true;
				result.put("exchangeCountry", savedExchangeCountry);
				result.put("isSuccess", isSuccess);
				result.put("message", Message.SUCCESS);
			
				return result;

			} else {
				result.put("exchangeCountry", savedExchangeCountry);
				result.put("isSuccess", isSuccess);
				result.put("message", Message.NOT_EXIST);
			
				
				
			}

		}catch (NullPointerException e) {
			// TODO: handle exception
			
			result.put("error", e.getMessage());
			LOGGER.warn(e.getMessage());
			 result.put("message", "NullPointerException at Update Exchange");
		}
    	catch (NumberFormatException e) {
			// TODO: handle exception
			
			result.put("error", e.getMessage());
			LOGGER.warn(e.getMessage());
			 result.put("message", "NumberFormatException at updateexchange");
		} 
		catch (Exception e) {
			LOGGER.warn(e.getMessage());
		     result.put("isSuccess", isSuccess);
		     result.put("message", Message.ERROR);
		}

        return result;
    }
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	
}
