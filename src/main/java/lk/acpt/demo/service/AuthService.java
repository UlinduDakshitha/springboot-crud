package lk.acpt.demo.service;

import lk.acpt.demo.dto.LoginDto;
import lk.acpt.demo.dto.UserDto;

public interface AuthService {
    String register(UserDto userDto);
    LoginDto login(LoginDto loginDto);
}

