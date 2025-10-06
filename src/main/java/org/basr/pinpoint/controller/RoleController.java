package org.basr.pinpoint.controller;

import org.basr.pinpoint.dto.RoleDto;
import org.basr.pinpoint.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public List<RoleDto> getAllRoles() {
        return roleService.getAllRoles();
    }

}
