package hu.lacztam.logistic.dto;

import java.time.LocalDateTime;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import hu.lacztam.logistic.model.TransportPlan;


@NotNull
public class MilestoneDto {
	
	private long milestoneId;
	@OneToOne
	private AddressDto addressDto;
	private LocalDateTime plannedTime;
	
	@ManyToOne
	TransportPlanDto transportPlanDto;
	
	public MilestoneDto() {
	}

	public long getMilestoneId() {
		return milestoneId;
	}

	public AddressDto getAddressDto() {
		return addressDto;
	}

	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}

	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

	public TransportPlanDto getTransportPlanDto() {
		return transportPlanDto;
	}

	public void setTransportPlanDto(TransportPlanDto transportPlanDto) {
		this.transportPlanDto = transportPlanDto;
	}

}
