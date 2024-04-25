package com.games.security.service;

import com.games.security.models.AuthResponse;
import com.games.security.models.AuthenticationRequest;
import com.games.security.models.RegisterRequest;

public interface AuthService {
    AuthResponse register (RegisterRequest request);
    AuthResponse login (AuthenticationRequest request);
}
