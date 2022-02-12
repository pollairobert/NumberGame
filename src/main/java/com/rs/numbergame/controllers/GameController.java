package com.rs.numbergame.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.rs.numbergame.converter.GameStatsConverter;
import com.rs.numbergame.converter.GamerConverter;
import com.rs.numbergame.dto.GameStatsDto;
import com.rs.numbergame.models.GameStats;
import com.rs.numbergame.models.Gamer;
import com.rs.numbergame.repos.GameStatsRepo;
import com.rs.numbergame.repos.GamerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class GameController {

	GameStatsRepo gameStatsRepo;
	GamerRepo gamerRepo;
	GameStatsConverter gameStatsConverter;
	GamerConverter gamerConverter;

	@Autowired
	public void setGameStatsRepo(GameStatsRepo gameStatsRepo) {
		this.gameStatsRepo = gameStatsRepo;
	}

	@Autowired
	public void setGamerRepo(GamerRepo gamerRepo) {
		this.gamerRepo = gamerRepo;
	}

	@Autowired
	public void setGameStatsConverter(GameStatsConverter gameStatsConverter) {
		this.gameStatsConverter = gameStatsConverter;
	}

	@Autowired
	public void setGamerConverter(GamerConverter gamerConverter) {
		this.gamerConverter = gamerConverter;
	}
	

	@PostMapping("/game/regorlog")
	public ResponseEntity<?> gamerRegOrLogin(@RequestBody Gamer gamer) {

		Optional<Gamer> gamerData = gamerRepo.findByName(gamer.getName());

		if (gamerData.isPresent()) {
			return ResponseEntity.ok(gamerData.get());
		} else {
			try {
				return ResponseEntity.ok(gamerRepo.save(gamer));
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(e);
			}
		}
	}

	@PostMapping("/game/{command}")
	public ResponseEntity<?> gameController(@PathVariable(value = "command") String command, @RequestBody Gamer gamer) {

		if (command.equals("start")) {

			Random r = new Random();
			int randomInt = r.nextInt(100) + 1;
			long startTimeToMilSec = System.currentTimeMillis();
			GameStats gameStats = new GameStats(gamer, randomInt, startTimeToMilSec);

			try {
				GameStats _gs = gameStatsRepo.save(gameStats);
				return ResponseEntity.ok(_gs.getId());
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(e);
			}

		} else {
			long gameStatId = gamer.getCurrentGameStatId();
			int gamerTipp = gamer.getTipp();
			Optional<GameStats> gameStatData = gameStatsRepo.findById(gameStatId);
			if (gameStatData.isPresent()) {
				GameStats _gameStatData = gameStatData.get();
				int gameNumber = _gameStatData.getGameNumber();
				if (gameNumber == gamerTipp) {
					long endTimeToMilSec = System.currentTimeMillis();
					long gameTime = (endTimeToMilSec - _gameStatData.getStartTime()) / 1000;
					_gameStatData.setGameTime(gameTime);
					try {
						return ResponseEntity.ok(gameStatsRepo.save(_gameStatData));

					} catch (Exception e) {
						return ResponseEntity.badRequest().body(e);

					}

				} else if (gameNumber > gamerTipp) {
					return ResponseEntity.ok("A tipped kisebb");
				} else {
					return ResponseEntity.ok("A tipped nagyobb");
				}
			} else {
				return ResponseEntity.badRequest().body("HIBA");
			}
		}
	}
	@GetMapping("/game/list/{id}")
  public List<GameStatsDto> getOwnStats(@PathVariable long id) {
    List<GameStatsDto> ownStats = gameStatsConverter.entityToDtoForStatToGamerList(gameStatsRepo.findAllOwnStats(id));
    return ownStats;
  }
	@GetMapping("/game/list")
  public List<GameStatsDto> getAllStats() {
    List<GameStatsDto> allStats = gameStatsConverter.entityToDtoForStatToGamerList(gameStatsRepo.findAllByOrderByGameTimeAsc());
    return allStats;
  }

}
