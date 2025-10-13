package org.basr.pinpoint.repository;

import org.basr.pinpoint.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
