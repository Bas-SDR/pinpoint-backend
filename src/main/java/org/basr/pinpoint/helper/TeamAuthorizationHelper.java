package org.basr.pinpoint.helper;

import org.basr.pinpoint.model.Team;
import org.basr.pinpoint.security.MyUserDetails;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;

@Component
public class TeamAuthorizationHelper {

    public void checkCaptainOrAdmin(Team team, MyUserDetails principal) throws AccessDeniedException {

        boolean isCaptain = team.getCaptain() != null && team.getCaptain().getId() == principal.getId();
        boolean isAdmin = principal.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (!isCaptain && !isAdmin) {
            throw new AccessDeniedException("Only the captain or admin can update the team");
        }
    }
}
