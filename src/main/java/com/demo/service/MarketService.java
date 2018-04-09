package com.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.constant.Message;
import com.demo.repository.ExchangeCountryRepository;
import com.demo.repository.Marketrepository;
import com.demo.domain.ExchangeCountry;
import com.demo.domain.Market;


@Service
public class MarketService {


	@Autowired
	private Marketrepository marketRepository;
	
	
	@Autowired
	ExchangeCountryRepository exchangeCountryRepository;
	
	
	@Autowired
	MessageService messageService;
	
	public Map<String, Object> saveMarket(Market market)
	{
		boolean isSuccess=false;
		Map<String, Object> result=new HashMap<>();
		Market mk=new Market();
		
		
		try {
			
			ExchangeCountry exchangeCountry =exchangeCountryRepository.findOne(market.getExchangeCountryId());
			if (exchangeCountry==null){
				
				result.put("isSuccess",false);
				result.put("message", messageService.getMessage(Message.ERROR));
				return result;
				
			}
			else {
				
				
				mk.setExchangeCountryId(market.getExchangeCountryId());
				mk.setExchangeCountry(exchangeCountry);
				marketRepository.save(mk);
				isSuccess=true;
			result.put("isSuccess", isSuccess);
			result.put("message", Message.SUCCESS);

			}
			
			
			
			}
			
		catch (NullPointerException e) {
			result.put("message", Message.ERROR);
			result.put("isSuccess", isSuccess)
;		}
		catch (Exception e) {
			result.put("message", Message.ERROR);
			result.put("isSuccess", isSuccess);
			
		}
		
			
		
		return result;
		
		
	}
	
	
	
	public Map<String , Object> getmarket(String marketId)
	{
        Map<String, Object> result = new HashMap<String, Object>();
        Boolean isSuccess = false;
        Market market=marketRepository.findOne(Long.parseLong(marketId));

        try {
        	if (market==null) {
    			result.put("isSuccess", isSuccess);
    			result.put("message", Message.NOT_EXIST);

    		return result;	
    		}
    		isSuccess=true;
    		result.put("isSuccess", isSuccess);
            result.put("message",Message.SUCCESS);

            result.put("market", market);


		} catch (Exception e) {

			result.put("error", e.getMessage());

			 result.put("message", Message.ERROR);
		        
			
		}
		
        return result;	
	}

public List<Object> findbyExchangeId(Long exchangeCountryid)
{
	
	Long id=exchangeCountryid;

	List<Object> result=new ArrayList<>();
	
	try {
		if (id!=null) {
				List<Market> option=marketRepository.findAll();
			Iterator<Market> itr=option.iterator();
			
			while (itr.hasNext()) {
				result.add(itr.next());			
				
			}
			
			}
		else {

			return result;
			
		}
	} catch (Exception e) {


	    result.add(e.getMessage());

	}
	
	

	return result;

}





     public Map<String, Object> updateMarket(String marketid ,Market marketform)
{
	
Map<String, Object>  result=new HashMap<>();
Boolean isSuccess=false;
try {
	Market market =marketRepository.findOne(Long.parseLong(marketid)); 
	 if (market == null) {
         result.put("isSuccess", isSuccess);
         result.put("message", Message.NOT_EXIST);

         return result;
     }
	 
	 
	 ExchangeCountry exchangeCountry=exchangeCountryRepository.findOne(marketform.getExchangeCountryId());
	 
	market.setExchangeCountry(exchangeCountry);
	market.setExchangeCountryId(marketform.getExchangeCountryId());
	
	
	Market savedMarket = marketRepository.save(market);

    isSuccess = true;
    result.put("market", savedMarket);
    result.put("isSuccess", isSuccess);

    result.put("message", Message.SUCCESS);

	
	
} catch (Exception e) {

     result.put("isSuccess", isSuccess);
     result.put("message", Message.ERROR);
}

return result;

}














}