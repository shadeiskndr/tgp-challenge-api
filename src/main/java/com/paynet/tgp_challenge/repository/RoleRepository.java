package com.paynet.tgp_challenge.repository;

import com.paynet.tgp_challenge.model.ERole;
import com.paynet.tgp_challenge.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
