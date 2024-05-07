package com.games.security.service;

import com.games.exceptions.ExcpIncoFormatEmail;
import com.games.exceptions.ExcpPlayerExist;
import com.games.exceptions.ExcpPlayerNotCreated;
import com.games.model.entity.Player;
import com.games.model.entity.Role;
import com.games.model.repository.PlayerRepository;
import com.games.model.services.ManagerService;
import com.games.security.models.AuthResponse;
import com.games.security.models.AuthenticationRequest;
import com.games.security.models.RegisterRequest;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
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

    @Override
    public AuthResponse register(RegisterRequest request) {
        if(request.getPassword() != null && request.getEmail() != null && request.getNickname() != null){
            if(!isValidEmail(request.getEmail())) throw new ExcpIncoFormatEmail();
            if(playerRepository.existsPlayerByEmail(request.getEmail())) throw new ExcpPlayerExist();
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
        } else if (request.getPassword() == null && request.getEmail() == null && request.getNickname() == null) {
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

    private boolean isValidEmail(String email) {
        boolean valid = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException e) {
            valid = false;
        }
        return valid;
    }
}
