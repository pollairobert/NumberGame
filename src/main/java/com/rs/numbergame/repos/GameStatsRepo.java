package com.rs.numbergame.repos;

import java.util.List;
import java.util.Optional;

import com.rs.numbergame.models.GameStats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameStatsRepo extends JpaRepository<GameStats, Long> {

  Optional<GameStats> findById(Long statId);
  // GameStats save(GameStats gameStat);

  void deleteById(Long statId);

  List<GameStats> findAllByOrderByGameTimeAsc();

  @Query(value = "SELECT * FROM game_stats WHERE gamer_id= :id ORDER BY game_time ASC", nativeQuery = true)
  List<GameStats> findAllOwnStats(@Param("id") Long id);
}
