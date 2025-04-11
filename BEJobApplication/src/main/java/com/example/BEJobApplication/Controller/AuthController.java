package com.example.bejobapplication.Controller;

import com.example.bejobapplication.DTO.LoginRequest;
import com.example.bejobapplication.DTO.LoginResponse;
import com.example.bejobapplication.DTO.GoogleLoginRequest;
import com.example.bejobapplication.Entity.User;
import com.example.bejobapplication.Reponsitory.UserReponsitory;
import com.example.bejobapplication.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import com.example.bejobapplication.Service.GoogleAuthService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private GoogleAuthService googleAuthService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserReponsitory userRepository;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        try {
            // Tìm user từ DB
            User user = userRepository.findByEmailOrUsername(request.getIdentifier(), request.getIdentifier())
                    .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
            // Tự xác thực (so sánh password bằng tay)
            if (!user.getPassword().equals(request.getPassword())) {
                return new LoginResponse("Invalid credentials", null, null);
            }
            // Tạo JWT token
            String token = jwtService.generateToken(user.getUsername());
            return new LoginResponse("Login thành công", token, user);
        } catch (Exception e) {
            return new LoginResponse("Invalid credentials", null, null);
        }
    }

    @PostMapping("/google")
    public ResponseEntity<LoginResponse> loginWithGoogle(@RequestBody GoogleLoginRequest request) {
        GoogleIdToken.Payload payload = googleAuthService.verifyGoogleToken(request.getIdToken());
        // Bước 1: Xác minh id_token từ client gửi lên

        if (payload == null) {
            return ResponseEntity.badRequest().body(new LoginResponse("Invalid Google token", null, null));
        }

        String email = payload.getEmail();
        String name = (String) payload.get("name");

        // Bước 2: Kiểm tra user đã tồn tại trong DB chưa
        Optional<User> useroptional = userRepository.findByEmail(email);

        if (useroptional.isEmpty()) {
            return ResponseEntity.ok(new LoginResponse("Người dùng không tồn tại", null, null));
        }
        // Bước 3: Tạo JWT token và trả về
        User user = useroptional.get();
        String token = jwtService.generateToken(user.getUsername());
        return ResponseEntity.ok(new LoginResponse("Login with Google thành công", token, user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        // Kiểm tra email đã tồn tại chưa
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email đã tồn tại");
        }

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Đăng ký thành công");
    }

}
