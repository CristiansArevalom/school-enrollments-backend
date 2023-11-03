package com.carevalom.school.school.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carevalom.school.school.security.JwtRequest;
import com.carevalom.school.school.security.JwtResponse;
import com.carevalom.school.school.security.JwtTokenUtil;
import com.carevalom.school.school.security.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;


//@ActiveProfiles("dev") //validar no funciona y debo quemar el dato en el .properites con spring.profiles.active
@Profile(value = {"dev", "uat"})
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest req) throws Exception{
        authenticate(req.getUsername(), req.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch(DisabledException ex){
            throw new Exception("USER_DISABLED", ex);
        }catch(BadCredentialsException ex){
            throw new Exception("INVALID_CREDENTIALS", ex);
        }

    }
    
}
