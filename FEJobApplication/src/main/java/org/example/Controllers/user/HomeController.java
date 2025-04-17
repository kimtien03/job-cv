package org.example.Controllers.user;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        return "user/index";
    }
    @GetMapping("/survey")
    public String survey(Model model) {
        return "user/survey";
    }
}
