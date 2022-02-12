package com.rs.numbergame.repos;

import java.util.Optional;

import com.rs.numbergame.models.Gamer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamerRepo extends JpaRepository<Gamer, Long> {

  Optional<Gamer> findByName(String gamerName);
}
