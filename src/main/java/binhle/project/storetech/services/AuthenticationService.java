package binhle.project.storetech.services;

import binhle.project.storetech.DTO.request.AuthenticationRequest;
import binhle.project.storetech.repository.UserRepository;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class AuthenticationService {

    @Autowired
    private UserRepository repo;

    public boolean authenticated(AuthenticationRequest request){
        var user = repo.findByUsername(request.getUsername()).orElseThrow(()-> new RuntimeException("User not found"));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new RuntimeException("password failed!!!!");
        return passwordEncoder.matches(request.getPassword(), user.getPassword());
    }
}
