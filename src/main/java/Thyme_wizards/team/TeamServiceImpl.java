package Thyme_wizards.team;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {


    public TeamServiceImpl() {

    }

    public List<Team> getTeams() {
        List<Team> teams = new ArrayList<Team>();
        teams.add(new Team(1, "Initiates"));
        teams.add(new Team(2, "Knights"));
        teams.add(new Team(3, "Padawans"));
        teams.add(new Team(4, "Rookies"));
        teams.add(new Team(5, "Wizards"));
        return teams;
    }

}
