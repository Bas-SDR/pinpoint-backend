package org.basr.pinpoint.service;

import org.basr.pinpoint.model.League;
import org.basr.pinpoint.repository.LeagueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueService {

    private final LeagueRepository repos;

    public LeagueService(LeagueRepository repos) {
        this.repos = repos;
    }

    public List<League> getAllLeagues() {
        return this.repos.findAll();
    }

}
