package com.daj.baseball.roster.data.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.daj.baseball.roster.data.entities.Positions;

public interface PositionsRepository extends CrudRepository<Positions, Long> {
	List<Positions> findByPlayerId(Long id);
	List<Positions> findByPosition(String position);
}
