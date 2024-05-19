package model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_account")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private boolean enabled;
    private String firstName;
    private boolean isUsing2fa;
    private String lastName;
    private String password;
    private String secret;

}
