package org.example.Controllers.user;
import org.example.Services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

@Controller
public class HomeController {
    @Autowired
    private final IndustryService industryService;
    private final PositionService positionService;
    private final StyleService styleService;
    private final TemplateCvService templateCvService;

    @Value("${api.base-url}")
    private String apiBaseUrl;
    private final Logger logger = LoggerFactory.getLogger(IndustryService.class);
    public HomeController(IndustryService industryService, PositionService positionService, StyleService styleService, TemplateCvService templateCvService) {
        this.industryService = industryService;
        this.positionService = positionService;
        this.styleService = styleService;
        this.templateCvService = templateCvService;
    }
    @GetMapping("/")
    public String home(Model model) {
        return "user/index";
    }
    @GetMapping("/survey")
    public String survey(Model model) {
        try {
            model.addAttribute("industries", industryService.getAllIndustries());
            model.addAttribute("styles", styleService.getAllStyles());
            model.addAttribute("apiBaseUrl", apiBaseUrl);
            return "user/survey";
        } catch (RuntimeException ex) {
            logger.error("Lỗi khi load survey: {}", ex.getMessage(), ex);
            String errorMsg = "Không thể tải dữ liệu ngành nghề. Vui lòng thử lại sau.";
            return "redirect:/error-page?errorMessage=" + UriUtils.encode(errorMsg, StandardCharsets.UTF_8);
        }
    }
    @GetMapping("/templates")
    public String templates(
            @RequestParam(value = "positionId", required = false) Integer positionId,
            @RequestParam(value = "styleId", required = false) Integer styleId,
            Model model) {
        try {
            model.addAttribute("apiBaseUrl", apiBaseUrl);
            model.addAttribute("industries",industryService.getAllIndustries());
            model.addAttribute("positions",positionService.getAllPositions());
            model.addAttribute("styles",styleService.getAllStyles());
            model.addAttribute("positionId",positionId);
            model.addAttribute("styleId",styleId);
            if (positionId == null && styleId == null) model.addAttribute("template_cvs",templateCvService.getAllTemplateCvs());
            else model.addAttribute("template_cvs",templateCvService.getTemplateCvsByFilter(positionId,styleId));
            return "user/templates";
        } catch (RuntimeException ex) {
            logger.error("Lỗi khi load template: {}", ex.getMessage(), ex);
            String errorMsg = "Không thể tải dữ liệu template. Vui lòng thử lại sau.";
            return "redirect:/error-page?errorMessage=" + UriUtils.encode(errorMsg, StandardCharsets.UTF_8);
        }

    }

}
