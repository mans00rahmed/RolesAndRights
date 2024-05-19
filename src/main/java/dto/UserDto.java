package dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private boolean enabled;
    private String firstName;
    private boolean isUsing2fa;
    private String lastName;
    private String password;
    private String secret;
}