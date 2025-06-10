package web.elearning.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import web.elearning.model.Account;
import web.elearning.repository.AccountRepository;
import web.elearning.service.JwtService;

@Service
@RequiredArgsConstructor
public class CustomLogoutService implements LogoutHandler {
    private final JwtService jwtService;
    private final AccountRepository accountRepository;

    @Override
    public void logout(
            jakarta.servlet.http.HttpServletRequest request,
            jakarta.servlet.http.HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        String jwt = authHeader.substring(7);
        String userEmail = jwtService.extractUsername(jwt);

//        if (userEmail != null) {
//            Account account = accountRepository.findByEmail(userEmail)
//                    .orElseThrow();
//            account.setRefreshToken(null);
//            accountRepository.save(account);
//        }
    }
}
