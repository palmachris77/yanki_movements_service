package com.everis.yankitransaction.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.everis.yankitransaction.dto.YankiMovementDTO;
import com.everis.yankitransaction.model.YankiMovement;
import com.everis.yankitransaction.repository.YankiMovementRepository;

import reactor.core.publisher.Mono;

@Service
public class YankiMovementServiceImpl {
	
	private static final String TOPIC_WITHDRAW = "withdraw-yanki-topic";
	private static final String TOPIC_DEPOSIT = "deposit-yanki-topic";
	
	@Autowired
	private YankiMovementRepository yankiMovementRepository;
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	
	public Mono<YankiMovement> withdrawAccount(YankiMovement y){
		YankiMovementDTO rq = YankiMovementDTO.builder()
				.amount(y.getAmount())
				.cellphoneNumber(y.getCellphoneNumber())
				.build();
		YankiMovement ySave = YankiMovement.builder()
										.id(UUID.randomUUID())
										.movementDate(LocalDateTime.now())
										.typeMovement("Withdraw")
										.amount(y.getAmount())
										.cellphoneNumber(y.getCellphoneNumber())
										.build();
		this.kafkaTemplate.send(TOPIC_WITHDRAW,rq);
		return yankiMovementRepository.save(ySave);
	}
	public Mono<YankiMovement> depositAccount(YankiMovement y){
		YankiMovementDTO rq = YankiMovementDTO.builder()
								.amount(y.getAmount())
								.cellphoneNumber(y.getCellphoneNumber())
								.build();
		
		this.kafkaTemplate.send(TOPIC_DEPOSIT,rq);
		YankiMovement ySave = YankiMovement.builder()
								.id(UUID.randomUUID())
								.movementDate(LocalDateTime.now())
								.typeMovement("Deposit")
								.amount(y.getAmount())
								.cellphoneNumber(y.getCellphoneNumber())
								.build();
		return yankiMovementRepository.save(ySave);
	}
	
	
}
