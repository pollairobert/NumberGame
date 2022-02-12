package com.rs.numbergame.dto;

import java.util.List;

public class GamerDto {
  
  private Long id;
  private String name;
  private List<GameStatsDto> gameStats;  

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<GameStatsDto> getGameStats() {
    return this.gameStats;
  }

  public void setGameStats(List<GameStatsDto> gameStats) {
    this.gameStats = gameStats;
  }

}
