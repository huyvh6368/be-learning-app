package web.elearning.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.elearning.dto.JwtResponse;
import web.elearning.dto.LoginRequest;
import web.elearning.dto.RefreshTokenRequest;
import web.elearning.dto.request.AccountRequest;
import web.elearning.dto.response.AccountResponse;
import web.elearning.mapper.AccountMapper;
import web.elearning.model.Account;
import web.elearning.model.Learner;
import web.elearning.repository.AccountRepository;
import web.elearning.repository.LearnerRepository;
import web.elearning.security.CustomUserDetail;

import java.math.BigDecimal;


@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final LearnerRepository learnerRepository;

    // handler register
    public AccountResponse register(AccountRequest request) {
        Boolean checkMail = accountRepository.existsByEmail(request.getEmail());
        if (checkMail) {
            throw new RuntimeException("Email already exists");
        }
        String password = passwordEncoder.encode(request.getPassword());
        Account account = AccountMapper.addRequestToEntity(request, password);
        accountRepository.save(account);
        CustomUserDetail userDetails = new CustomUserDetail(account);
        var refreshToken = jwtService.generateRefreshToken(userDetails);
        // init learner
        Learner learner = new Learner();
        learner.setAccount(account);
        learner.setCode("code");
        learner.setRank(null);
        learner.setTotalScore(BigDecimal.ZERO);
        learner.setName(account.getName());
        learnerRepository.save(learner);
        account.setRefreshToken(refreshToken);
        account.setLearner(learner);
        return AccountMapper.entityToResponse(accountRepository.save(account));
    }

    // handler login
    public JwtResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var account = accountRepository.findByEmail(request.getEmail())
                .orElseThrow();
        CustomUserDetail userDetails = new CustomUserDetail(account);
        var accessToken = jwtService.generateAccessToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);
        account.setRefreshToken(refreshToken);
        accountRepository.save(account);
        return JwtResponse.builder()
                .accountEmail(account.getEmail())
                .accountId(account.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public JwtResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        // Validate refresh token
        if (!jwtService.isTokenValid(refreshToken)) {
            throw new RuntimeException("Refresh token is invalid");
        }

        // Lấy thông tin user từ refresh token
        String userEmail = jwtService.extractUsername(refreshToken);
        Account account = accountRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra refresh token có khớp với database
        if (!refreshToken.equals(account.getRefreshToken())) {
            throw new RuntimeException("Refresh token does not match");
        }
        CustomUserDetail userDetails = new CustomUserDetail(account);
        // Tạo token mới
        String newAccessToken = jwtService.generateAccessToken(userDetails);
        String newRefreshToken = jwtService.generateRefreshToken(userDetails);

        // Cập nhật refresh token mới vào database
        account.setRefreshToken(newRefreshToken);
        accountRepository.save(account);

        return JwtResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String editPassword(String email, String newPassword) {
        String password = passwordEncoder.encode(newPassword);
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        account.setPassword(password);
        accountRepository.save(account);
        return password;
    }
}