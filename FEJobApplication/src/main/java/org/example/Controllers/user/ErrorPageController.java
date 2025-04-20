package org.example.Controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ErrorPageController {
    @GetMapping("/error-page")
    public String showErrorPage(@RequestParam(required = false) String errorMessage, Model model) {
        model.addAttribute("errorMessage", errorMessage != null ? errorMessage : "Đã xảy ra lỗi không xác định.");
        return "user/error";
    }
}