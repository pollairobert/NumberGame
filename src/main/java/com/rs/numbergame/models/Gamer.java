package com.rs.numbergame.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "GAMER", uniqueConstraints = { @UniqueConstraint(columnNames = "name")})  
public class Gamer {

  @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "GAMER_ID")
	private Long id;

  //@JsonIgnore
  //@JsonBackReference
  @JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "gamer")
	private List<GameStats> gameStats;

  private String name;

  @Transient
  private long currentGameStatId;
  
  @Transient
  private int tipp;

  public Gamer() {
  }

  public Gamer(String name) {
    this.name = name;
  }


  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<GameStats> getGameStats() {
    return this.gameStats;
  }

  public void setGameStats(List<GameStats> gameStats) {
    this.gameStats = gameStats;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getCurrentGameStatId() {
    return this.currentGameStatId;
  }

  public void setCurrentGameStatId(long currentGameStatId) {
    this.currentGameStatId = currentGameStatId;
  }

  public int getTipp() {
    return this.tipp;
  }

  public void setTipp(int tipp) {
    this.tipp = tipp;
  }
  

}
