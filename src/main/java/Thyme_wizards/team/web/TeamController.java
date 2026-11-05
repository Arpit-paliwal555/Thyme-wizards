package Thyme_wizards.team.web;

import Thyme_wizards.team.Team;
import Thyme_wizards.team.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{id}")
    public String teamInfo(@PathVariable("id") String teamId, Model model) {
        model.addAttribute("teamInfo", service.getTeamInfo(teamId));
        return "teams/info";
    }
    @GetMapping("/{teamId}/players/{playerId}")
    public String playerOnTeamInfo(@PathVariable("teamId") String teamId,
                                   @PathVariable("playerId") String playerId,
                                   Model model) {
        model.addAttribute("player", service.getPlayerOnTeam(teamId, playerId));
        return "teams/info";
    }

    @PostMapping("/{id}")
    public String editTeamName(@PathVariable("id") String teamId,
                               @ModelAttribute("editTeamFormData") EditTeamFormData formData) {
        service.changeTeamName(teamId, formData.getTeamName());
        return "redirect:/teams/all";
    }

    public static class EditTeamFormData {
        private String teamName;

        public String getTeamName() {
            return teamName;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }
    }
}
