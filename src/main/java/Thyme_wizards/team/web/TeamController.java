package Thyme_wizards.team.web;

import Thyme_wizards.team.Team;
import Thyme_wizards.team.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.SortedSet;
import java.util.List;

@Controller
@RequestMapping("/teams")
public class TeamController {

    private final TeamService service;

    public TeamController(TeamService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public String index(Model model) {
        List<Team> teams = service.getTeams();
        model.addAttribute("teams", teams);
        return "teams/list";
    }
}

