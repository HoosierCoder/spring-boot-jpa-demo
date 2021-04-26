package com.daj.baseball.roster.data.service;

import java.util.List;

import com.daj.baseball.roster.data.entities.Player;

public interface PlayerService {

	public Player update(Long id, Player updateInfo);
	public void delete(Long id);
	public Player findById(Long id);
	public Player findByFirstLast(String firstname, String lastname);
	public List<Player> findByLastName(String lastName);
	public Player findByNumber(Long number);
}
