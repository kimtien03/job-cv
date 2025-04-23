package org.example.Controllers.admin;

import org.example.Models.*;

import org.example.Services.JobApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller("LoginController")
@RequestMapping("")
public class LoginController {
    private final JobApiService jobService;

    @Autowired
    public LoginController(JobApiService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/login")
    public String home(Model model) {
        model.addAttribute("user", new User());
        return "Auth/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserCreate());
        return "Auth/register";
    }

    @GetMapping("/forget")
    public String forget(Model model) {
        model.addAttribute("user", new User());
        return "Auth/forget";
    }

    @GetMapping("/forget/otp")
    public String otp(Model model) {
        model.addAttribute("user", new User());
        return "Auth/otp";
    }
    @GetMapping("/forget/resetpass")
    public String resetPass(Model model) {
        model.addAttribute("user", new User());
        return "Auth/resetPass";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model) {
        LoginRequest loginRequest = new LoginRequest(user.getUsername(), user.getPassword());
        try {
            ResponseEntity<LoginResponse> response = jobService.login(loginRequest);
            if (response.getBody().getToken() != null && response.getBody().getUser() != null) {
                model.addAttribute("message", "Đăng nhập thành công!");
                if (response.getBody().getUser().getRole().equals("ADMIN")) {
                    return "redirect:admin/";
                } else {
                    return "redirect:/";
                }
            } else {
                model.addAttribute("loginError", response.getBody().getMessage());
                return "Auth/login";
            }

        } catch (Exception e) {
            model.addAttribute("loginError", "Lỗi kết nối máy chủ!");
            return "Auth/login";

        }
    }
    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserCreate user, Model model) {
        try {
            user.setRole("USER");
            ResponseEntity<?> response = jobService.register(user);
            // Kiểm tra xem response có thành công không
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                model.addAttribute("checkRegister", true);
                return "redirect:/login";
            } else {
                // Nếu không thành công, xử lý lỗi trả về
                if (response.getBody() != null) {
                    ErrorResponse errorResponse = (ErrorResponse) response.getBody();
                    model.addAttribute("registerError", errorResponse.getMessage());
                } else {
                    model.addAttribute("registerError", "Lỗi không xác định.");
                }
                return "Auth/register"; // Trả về trang đăng ký với lỗi
            }
        } catch (Exception e) {
            model.addAttribute("registerError", "Lỗi kết nối máy chủ!");
            return "Auth/register"; // Nếu có lỗi kết nối
        }
    }

    @PostMapping("/google")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> loginWithGoogle(@RequestBody Map<String, String> payload) {
        Map<String, Object> responseData = new HashMap<>();

        try {
            String idToken = payload.get("idToken");
            ResponseEntity<LoginResponse> response = jobService.loginWithGoogle(idToken);

            if (response.getBody().getToken() != null && response.getBody().getUser() != null) {
                responseData.put("LoginResponse", response);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.put("LoginResponse", response);
                return ResponseEntity.ok(responseData);
            }
        } catch (Exception e) {
            responseData.put("success", false);
            responseData.put("message", "Lỗi hệ thống khi đăng nhập bằng Google!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }
    @PutMapping("/reset-password")
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            ResponseEntity<?> response = jobService.resetPassword(request.email, request.newPassword);
            Map<String, Object> responseBody = new HashMap<>();
            if (response.getStatusCode().is2xxSuccessful()) {
                responseBody.put("success", true);
                return ResponseEntity.ok(responseBody);
            } else {
                responseBody.put("success", false);
                responseBody.put("error", response.getBody());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
            }
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Lỗi hệ thống khi đổi mật khẩu!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
