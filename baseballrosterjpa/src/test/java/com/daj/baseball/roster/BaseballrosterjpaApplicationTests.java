package com.daj.baseball.roster;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.daj.baseball.roster.data.entities.Player;
import com.daj.baseball.roster.data.entities.Positions;
import com.daj.baseball.roster.data.repository.PlayerRepository;

@SpringBootTest
class BaseballrosterjpaApplicationTests {

	@Autowired
	ApplicationContext context;
	
	@Test
	void newPlayer() {
		PlayerRepository playerRepository = context.getBean(PlayerRepository.class);
		
		Player player = new Player();
		player.setFirstName("Jake");
		player.setLastName("Taylor");
		player.setNumber(Integer.MAX_VALUE);
		
		try {
			playerRepository.save(player);
		}catch(IllegalArgumentException ex) {}
		
		assert( playerRepository.findByNumber(Integer.MAX_VALUE)!=null );
		
	}

	@Test
	void setPositions() {
		PlayerRepository playerRepository = context.getBean(PlayerRepository.class);
		
		Player player = playerRepository.findByFirstNameAndLastName("Jake", "Taylor");
		
		if( player != null ) {
			HashSet<Positions> positionsSet = new HashSet<Positions>();
			Positions positions = new Positions();
			positions.setPlayerId( player.getId() );
			positions.setPosition( "Catcher");
			positionsSet.add(positions);
			
			player.setPositions(positionsSet);
			Player p2 = playerRepository.save(player);
			
			assert( p2.getPositions()!=null && !p2.getPositions().isEmpty() );
		}
		
	}
	
	@Test
	void deletePlayer() {
		PlayerRepository playerRepository = context.getBean(PlayerRepository.class);
		
		Player player = playerRepository.findByFirstNameAndLastName("Jake", "Taylor");
		
		if( player != null ) {
			playerRepository.deleteById(player.getId());
			
			Optional<Player> player2 = playerRepository.findById(player.getId());
			
			assert(!player2.isPresent());
		}
	}

}
