package org.basr.pinpoint.repository;

import org.basr.pinpoint.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByDobAfter(LocalDate date);
}
