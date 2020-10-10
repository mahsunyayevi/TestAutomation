package entity.user;

import lombok.Data;

@Data
public class LoginResponse {
    private int code;
    private String type;
    private String message;
}
