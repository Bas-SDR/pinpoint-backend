package org.basr.pinpoint.repository;

import org.basr.pinpoint.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
