package com.rs.numbergame.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "GAME_STATS")
public class GameStats {

  @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "GAME_STAT_ID")
	private Long id;

  @JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GAMER_ID")
	private Gamer gamer;

  private int gameNumber;
  private long startTime;
  private long gameTime;
  
  public GameStats() {
  }

  public GameStats(Gamer gamer, int gameNumber, long startTime) {
    this.gamer = gamer;
    this.gameNumber = gameNumber;
    this.startTime = startTime;
  }


  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Gamer getGamer() {
    return this.gamer;
  }

  public void setGamer(Gamer gamer) {
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
