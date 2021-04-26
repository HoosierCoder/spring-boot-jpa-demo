package com.daj.baseball.roster.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daj.baseball.roster.data.entities.Player;
import com.daj.baseball.roster.data.repository.PlayerRepository;
import com.daj.baseball.roster.data.service.PlayerService;

@RestController
@RequestMapping("/players")
public class PlayerController {

	@Autowired
	PlayerRepository repository;
	
	@Autowired
	PlayerService service;

	@GetMapping
	public Iterable<Player> getPlayers() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Player getPlayer(@PathVariable("id") Long id) {
		
		return service.findById(id);
	}

	@GetMapping("/findbyfirstlast/{firstname}/{lastname}")
	public Player getPlayerByFirstLast(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname) {
		
		return service.findByFirstLast(firstname, lastname);
	}

	@GetMapping("/findbylast/{lastname}")
	public List<Player> getPlayerByLastname(@PathVariable("lastname") String lastname) {
		return service.findByLastName(lastname);
	}

	@GetMapping("/findbynumber/{number}")
	public Player getPlayerByNumber(@PathVariable("number") int number) {
		return repository.findByNumber(number);
	}

	@PostMapping
	public Player create( @RequestBody Player player) {

		return repository.save(player);
	}

	@PutMapping("/update/{id}")
	public Player update(@PathVariable("id") Long id, @RequestBody Player player) {

		return service.update(id, player);
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable("id") Long id) {
		service.delete(id);
	}
}
