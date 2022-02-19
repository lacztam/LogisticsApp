package hu.lacztam.logistic.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NotNull
public class TransportDelayDto {

	@NotNull
	@NotEmpty
	Long milestoneDtoId;
	
	@NotNull
	@NotEmpty
	Long delayInMinutes;
	
}
