package org.ticket.event_ticket_management.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.ticket.event_ticket_management.domain.User;
import org.ticket.event_ticket_management.repositories.UserRepository;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserProvisioningFilter extends OncePerRequestFilter {

    private final UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof Jwt jwt)  {
          UUID keycloakedId = UUID.fromString(jwt.getSubject());

          if(!userRepository.existsById(keycloakedId)) {
              User user = new User();
              user.setId(keycloakedId);
              user.setName(jwt.getClaimAsString("preferred_username"));
              user.setEmail(jwt.getClaimAsString("email"));

                //saving data
              userRepository.save(user);
          }
        }
        filterChain.doFilter(request, response);

    }
}
