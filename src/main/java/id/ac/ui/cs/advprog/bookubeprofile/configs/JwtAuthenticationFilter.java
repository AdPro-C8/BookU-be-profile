package id.ac.ui.cs.advprog.bookubeprofile.configs;

import id.ac.ui.cs.advprog.bookubeprofile.models.User;
import id.ac.ui.cs.advprog.bookubeprofile.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String currentUserEndpoint;

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtService jwtService;
    private final RestClient restClient;

    JwtAuthenticationFilter(
            JwtService jwtService,
            HandlerExceptionResolver handlerExceptionResolver,
            RestClient restClient,
            @Value("${api.auth-host}") String authHost) {
        this.jwtService = jwtService;
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.restClient = restClient;
        this.currentUserEndpoint = String.format("%s/users/me", authHost);
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null) {
                User user = restClient.get()
                        .uri(currentUserEndpoint)
                        .header("Authorization", authHeader)
                        .retrieve()
                        .toEntity(User.class)
                        .getBody();

                if (user == null) {
                    filterChain.doFilter(request, response);
                    return;
                }

                if (jwtService.isTokenValid(jwt, user)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}