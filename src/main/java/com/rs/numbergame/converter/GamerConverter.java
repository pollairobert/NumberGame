package com.rs.numbergame.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.rs.numbergame.dto.GamerDto;
import com.rs.numbergame.models.Gamer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GamerConverter {

  GameStatsConverter gameStatsConverter;

  @Autowired
  public void setGameStatsConverter(GameStatsConverter gameStatsConverter) {
    this.gameStatsConverter = gameStatsConverter;
  }

  public GamerDto entityToDtoForGamerToAllStat(Gamer gamer) {
    GamerDto dto = new GamerDto();
    dto.setId(gamer.getId());
    dto.setName(gamer.getName());
    dto.setGameStats(gameStatsConverter.entityToDtoForStatToGamerList(gamer.getGameStats()));
    return dto;
  }

  public List<GamerDto> entityToDtoForGamerToAllStatList(List<Gamer> cities) {
    return cities.stream().map(x -> entityToDtoForGamerToAllStat(x)).collect(Collectors.toList());
  }

  public GamerDto entityToDtoForGamerToStat(Gamer gamer) {
    GamerDto dto = new GamerDto();
    dto.setId(gamer.getId());
    dto.setName(gamer.getName());
    return dto;
  }
}
