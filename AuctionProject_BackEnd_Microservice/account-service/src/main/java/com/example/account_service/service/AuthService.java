package com.example.account_service.service;

import com.example.account_service.base_exception.AppException;
import com.example.account_service.base_exception.ErrorCode;
import com.example.account_service.dto.request.authenticate.LoginRequest;
import com.example.account_service.entity.User;
import com.example.account_service.respository.UserRespository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
public class AuthService {
    @Autowired
    UserRespository respository;
    @NonFinal
    @Value("${signer.key}")
    protected String Signer_Key;

    public Boolean introspect(String token)
            throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(Signer_Key.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);
        return verified && expiration.after(new Date());
    }

    public String login(LoginRequest loginRequest) {
        var user = respository.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new AppException(ErrorCode.Not_Found_Email));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword()))
        {
            var token = generateToken(loginRequest.getEmail());
            return token;
        }
        else
            throw new AppException(ErrorCode.Login_Failed);
    }
    public String generateToken(String email) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("I'm a good person")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(10, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("authorities", "ROLE_USER")
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(Signer_Key.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
