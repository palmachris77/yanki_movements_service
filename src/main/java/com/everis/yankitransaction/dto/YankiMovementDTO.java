package com.everis.yankitransaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YankiMovementDTO {
	private String cellphoneNumber;
	private Double amount;
	
}
