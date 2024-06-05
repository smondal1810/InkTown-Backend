package com.dev.inktown.model;


import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    String userName;
    String userPhone;
    String password;
    Role role;
    String userSecretKey;
}
