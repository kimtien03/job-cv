package com.example.BEJobApplication.Controller;

import com.example.BEJobApplication.DTO.LoginRequest;
import com.example.BEJobApplication.DTO.LoginResponse;
import com.example.BEJobApplication.DTO.UserCreateDTO;
import com.example.BEJobApplication.DTO.PasswordResetDTO;

import com.example.BEJobApplication.DTO.GoogleLoginRequest;
import com.example.BEJobApplication.Entity.User;
import com.example.BEJobApplication.Service.UserService;
import com.example.BEJobApplication.Service.AuthService;
import com.example.BEJobApplication.Utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import com.example.BEJobApplication.Service.GoogleAuthService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private GoogleAuthService googleAuthService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Thêm dòng này

    @PostMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestBody String email) {
        System.out.println("Email nhận được: " + email); // Log email nhận được
        email = email.replace("\"", "").trim();
        boolean exists = userService.emailExists(email);
        System.out.println("Email tồn tại: " + exists);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        // Sử dụng Optional để kiểm tra người dùng có tồn tại không
        Optional<User> userOptional = userService.findByEmailOrUsername(request.getIdentifier(), request.getIdentifier());
        if (!userOptional.isPresent()) {
            // Trả về lỗi nếu người dùng không tồn tại
            return new LoginResponse("Người dùng không tồn tại", null, null);
        }
        User user = userOptional.get();
        // Kiểm tra mật khẩu
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new LoginResponse("Sai mật khẩu", null, null);
        }

        // Tạo JWT token
        String token = jwtUtils.generateToken(user);
        return new LoginResponse("Login thành công", token, user);
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
        Optional<User> useroptional = userService.findByEmail(email);

        if (useroptional.isEmpty()) {
            return ResponseEntity.ok(new LoginResponse("Người dùng không tồn tại", null, null));
        }
        // Bước 3: Tạo JWT token và trả về
        User user = useroptional.get();
        String token = jwtUtils.generateToken(user);
        return ResponseEntity.ok(new LoginResponse("Login with Google thành công", token, user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCreateDTO user) {
        try {
            if (!authService.isValidFormat(user.getEmail()) || !authService.isEmailDeliverable(user.getEmail())) {
                return ResponseEntity.badRequest().body("Email không hợp lệ hoặc không tồn tại.");
            }
            // Kiểm tra email đã tồn tại chưa
            if (userService.findByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Email đã tồn tại");
            }

            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Đăng ký thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("error", e.getMessage(), "status", 400));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(
                    Map.of("error", "Lỗi máy chủ", "message", e.getMessage()));
        }

    }

    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetDTO request) {
        try {
            Optional<User> optionalUser = userService.findByEmail(request.getEmail());

            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Email không tồn tại trong hệ thống.");
            }

            userService.updatePassword(request.getEmail(), request.getNewPassword());

            return ResponseEntity.ok("Đặt lại mật khẩu thành công.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Lỗi máy chủ", "message", e.getMessage()));
        }
    }

}
