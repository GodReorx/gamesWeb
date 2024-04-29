package com.games.security.service;

import com.games.exceptions.ExcpPlayerNotCreated;
import com.games.model.entity.Player;
import com.games.model.entity.Role;
import com.games.model.repository.PlayerRepository;
import com.games.model.services.ManagerService;
import com.games.security.models.AuthResponse;
import com.games.security.models.AuthenticationRequest;
import com.games.security.models.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ManagerService managerService;

    //ToDo: en register, controlar si viene en blanco los datos, para poder crear el anonimo. Si no vienen en blanco, chequear si el email existe y no dejar crear user en ese caso.
    @Override
    public AuthResponse register(RegisterRequest request) {
        boolean playerExist = playerRepository.existsPlayerByEmail(request.getEmail());
        if(request.getPassword() != null && request.getEmail() != null && request.getNickname() != null && !playerExist){
            Player player = Player.builder()
                    .nickname(request.getNickname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .build();
            playerRepository.save(player);
            var jwtToken = jwtService.generateToken(player);
            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
        } else if (request.getPassword() == null && request.getEmail() == null) {
            Player player = Player.builder()
                    .nickname(request.getNickname())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .role(Role.USER)
                    .build();
            managerService.createPlayer(player);
            var jwtToken = jwtService.generateToken(player);
            return AuthResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new ExcpPlayerNotCreated();
        }
    }


    @Override
    public AuthResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Player player = playerRepository.findUserByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(player);
        return AuthResponse.builder().token(jwtToken).build();
    }
}
