package io.github.petty.users.repository;

import io.github.petty.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository // Repository Annotation 추가
public interface UsersRepository extends JpaRepository<Users, UUID> {
    Boolean existsByUsername(String username);

    Users findByUsername(String username);
}
