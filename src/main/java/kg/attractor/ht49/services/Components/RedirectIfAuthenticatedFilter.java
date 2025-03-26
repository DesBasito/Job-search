package kg.attractor.ht49.services.Components;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class RedirectIfAuthenticatedFilter extends OncePerRequestFilter {
    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (request.getRequestURI().equals("/login") && auth != null && auth.isAuthenticated()
            && !(auth instanceof AnonymousAuthenticationToken)) {
            response.sendRedirect("/profile");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
