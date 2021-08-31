package com.everis.yankitransaction.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.everis.yankitransaction.model.YankiMovement;

@Repository
public interface YankiMovementRepository extends ReactiveMongoRepository<YankiMovement, UUID>{

}
