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

	public TransportDelayDto() {
		super();
	}

	public Long getMilestoneDtoId() {
		return milestoneDtoId;
	}

	public Long getDelayInMinutes() {
		return delayInMinutes;
	}

	public void setMilestoneDtoId(Long milestoneDtoId) {
		this.milestoneDtoId = milestoneDtoId;
	}

	public void setDelayInMinutes(Long delayInMinutes) {
		this.delayInMinutes = delayInMinutes;
	}

	@Override
	public String toString() {
		return "TransportDelayDto [milestoneDtoId=" + milestoneDtoId + ", delayInMinutes=" + delayInMinutes + "]";
	}
}
