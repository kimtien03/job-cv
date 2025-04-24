package org.example.Controllers.user;
import org.example.Models.PaginatedResponse;
import org.example.Models.Template_cvs;
import org.example.Services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;

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
        model.addAttribute("industries", industryService.getAllIndustries());
        model.addAttribute("styles", styleService.getAllStyles());
        model.addAttribute("apiBaseUrl", apiBaseUrl);
        return "user/survey";
    }
    @GetMapping("/templates")
    public String templates(
            @RequestParam(value = "industryId", required = false) Integer industryId,
            @RequestParam(value = "positionId", required = false) Integer positionId,
            @RequestParam(value = "styleId", required = false) Integer styleId,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            Model model) {
        model.addAttribute("apiBaseUrl", apiBaseUrl);
        model.addAttribute("industries", industryService.getAllIndustries());
        model.addAttribute("styles", styleService.getAllStyles());
        model.addAttribute("selectedIndustryId", industryId);
        model.addAttribute("selectedPositionId", positionId);
        model.addAttribute("selectedStyleId", styleId);
        PaginatedResponse pageResponse = templateCvService.getTemplateCvs(industryId, positionId, styleId, page);
        model.addAttribute("template_cvs", pageResponse.getContent());
        model.addAttribute("currentPage", pageResponse.getNumber() + 1);
        model.addAttribute("totalPages", pageResponse.getTotalPages());
        return "user/templates";
    }

    @GetMapping("/editResume")
    public String editResume(
            @RequestParam(value = "id") Integer id,
            Model model) {

        return "user/editResume";
    }

}