package org.example.Controllers.admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminHomeController")
@RequestMapping("/admin")
public class HomeController {
    @GetMapping({"/", "/dashboard"})
    public String home(Model model) {
        return "admin/index";
    }
}
