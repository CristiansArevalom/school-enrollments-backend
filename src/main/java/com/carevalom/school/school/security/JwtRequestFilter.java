package com.carevalom.school.school.security;

import java.io.IOException;

import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

//Para poder ejecutar los test con Spring Security
//Aqui se indica que solo se usara este componente cuando el perfil sea dev o uat
@Profile(value = {"dev", "uat"})
//Clase S5

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter{

    private final JwtUserDetailsService jwtUserDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String tokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;


        if(tokenHeader!=null){
            if(tokenHeader.startsWith("Bearer ") || tokenHeader.startsWith("bearer ")){
                //jwtToken = tokenHeader.split(" ")[1];
                final int TOKEN_POSITION = 7;
                jwtToken = tokenHeader.substring(TOKEN_POSITION);

                try{
                    username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                }catch(Exception ex){
                    request.setAttribute("exception", ex.getMessage());
                }
            }
        }

        if(username !=null){
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
            if(jwtTokenUtil.validateToken(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken userPassAuthToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                userPassAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(userPassAuthToken);
            }
        }
        filterChain.doFilter(request, response);

    }

    
    
}
