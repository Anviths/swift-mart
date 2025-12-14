package com.ecom.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AppUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header=request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer ")){
           filterChain.doFilter(request,response);
            return;
        }

        String token =header.substring(7);
        if(!jwtService.validateToken(token)){
            filterChain.doFilter(request,response);
            return;
        }

        String username= jwtService.extractUsername(token);
        var authorities=jwtService.extractRoles(token)
                .stream()
                .map(r->new SimpleGrantedAuthority("ROLE "+r))
                .collect(Collectors.toSet());

        UsernamePasswordAuthenticationToken auth=
                new UsernamePasswordAuthenticationToken(username,null,authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request,response);
    }
}
