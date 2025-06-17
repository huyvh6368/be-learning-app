package web.elearning.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.stereotype.Service;
import web.elearning.utils.TextGenerator;


@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final AccountService accountService;

    public void sendNewPasswordEmail(String toEmail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(toEmail);
        String newPassword = TextGenerator.genText("pw_");
        accountService.editPassword(toEmail, newPassword);
        helper.setSubject("🔐 Mật khẩu mới của bạn"); // tiêu đề
//        helper.setFrom("vuhoanghuy123321@gmail.com"); // email gưi
        // phần nội dung
        String htmlContent = """
                    <div style="font-family:Arial, sans-serif; padding:20px; background:#f1f1f1; border-radius:8px;">
                        <h2 style="color:#333;">Chào bạn,</h2>
                        <p style="font-size:15px; color:#555;">
                            Dưới đây là mật khẩu mới của bạn:
                        </p>
                        <div style="margin:20px 0; padding:15px; background:#fff3cd; border-left:5px solid #ffc107; font-size:18px;">
                            <strong>Mật khẩu:</strong> %s
                        </div>
                        <p style="font-size:13px; color:#888;">
                            Vui lòng đăng nhập và đổi lại mật khẩu nếu cần. Không chia sẻ mật khẩu với bất kỳ ai.
                        </p>
                        <p style="font-size:13px; color:#888;">Trân trọng,<br/>Hệ thống hỗ trợ</p>
                    </div>
                """.formatted(newPassword);

        helper.setText(htmlContent, true); // true = gửi HTML
        mailSender.send(message);
    }
}
