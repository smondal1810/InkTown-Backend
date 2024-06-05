package com.dev.inktown.model;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
    String userPhoneNo;
    String password;
}
