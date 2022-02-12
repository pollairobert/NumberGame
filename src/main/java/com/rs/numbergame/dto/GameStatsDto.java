package com.rs.numbergame.dto;

public class GameStatsDto {
  
  private Long id;
  private GamerDto gamer;
  private int gameNumber;
  private long startTime;
  private long gameTime;

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public GamerDto getGamer() {
    return this.gamer;
  }

  public void setGamer(GamerDto gamer) {
    this.gamer = gamer;
  }

  public int getGameNumber() {
    return this.gameNumber;
  }

  public void setGameNumber(int gameNumber) {
    this.gameNumber = gameNumber;
  }

  public long getStartTime() {
    return this.startTime;
  }

  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }

  public long getGameTime() {
    return this.gameTime;
  }

  public void setGameTime(long gameTime) {
    this.gameTime = gameTime;
  }

}
