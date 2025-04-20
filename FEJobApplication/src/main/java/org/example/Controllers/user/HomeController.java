package org.example.Controllers.user;
import org.example.Services.IndustryService;
import org.example.Services.JobApiService;
import org.example.Services.PositionService;
import org.example.Services.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private final IndustryService industryService;
    private final PositionService positionService;
    private final StyleService styleService;

    @Value("${api.base-url}")
    private String apiBaseUrl;

    public HomeController(IndustryService industryService, PositionService positionService, StyleService styleService) {
        this.industryService = industryService;
        this.positionService = positionService;
        this.styleService = styleService;
    }
    @GetMapping("/")
    public String home(Model model) {
        return "user/index";
    }
    @GetMapping("/survey")
    public String survey(Model model) {
        model.addAttribute("industries",industryService.getAllIndustries());
        model.addAttribute("styles",styleService.getAllStyles());
        model.addAttribute("apiBaseUrl", apiBaseUrl);
        return "user/survey";
    }
    @GetMapping("/templates")
    public String templates(Model model) {
        model.addAttribute("industries",industryService.getAllIndustries());
        model.addAttribute("positions",positionService.getAllPositions());
        model.addAttribute("styles",styleService.getAllStyles());
        return "user/templates";
    }

}
