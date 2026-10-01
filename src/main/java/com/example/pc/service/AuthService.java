package com.example.pc.service;

import com.example.pc.config.JwtService;
import com.example.pc.dto.RegisterRequestDTO;
import com.example.pc.dto.RegisterResponseDTO;
import com.example.pc.exception.UserAlreadyExistsException;
import com.example.pc.repository.AuthRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.apache.catalina.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RegisterResponseDTO register(RegisterRequestDTO request){
        if(authRepository.existsByUsername(request.getUsername())
                || authRepository.existsByEmail(request.getEmail()))
        {
            throw new UserAlreadyExistsException("El usuario ya existe");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        try{
            authRepository.saveAndFlush(user);
        }catch(DataIntegrityViolationException ex){
            throw new UserAlreadyExistsException("El usuario ya existe");
        }
        return new RegisterResponseDTO(user.getId(),user.getUsername(),user.getEmail());

    }
    @Transactional
    public LoginResponseDTO login (LoginRequestDTO request){
        try{
            var.authentication = authentication.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails user = (UserDetails)authentication.getPrincipal();
            return new LoginResponseDTO(jwtService.generateToken(user),
            JwtService.expiresInSeconds());
        }catch(AuthenticationException ex){
            throw new InvalidCredetialException("Credenciales Incorrectas");
        }
    }

}
