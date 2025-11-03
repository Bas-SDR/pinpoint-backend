package org.basr.pinpoint.repository;

import org.basr.pinpoint.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> getGamesByPlayerId(long playerId);
    List<Game> getGamesByTeamId(long teamId);
    List<Game> getGamesByLeagueId(long leagueId);
}
