package web.elearning.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import web.elearning.model.Account;
import web.elearning.repository.AccountRepository;

@Configuration
@RequiredArgsConstructor
public class StartAppConfig {
    private final AccountRepository accountRepository;

    @Bean
    ApplicationRunner initApplicationRunner() {
        return args -> {
            boolean existsAdmin = accountRepository.existsByRoles("ADMIN");

            if (!existsAdmin) {
                Account admin = new Account();
                admin.setName("admin");
                admin.setEmail("huyvh6368@gmail.com");
                admin.setPassword(new BCryptPasswordEncoder().encode("admin123")); // mã hóa password
                admin.setRoles("ADMIN");
                accountRepository.save(admin);
                System.out.println("Đã tạo tài khoản admin mặc định.");
            } else {
                System.out.println("Tài khoản admin đã tồn tại.");
            }
        };
    }
}
