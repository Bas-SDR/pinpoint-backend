package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.LeagueRequestDto;
import org.basr.pinpoint.exception.ResourceNotFoundException;
import org.basr.pinpoint.mapper.LeagueMapper;
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

    public League getLeagueById(Long id) {
        return this.repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("League " + id + " not found"));
    }

    public League getLeagueByName(String leagueName) {
        return this.repos.findByLeagueName(leagueName).orElseThrow(() -> new ResourceNotFoundException("League \"" + leagueName + "\" not found"));
    }

    public League createLeague(LeagueRequestDto leagueRequestDto) {
        League league = LeagueMapper.toCreateEntity(leagueRequestDto);
        return this.repos.save(league);
    }

}
