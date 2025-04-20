package com.example.BEJobApplication.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class SecurityController {
    @RequestMapping("/login")
    public String redirectToLoginPage() {
        // URL login từ frontend (Ví dụ là React, Vue, hoặc Angular)
        return "redirect:http://localhost:8080/login";
    }
}
