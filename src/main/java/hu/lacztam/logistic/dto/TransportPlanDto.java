package hu.lacztam.logistic.dto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@NotNull
public class TransportPlanDto {

	private long transportId;
	private long expectedIncome;
	private long finalIncome;
	private List<SectionDto> sectionDtos;

	private LocalDateTime expectedArrivalTime;
	private LocalDateTime delayedArrivalTime;
	
	
	public TransportPlanDto() {
	}


	public long getTransportId() {
		return transportId;
	}


	public long getExpectedIncome() {
		return expectedIncome;
	}


	public long getFinalIncome() {
		return finalIncome;
	}


	public List<SectionDto> getSectionDtos() {
		return sectionDtos;
	}


	public LocalDateTime getExpectedArrivalTime() {
		return expectedArrivalTime;
	}


	public LocalDateTime getDelayedArrivalTime() {
		return delayedArrivalTime;
	}


	public void setTransportId(long transportId) {
		this.transportId = transportId;
	}


	public void setExpectedIncome(long expectedIncome) {
		this.expectedIncome = expectedIncome;
	}


	public void setFinalIncome(long finalIncome) {
		this.finalIncome = finalIncome;
	}


	public void setSectionDtos(List<SectionDto> sectionDtos) {
		this.sectionDtos = sectionDtos;
	}


	public void setExpectedArrivalTime(LocalDateTime expectedArrivalTime) {
		this.expectedArrivalTime = expectedArrivalTime;
	}


	public void setDelayedArrivalTime(LocalDateTime delayedArrivalTime) {
		this.delayedArrivalTime = delayedArrivalTime;
	}

}
