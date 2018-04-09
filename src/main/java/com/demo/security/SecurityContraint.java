package com.demo.security;

import com.demo.mapping.URLMapping;

public class SecurityContraint {


    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 300000; // 5 min
    public static final String TOKEN_PREFIX = "";
    public static final String HEADER_STRING = "Authorization";
    public static final String REGISTER_URL = URLMapping.REGISTRATION;
    public static final String LOGIN_URL = URLMapping.LOGIN;
    public static final String VERIFICATION_URL = URLMapping.VERIFICATION;
    public static final String LOGOUT_URL = URLMapping.LOGOUT;


}
