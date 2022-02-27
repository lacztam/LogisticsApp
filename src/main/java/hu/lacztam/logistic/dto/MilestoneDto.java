package hu.lacztam.logistic.dto;

import java.time.LocalDateTime;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@NotNull
public class MilestoneDto {
	
	private long milestoneId;
	@OneToOne
	private AddressDto addressDto;
	private LocalDateTime plannedTime;
	
	@ManyToOne
	SectionDto sectionDto;
	
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

	public SectionDto getSectionDto() {
		return sectionDto;
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

	public void setSectionDto(SectionDto sectionDto) {
		this.sectionDto = sectionDto;
	}

}
