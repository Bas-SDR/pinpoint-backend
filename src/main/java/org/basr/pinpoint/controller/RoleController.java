package org.basr.pinpoint.controller;

import org.basr.pinpoint.dto.RoleDto;
import org.basr.pinpoint.model.Role;
import org.basr.pinpoint.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService service;

    public RoleController(RoleService roleService) {
        this.service = roleService;
    }

    @GetMapping
    public List<RoleDto> getAllRoles() {
        return service.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id) {
        Role role = service.getRoleById(id);
        RoleDto dto = new RoleDto(role.getId(), role.getRolename());
        return ResponseEntity.ok(dto);
    }

}
