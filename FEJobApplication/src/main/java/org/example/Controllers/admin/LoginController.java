package org.example.Controllers.admin;
import org.example.Models.LoginRequest;
import org.example.Models.LoginResponse;
import org.example.Models.User;
import org.example.Services.JobApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
            System.out.println(response.getBody().getToken()) ;
            System.out.println(response.getBody().getUser());
            if (response.getBody().getToken() != null && response.getBody().getUser() != null) {
                model.addAttribute("message", "Đăng nhập thành công!");
                System.out.println("TEST1");
                if(response.getBody().getUser().getRole().equals("ADMIN") ) {
                    return "redirect:admin/";
                }
                else {
                    return "redirect:/";
                }
            } else {
                System.out.println("TEST2");
                model.addAttribute("error", response.getBody().getMessage());
                return "Auth/login";
            }

        } catch (Exception e) {
            System.out.println("TEST3");

            model.addAttribute("error", "Lỗi kết nối máy chủ!");
            return "Auth/login";

        }
    }

}
