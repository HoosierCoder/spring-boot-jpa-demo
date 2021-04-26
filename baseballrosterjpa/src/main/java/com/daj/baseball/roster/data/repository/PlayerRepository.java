package com.daj.baseball.roster.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.daj.baseball.roster.data.entities.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {
	Player findByFirstNameAndLastName(String firstName, String lastName);
	List<Player> findByLastName(String lastName);
	Player findByNumber(int number);
}
