package com.heliton.desafioILog.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.heliton.desafioILog.domain.LogSistema;

@Repository
public interface LogSistemaRepository extends MongoRepository<LogSistema, Integer> {

}
