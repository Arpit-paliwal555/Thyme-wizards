package Thyme_wizards;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/")
public class RootController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("pageTitle", "Taming Thymeleaf");
        model.addAttribute("scientists", List.of("Marie Curie", "Albert Einstein", "Isaac Newton", "Charles Darwin"));
        return "index";
    }
}
