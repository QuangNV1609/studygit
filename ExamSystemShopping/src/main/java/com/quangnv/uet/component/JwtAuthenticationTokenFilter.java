package com.quangnv.uet.component;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import com.quangnv.uet.service.JwtTokenService;
import com.quangnv.uet.service.impl.UserDetailsServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtTokenService jwtTokenService;

	@Autowired
	private UserDetailsServiceImpl userDetailservice;

	@Transactional
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = jwtTokenService.getJwtFormRequest(request);
			if(jwt != null) {
				String username = jwtTokenService.getUsernameFormToken(jwt);

				UserDetails userDetails = userDetailservice.loadUserByUsername(username);
				if (userDetails != null) {
					// Nếu người dùng hợp lệ, set thông tin cho Seturity Context
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (Exception e) {
			log.error("failed on set user authentication", e.getMessage());
		}
		filterChain.doFilter(request, response);
	}

}
