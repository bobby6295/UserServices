package com.demo.otp;

import java.util.Random;

public class DefaultOtpGenerator implements OtpGenerator {


    private final char chars[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9'};


    private Random random;
    private int length;


    DefaultOtpGenerator(Random random, int length) {

        this.random = random;
        this.length = length;


    }


    @Override
    public String generateToken() {


        StringBuilder token = new StringBuilder(length);
        for (int i = 0; i < length; i++) {


            token.append(chars[random.nextInt(chars.length)]);
        }


        return token.toString();
    }
}
