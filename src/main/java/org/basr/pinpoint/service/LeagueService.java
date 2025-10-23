package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.LeaguePatchDto;
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
        League league = LeagueMapper.toEntity(leagueRequestDto);
        return this.repos.save(league);
    }

    public boolean deleteLeagueById(Long id) {
        if (repos.existsById(id)) {
            this.repos.deleteById(id);
            return true;
        } else
            return false;
    }

    public League updateLeagueById(Long id, LeagueRequestDto leagueRequestDto) {
        League league = repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("League " + id + " not found"));
        LeagueMapper.updateEntity(league, leagueRequestDto);
        return repos.save(league);
    }

    public League patchLeagueById(Long id, LeaguePatchDto leaguePatchDto) {
        League league = repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("League " + id + " not found"));

        if (leaguePatchDto.getLeagueName() != null) {
            league.setLeagueName(leaguePatchDto.getLeagueName());
        }
        if (leaguePatchDto.getLeagueDivision() != null) {
            league.setLeagueDivision(leaguePatchDto.getLeagueDivision());
        }
        if (leaguePatchDto.getLeagueDay() != null) {
            league.setLeagueDay(leaguePatchDto.getLeagueDay());
        }

        return repos.save(league);
    }
}
