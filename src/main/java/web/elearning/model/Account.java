package web.elearning.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, length = 100)
    private String email;

    private String password;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    private String roles;

    private String refreshToken;

    @OneToOne(mappedBy = "account")
    @JsonBackReference
    private Learner learner;
}

