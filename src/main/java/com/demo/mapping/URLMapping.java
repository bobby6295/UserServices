package com.demo.mapping;

public class URLMapping {

	public static final String ACCOUNTS_API_V1 = "/v1";

	
	// Market
	public static final String SAVE_MARKET = ACCOUNTS_API_V1 + "/add/market";
	public static final String GET_MARKET = ACCOUNTS_API_V1 + "/market";
	public static final String GET_MARKETS = ACCOUNTS_API_V1 + "/markets";
	public static final String GET_MARKETS_BY_EXCHANGE = ACCOUNTS_API_V1 + "exchange/markets";
	public static final String UPDATE_MARKET = ACCOUNTS_API_V1 + "/update/market";
	public static final String MARKET_STATUS = ACCOUNTS_API_V1 + "/market/status";

		
		
		// Exchange
	public static final String ADD_EXCHANGE = ACCOUNTS_API_V1 + "/add_exchange";
	public static final String GET_EXCHANGE_COUNTRIES = ACCOUNTS_API_V1 + "/all_exchange";
	public static final String GET_ACTIVE_EXCHANGES = ACCOUNTS_API_V1 + "/active_exchange";
	public static final String GET_EXCHANGE = ACCOUNTS_API_V1 + "/exchange";
	public static final String UPDATE_EXCHANGE = ACCOUNTS_API_V1 + "/update_exchange";
	public static final String EXCHANGE_STATUS = ACCOUNTS_API_V1 + "/exchange/status";


	//User
	public static final String Get_User= ACCOUNTS_API_V1 + "/get_users";
	public static final String CREATE_USER= ACCOUNTS_API_V1 + "/create_user";
	public static final String LOGIN= ACCOUNTS_API_V1 + "/login";
	public static final String LOGOUT= ACCOUNTS_API_V1 + "/logout";
	public static final String VERIFICATION = ACCOUNTS_API_V1+"/verification";
	public static final String REGISTRATION= ACCOUNTS_API_V1 + "/registration";
	public static final String FORGOTPASSWORD=ACCOUNTS_API_V1+"/forgotpassword";
	public static final String RESETPASSWORD=ACCOUNTS_API_V1+"/resetpassword";
	public static final String FORGOTPASSWORDMOBILE=ACCOUNTS_API_V1+"/forgotpasswordmobile";



	//Admin login
	public static final String ADMINLOGIN = ACCOUNTS_API_V1+"/admin_login";
	public static final String ADMINLOGOUT = ACCOUNTS_API_V1+"/admin_logout";

	public static final String domain="http://localhost:1118";


}
