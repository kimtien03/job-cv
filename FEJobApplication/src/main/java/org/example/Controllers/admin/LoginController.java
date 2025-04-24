package org.example.Controllers.admin;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Models.*;

import org.example.Services.JobApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/check-email")
    public ResponseEntity<Boolean> checkEmailFromBE(@RequestBody String email) {
        boolean exists = jobService.checkEmailExists(email);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user,
                        RedirectAttributes redirectAttributes,
                        Model model,
                        HttpServletResponse response) {
        LoginRequest loginRequest = new LoginRequest(user.getUsername(), user.getPassword());
        try {
            ResponseEntity<LoginResponse> loginResponse = jobService.login(loginRequest);
            if (loginResponse.getBody().getToken() != null && loginResponse.getBody().getUser() != null) {
                // Thêm cookie có thời hạn 1 tiếng (3600 giây)
                Cookie tokenCookie = new Cookie("token", loginResponse.getBody().getToken());
                tokenCookie.setMaxAge(3600); // 1 giờ
                tokenCookie.setPath("/");
                tokenCookie.setHttpOnly(true); // Tùy chọn bảo mật
                Cookie usernameCookie = new Cookie("username", loginResponse.getBody().getUser().getUsername());
                usernameCookie.setMaxAge(3600);
                usernameCookie.setPath("/");
                // Gửi cookie về client
                response.addCookie(tokenCookie);
                response.addCookie(usernameCookie);
                // Toast và chuyển hướng
                redirectAttributes.addFlashAttribute("toastMessage", "Đăng nhập thành công!");
                if ("ADMIN".equals(loginResponse.getBody().getUser().getRole())) {
                    return "redirect:/admin/";
                } else {
                    return "redirect:/";
                }
            } else {
                model.addAttribute("loginError", loginResponse.getBody().getMessage());
                return "Auth/login";
            }

        } catch (Exception e) {
            model.addAttribute("loginError", "Lỗi kết nối máy chủ!");
            return "Auth/login";
        }
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserCreate user, RedirectAttributes redirectAttributes, Model model) {
        try {
            user.setRole("USER");
            ResponseEntity<?> response = jobService.register(user);
            // Kiểm tra xem response có thành công không
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                redirectAttributes.addFlashAttribute("toastMessage", "Đăng ký thành công!");
                return "redirect:/login";
            } else {
                // Nếu không thành công, xử lý lỗi trả về
                if (response.getBody() != null) {
                    ErrorResponse errorResponse = (ErrorResponse) response.getBody();
                    System.out.println(errorResponse.getMessage());
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
    public ResponseEntity<Map<String, Object>> loginWithGoogle(@RequestBody Map<String, String> payload,
                                                               HttpServletResponse response) {
        Map<String, Object> responseData = new HashMap<>();

        try {
            String idToken = payload.get("idToken");
            ResponseEntity<LoginResponse> loginResponse = jobService.loginWithGoogle(idToken);

            if (loginResponse.getBody().getToken() != null && loginResponse.getBody().getUser() != null) {
                // Thêm cookie: token, username, email...
                Cookie tokenCookie = new Cookie("token", loginResponse.getBody().getToken());
                tokenCookie.setMaxAge(3600); // 1 giờ
                tokenCookie.setHttpOnly(true);
                tokenCookie.setPath("/");

                Cookie emailCookie = new Cookie("email", loginResponse.getBody().getUser().getEmail());
                emailCookie.setMaxAge(3600);
                emailCookie.setPath("/");

                Cookie fullnameCookie = new Cookie("fullname", loginResponse.getBody().getUser().getUsername());
                fullnameCookie.setMaxAge(3600);
                fullnameCookie.setPath("/");

                // Gửi cookie về client
                response.addCookie(tokenCookie);
                response.addCookie(emailCookie);
                response.addCookie(fullnameCookie);

                responseData.put("LoginResponse", loginResponse);
                return ResponseEntity.ok(responseData);
            } else {
                responseData.put("LoginResponse", loginResponse);
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
