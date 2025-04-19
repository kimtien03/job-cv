package org.example.Controllers.admin;
import org.example.Models.LoginRequest;
import org.example.Models.LoginResponse;
import org.example.Models.User;
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

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model) {
        LoginRequest loginRequest = new LoginRequest(user.getUsername(), user.getPassword());
        try {
            ResponseEntity<LoginResponse> response = jobService.login(loginRequest);
            if (response.getBody().getToken() != null && response.getBody().getUser() != null) {
                model.addAttribute("message", "Đăng nhập thành công!");
                if(response.getBody().getUser().getRole().equals("ADMIN") ) {
                    return "redirect:admin/";
                }
                else {
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
    @PostMapping("/google")
    @ResponseBody // 👈 thêm dòng này để trả về JSON thay vì view
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


}
