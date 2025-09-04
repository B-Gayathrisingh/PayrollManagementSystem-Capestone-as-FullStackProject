package com.example.AdminService.AdminService.Jwt;

import com.example.AdminService.AdminService.service.CustomerDetailsService;
import com.example.AdminService.AdminService.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {


    @Value("${jwt.secret}")
    private String secretKey;
    private final JwtService jwtService;
    private final CustomerDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            String token = authHeader.substring(7); // remove "Bearer "
//
//            try {
//                byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//                SecretKey key = Keys.hmacShaKeyFor(keyBytes);
//
//                Claims claims = Jwts.parserBuilder()
//                        .setSigningKey(key)
//                        .build()
//                        .parseClaimsJws(token)
//                        .getBody();
//
//                String username = claims.getSubject();
//                List<String> roles = claims.get("roles", List.class);
//
//                List<SimpleGrantedAuthority> authorities = roles.stream()
//                        .map(SimpleGrantedAuthority::new)
//                        .toList();
//
//                UsernamePasswordAuthenticationToken authentication =
//                        new UsernamePasswordAuthenticationToken(username, null, authorities);
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            } catch (Exception e) {
//                throw new RuntimeException("Invalid JWT", e);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}

            try {
                Claims claims = jwtService.parse(token); // parse JWT
                String username = claims.getSubject();

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    var userDetails = userDetailsService.loadUserByUsername(username);

                    var auth = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getWriter(),
                        Map.of("error", "Invalid or expired token"));
                return; // stop further processing
            }
        }

        filterChain.doFilter(request, response);
    }
}
//@Component
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//    private final JwtService jwtService;
//
//    public JwtAuthFilter(JwtService jwtService) {
//        this.jwtService = jwtService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//
//        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (header != null && header.startsWith("Bearer ")) {
//            String token = header.substring(7);
//
//            try {
//                Claims claims = jwtService.parse(token);
//                String username = claims.getSubject();
//                List<String> roles = claims.get("roles", List.class);
//
//                List<SimpleGrantedAuthority> authorities = roles.stream()
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
//
//                UsernamePasswordAuthenticationToken auth =
//                        new UsernamePasswordAuthenticationToken(username, null, authorities);
//
//                SecurityContextHolder.getContext().setAuthentication(auth);
//            } catch (Exception e) {
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.getWriter().write("Invalid JWT");
//                return;
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//}