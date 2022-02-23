package hu.lacztam.logistic.dto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import hu.lacztam.logistic.config.ConfigProperties;

@NotNull
public class TransportPlanDto {
	
	@Autowired ConfigProperties config;

	private long transportId;
	private long expectedIncome;
	private long finalIncome;
	
	@OneToMany
	private List<SectionDto> sectionDtos;

	private LocalDateTime expectedArrivalTime;
	private LocalDateTime delayedArrivalTime;
	
	
	public TransportPlanDto() {
		this.delayedArrivalTime = this.expectedArrivalTime;
		this.finalIncome = this.expectedIncome;
	}
	
	public void addDelay(long minutes) {
		this.delayedArrivalTime.plusMinutes(minutes);
		calculateFinalIncome(minutes);
	}
	
	private void calculateFinalIncome(long minutes) {
		if(minutes >= 30) 
			this.finalIncome = (int)((1.0f - config.getPenaltyPercent1()) * this.finalIncome);
		else if(minutes >= 60) 
			this.finalIncome = (int)((1.0f - config.getPenaltyPercent2()) * this.finalIncome);
		else if(minutes >= 120) 
			this.finalIncome = (int)((1.0f - config.getPenaltyPercent3()) * this.finalIncome);
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
		this.finalIncome = expectedIncome;
	}

	public void setFinalIncome(long finalIncome) {
		this.finalIncome = finalIncome;
	}

	public void setSectionDtos(List<SectionDto> sectionDtos) {
		this.sectionDtos = sectionDtos;
	}

	public void setExpectedArrivalTime(LocalDateTime expectedArrivalTime) {
		this.expectedArrivalTime = expectedArrivalTime;
		this.delayedArrivalTime = expectedArrivalTime;
	}

	public void setDelayedArrivalTime(LocalDateTime delayedArrivalTime) {
		this.delayedArrivalTime = delayedArrivalTime;
	}

}
