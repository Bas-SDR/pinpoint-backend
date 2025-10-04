package org.basr.pinpoint.service;

import org.basr.pinpoint.dto.RoleDto;
import org.basr.pinpoint.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(role -> {
                    RoleDto dto = new RoleDto();
                    dto.rolename = role.getRolename();
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
