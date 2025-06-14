package com.example.gameignis.service.impl;

public interface OtpServiceImpl {

    String generateOtp();

    void sendOtpEmail(String recipientEmail, String otp);

    boolean verifyOtp(String email, String otp);

}
