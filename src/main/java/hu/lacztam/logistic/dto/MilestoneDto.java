package hu.lacztam.logistic.dto;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;


public class MilestoneDto {
	
	private long milestoneId;
	private AddressDto addressDto;
	private LocalDateTime plannedTime;
	
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
	
}
