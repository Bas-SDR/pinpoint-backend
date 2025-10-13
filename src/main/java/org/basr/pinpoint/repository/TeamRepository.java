package org.basr.pinpoint.repository;

import org.basr.pinpoint.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
