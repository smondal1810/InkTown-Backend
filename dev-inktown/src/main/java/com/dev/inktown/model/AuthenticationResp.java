package com.dev.inktown.model;

import com.dev.inktown.entity.User;
import lombok.*;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResp {
    String token;
    User user;

}
