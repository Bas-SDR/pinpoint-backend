package org.basr.pinpoint.service;

import org.basr.pinpoint.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository repos;

    public PlayerService(PlayerRepository repos) {
        this.repos = repos;
    }
}
