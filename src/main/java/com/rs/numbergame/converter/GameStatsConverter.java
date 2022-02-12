package com.rs.numbergame.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.rs.numbergame.dto.GameStatsDto;
import com.rs.numbergame.models.GameStats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameStatsConverter {

  GamerConverter gamerConverter;

  @Autowired
  public void setGamerConverter(GamerConverter gamerConverter) {
    this.gamerConverter = gamerConverter;
  }

  public GameStatsDto entityToDtoForStatToGamer(GameStats gameStats) {
    GameStatsDto dto = new GameStatsDto();
    dto.setId(gameStats.getId());
    dto.setGameNumber(gameStats.getGameNumber());
    dto.setGameTime(gameStats.getGameTime());
    dto.setStartTime(gameStats.getStartTime());
    dto.setGamer(gamerConverter.entityToDtoForGamerToStat(gameStats.getGamer()));
    return dto;
  }

  public List<GameStatsDto> entityToDtoForStatToGamerList(List<GameStats> cities) {
    return cities.stream().map(x -> entityToDtoForStatToGamer(x)).collect(Collectors.toList());
  }
}
