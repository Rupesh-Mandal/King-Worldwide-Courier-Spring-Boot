package com.kingworldwidecourier.config.Controller;

import com.kingworldwidecourier.config.Model.JWTRequest;
import com.kingworldwidecourier.config.Model.JwtResponse;
import com.kingworldwidecourier.config.service.CustomUserDetailsService;
import com.kingworldwidecourier.config.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/token")
    public ResponseEntity<?> generatToken(@RequestBody JWTRequest jwtRequest) throws Exception {

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()));

        } catch (UsernameNotFoundException e) {
            throw new Exception("Bad Credentials");
        }catch (BadCredentialsException e){
            throw new Exception("Bad Credentials");

        }

        UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token=this.jwtUtil.generateToken(userDetails);

        JwtResponse jwtResponse=new JwtResponse(token);

        return ResponseEntity.ok(jwtResponse);

    }


}
