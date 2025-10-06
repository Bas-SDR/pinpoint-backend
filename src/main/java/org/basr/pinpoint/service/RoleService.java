package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.RoleDto;
import org.basr.pinpoint.model.Role;
import org.basr.pinpoint.model.User;
import org.basr.pinpoint.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository repos;

    public RoleService(RoleRepository repos) {
        this.repos = repos;
    }

    public List<RoleDto> getAllRoles() {
        return repos.findAll()
                .stream()
                .map(role -> {
                    RoleDto dto = new RoleDto();
                    dto.setRolename(role.getRolename());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public Role getRoleById(Long id) {
        return repos.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    public Role getRoleByName(String rolename) {
        return repos.findByRolename(rolename)
                .orElseThrow(() -> new RuntimeException("Role not found: " + rolename));
    }

    public void assignDefaultRole(User user) {
        user.getRoles().add(getRoleByName("ROLE_USER"));
    }


}
