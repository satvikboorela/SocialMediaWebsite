package com.satvik.restful_web_services.security;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
public class jwtAuthenticationResource {
    private JwtEncoder jwtEncoder;
    public jwtAuthenticationResource(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }
    @PostMapping("/authenticate")
    public JwtResponse authenticate( Authentication authentication) {
        return new JwtResponse(createToken(authentication));
    }

    private String createToken(Authentication authentication) {
       var claims=  JwtClaimsSet.builder().issuer("self").issuedAt(Instant.now()).expiresAt(Instant.now().plusSeconds(1800)).subject(authentication.getName()).claim("scope", createScope(authentication)).build();
       JwtEncoderParameters parameters= JwtEncoderParameters.from(claims);
       return jwtEncoder.encode(parameters).getTokenValue();
    }

    private String createScope(Authentication authentication) {
       return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
    }
}
record JwtResponse(String token){}
