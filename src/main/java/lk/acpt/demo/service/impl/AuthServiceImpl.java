package lk.acpt.demo.service.impl;
import lk.acpt.demo.dto.LoginDto;
import lk.acpt.demo.dto.UserDto;
import lk.acpt.demo.entity.User;
import lk.acpt.demo.repository.UserRepo;
import lk.acpt.demo.security.JwtUtil;
import lk.acpt.demo.service.AuthService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl  implements AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;


    @Override
    public String register(UserDto userDto) {
        String encodedPassword=passwordEncoder.encode(userDto.getPassword());
        userRepo.save(new User(userDto.getUsername(),encodedPassword,userDto.getRole()));
        return "User registered successfully";
    }
    @Override
    public LoginDto login(LoginDto loginDto) {
        //1.userName and password
        authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));

        //2.if correct,token create
        String token=jwtUtil.generateToken(loginDto.getUsername());

        //3.return
        return new LoginDto(token);
    }


}


