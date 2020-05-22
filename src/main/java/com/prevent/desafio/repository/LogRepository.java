package com.prevent.desafio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prevent.desafio.entity.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {

	List<Log> findAll();
	Optional<Log> findById(Long id);
	List<Log> findByIp(String ip);
	void deleteById(Long id);
}
