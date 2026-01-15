package lk.acpt.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    private String username;
    private String password;
    private String jwtToken;

    public LoginDto(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public LoginDto(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}

