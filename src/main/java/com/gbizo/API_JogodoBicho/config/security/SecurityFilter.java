package com.gbizo.API_JogodoBicho.config.security;

import com.gbizo.API_JogodoBicho.repository.contraventorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;

    @Autowired
    contraventorRepository contraventorRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = this.recoverToken(request);
        if (token != null) {
            String login = tokenService.validateToken(token); // Verifica o token e recupera o login
            UserDetails contraventor = contraventorRepository.findByLogin(login);

            if (contraventor != null) { // Certifica-se de que o usuário foi encontrado
                var auth = new UsernamePasswordAuthenticationToken(contraventor, null, contraventor.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response); // Continua o processamento da requisição
    }

    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null; // Caso o token não seja passado corretamente
        }
        return authHeader.replace("Bearer ", ""); // Remove o prefixo "Bearer" do token
    }
}
