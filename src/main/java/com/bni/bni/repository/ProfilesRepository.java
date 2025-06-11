package com.bni.bni.repository;

import com.bni.bni.entity.Profiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfilesRepository extends JpaRepository<Profiles, Long> {
    Optional<Profiles> findByUserId(Long userId);
    boolean existsByUserId(Long userId);
}
