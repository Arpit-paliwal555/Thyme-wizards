package Thyme_wizards.team;

import org.jspecify.annotations.Nullable;

import java.util.List;

public interface TeamService {
    List<Team> getTeams();

    @Nullable Object getTeamInfo(String teamId);

    @Nullable Object getPlayerOnTeam(String teamId, String playerId);
}