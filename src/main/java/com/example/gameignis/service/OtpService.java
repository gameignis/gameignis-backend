package com.example.gameignis.service;

import com.example.gameignis.service.impl.OtpServiceImpl;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class OtpService implements OtpServiceImpl {

    @Autowired
    private JavaMailSender mailSender;

    private SecureRandom secureRandom = new SecureRandom();

    private final Map<String, String> otpStorage = new HashMap<>();
    private final Map<String, Long> otpExpiration = new HashMap<>();
    private final Map<String, Integer> otpAttempts = new HashMap<>();
    private final Map<String, Long> otpFirstAttemptTime = new HashMap<>();

    private static final long OTP_VALIDITY = 5 * 60 * 1000;
    private static final int MAX_ATTEMPTS = 5;
    private static final long ATTEMPT_WINDOW = 15 * 60 * 1000;

    @Override
    public String generateOtp() {
        int otp = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(otp);
    }

    @Override
    public void sendOtpEmail(String recipientEmail, String otp) {
        if (otpAttempts.containsKey(recipientEmail)
                && otpAttempts.get(recipientEmail) >= MAX_ATTEMPTS
                && System.currentTimeMillis() - otpFirstAttemptTime.get(recipientEmail) <= ATTEMPT_WINDOW) {
            throw new RuntimeException("OTP attempt exceeded!");
        }

        otpStorage.put(recipientEmail, otp);
        otpExpiration.put(recipientEmail, System.currentTimeMillis() + OTP_VALIDITY);

        if (!otpAttempts.containsKey(recipientEmail) || System.currentTimeMillis() - otpFirstAttemptTime.get(recipientEmail) > ATTEMPT_WINDOW) {
            otpAttempts.put(recipientEmail, 1);
            otpFirstAttemptTime.put(recipientEmail, System.currentTimeMillis());
        } else {
            otpAttempts.put(recipientEmail, otpAttempts.get(recipientEmail) + 1);
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(recipientEmail);
            helper.setSubject("Your OTP Code!");
            helper.setText("Your OTP code is: " + otp, true);

            mailSender.send(message);
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        String storedOtp = otpStorage.get(email);
        Long expirationTime = otpExpiration.get(email);

        if (storedOtp != null && expirationTime != null) {
            if (System.currentTimeMillis() <= expirationTime && storedOtp.equals(otp)) {
                otpStorage.remove(email);
                otpExpiration.remove(email);
                otpAttempts.remove(email);
                otpFirstAttemptTime.remove(email);
                return true;
            }
        }
        return false;
    }
}
