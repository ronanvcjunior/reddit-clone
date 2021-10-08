package com.ronan.redditclone.repository;

import java.util.List;
import java.util.Optional;

import com.ronan.redditclone.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
