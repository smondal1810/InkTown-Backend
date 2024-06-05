package com.dev.inktown.service;


import com.dev.inktown.auth.JwtService;
import com.dev.inktown.constant.StringConstant;
import com.dev.inktown.entity.User;
import com.dev.inktown.model.AuthenticationResp;
import com.dev.inktown.model.Role;
import com.dev.inktown.model.SignInRequest;
import com.dev.inktown.model.SignupRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log
public class AuthenticationService implements StringConstant {
    private final UserService userService;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    public AuthenticationResp signUp(SignupRequest signupRequest) throws Exception {
        User user = User.builder()
                .userName(signupRequest.getUserName())
                .userPhoneNo(signupRequest.getUserPhone())
                .role(signupRequest.getRole())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
//                .password(signupRequest.getPassword())
                .build();

        if(signupRequest.getRole() == Role.ROLE_EMP && !signupRequest.getUserSecretKey().equals(USER_SECRET_KEY)){
            throw new Exception("user authentication key did not match");
        }


        User createdUser = this.userService.createUser(user);
        String jwtToken  = jwtService.generateToken(user);
        return AuthenticationResp.builder()
                .token(jwtToken)
                .user(createdUser)
                .build();

    }


    public AuthenticationResp signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getUserPhoneNo(),
                        signInRequest.getPassword()
                )
        );
        User user = this.userService.getUserByPhoneNo(signInRequest.getUserPhoneNo());
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResp.builder()
                .token(jwtToken)
                .user(user)
                .build();

    }
}
