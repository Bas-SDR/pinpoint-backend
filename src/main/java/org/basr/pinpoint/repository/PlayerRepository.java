package org.basr.pinpoint.repository;

import org.basr.pinpoint.model.Player;
import org.basr.pinpoint.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findTop10ByOrderByStatsHighestSeriesDesc();
    List<Player> findTop10ByOrderByStatsHighestGameDesc();
}
