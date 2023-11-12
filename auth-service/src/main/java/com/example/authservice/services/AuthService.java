package com.example.authservice.services;

import com.example.authservice.dtos.AuthRequest;
import com.example.authservice.dtos.AuthResponse;
import com.example.authservice.dtos.UserVO;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;

    public AuthResponse register(AuthRequest request) {
        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        UserVO registeredUser = new UserVO(String.valueOf(new Date().getTime()), request.getPassword(), request.getName());
        String accessToken = jwtUtil.generate(registeredUser.getId(), registeredUser.getName());
        return new AuthResponse(accessToken);
    }

}