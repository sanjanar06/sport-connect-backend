package com.backend.sportC.communication.Response;

import com.backend.sportC.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponse {

    private String accessToken;
    private String refreshToken;
    private Role userRole;
}
