package com.everis.yankitransaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.yankitransaction.dto.YankiTransferDTO;
import com.everis.yankitransaction.model.YankiMovement;
import com.everis.yankitransaction.service.YankiMovementServiceImpl;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/yanki-movement")
public class YankiMovementController {
	
	@Autowired
	private YankiMovementServiceImpl yankiService;
	
	@PostMapping("/withdraw")
	public Mono<YankiMovement> withdrawAccount(@RequestBody YankiMovement y){
		return yankiService.withdrawAccount(y);
	}
	
	@PostMapping("/deposit")
	public Mono<YankiMovement> depositAccount(@RequestBody YankiMovement y){
		return yankiService.depositAccount(y);
	}
	
	@PostMapping("/transfer")
	public Mono<YankiMovement> transferAmount(@RequestBody YankiTransferDTO body){
		return Mono.empty();
	}
}

