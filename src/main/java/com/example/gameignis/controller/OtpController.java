package com.example.gameignis.controller;

import com.example.gameignis.dto.OtpDto;
import com.example.gameignis.service.OtpService;
import com.example.gameignis.service.impl.OtpServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/otp")
public class OtpController {

    private final OtpServiceImpl otpService;

    public OtpController(OtpService otpService) {
        this.otpService = (OtpServiceImpl) otpService;
    }

    @PostMapping("/sendOtp")
    public String sendOtp(@RequestBody OtpDto request) {
        try {
            String otp = otpService.generateOtp();
            otpService.sendOtpEmail(request.getEmail(), otp);
            return "OTP has been sent to your email!";
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestBody OtpDto request) {
        boolean isValid = otpService.verifyOtp(request.getEmail(), request.getOtp());
        if (isValid) {
            return "OTP verified successfully!";
        } else {
            return "Invalid or expired OTP!";
        }
    }
}

