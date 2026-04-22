package com.example.server.dto.response;

import com.example.server.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token;
    private String id;
    private String name;
    private String email;
    private Role role;
}