package org.basr.pinpoint.repository;

import org.basr.pinpoint.model.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeagueRepository extends JpaRepository<League,Long> {
    Optional<League> findByLeagueName(String leagueName);
}
