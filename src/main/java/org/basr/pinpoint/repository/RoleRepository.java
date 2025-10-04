package org.basr.pinpoint.repository;

import org.basr.pinpoint.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
}
