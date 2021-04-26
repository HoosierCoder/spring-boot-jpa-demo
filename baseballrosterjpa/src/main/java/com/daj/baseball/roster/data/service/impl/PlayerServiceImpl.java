package com.daj.baseball.roster.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daj.baseball.roster.data.entities.Player;
import com.daj.baseball.roster.data.exception.ResourceNotFoundException;
import com.daj.baseball.roster.data.repository.PlayerRepository;
import com.daj.baseball.roster.data.service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	public Player update(Long id, Player updateInfo) {

		Player player = playerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Player", "id", id));

		if (updateInfo.getLastName() != null && !updateInfo.getLastName().equals(player.getLastName())) {
			player.setLastName(updateInfo.getLastName());
		}
		if (updateInfo.getFirstName() != null && !updateInfo.getFirstName().equals(player.getFirstName())) {
			player.setFirstName(updateInfo.getFirstName());
		}
		if (updateInfo.getNumber() > 0 && updateInfo.getNumber() != player.getNumber()) {
			player.setNumber(updateInfo.getNumber());
		}
		if (updateInfo.getPositions() != null && !updateInfo.getPositions().isEmpty()) {
			player.setPositions(updateInfo.getPositions());
		}

		return playerRepository.save(player);
	}

	@Override
	public void delete(Long id) {
		Player player = playerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Player", "id", id));
		playerRepository.deleteById(id);
	}

	@Override
	public Player findById(Long id) {
		return playerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Player", "id", id));
	}

	@Override
	public Player findByFirstLast(String firstname, String lastname) {
		Player player = playerRepository.findByFirstNameAndLastName(firstname, lastname);
		if (player == null)
			throw new ResourceNotFoundException("Player", "first last", firstname + " " + lastname);

		return player;
	}

	@Override
	public List<Player> findByLastName(String lastName) {
		List<Player> list = playerRepository.findByLastName(lastName);
		if (list == null || list.isEmpty())
			throw new ResourceNotFoundException("Player", "last name", lastName);

		return list;
	}

	@Override
	public Player findByNumber(Long number) {
		Player player = playerRepository.findByNumber(number.intValue());
		if (player == null)
			throw new ResourceNotFoundException("Player", "number", number.toString());

		return player;
	}

}
