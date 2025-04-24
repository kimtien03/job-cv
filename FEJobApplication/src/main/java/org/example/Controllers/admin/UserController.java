package org.example.Controllers.admin;
import org.example.Services.JobApiService;
import org.example.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class UserController {

    private final JobApiService jobService;

    // Inject JobApiService qua constructor
    @Autowired
    public UserController(JobApiService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/user")
    public String userList(Model model) {
        List<User> users = jobService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user";
    }
//    @GetMapping("/edit/{id}")
//    public String userList(@RequestParam int id,Model model) {
//        User user= jobService.getUserByID(id);
//        model.addAttribute("usered", user);
//        return "admin/user/edit";
//    }

//    @PostMapping("/user")
//    public String Delete(@RequestParam(value = "id", required = false) Integer id) {
//        if (id != null) {
//            jobService.deleteUser(id);
//        }
//        return "redirect:/admin/user";
//    }

}
