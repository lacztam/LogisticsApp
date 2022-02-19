package hu.lacztam.logistic.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@NotNull
public class MilestoneDto {
	
	private long milestoneId;
	@ManyToOne
	private AddressDto addressDto;
	private LocalDateTime plannedTime;
	
	@ManyToMany
	Set<SectionDto> sectionDtos = new HashSet<>();
	
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

	public Set<SectionDto> getSectionDtos() {
		return sectionDtos;
	}

	public void setSectionDtos(Set<SectionDto> sectionDtos) {
		this.sectionDtos = sectionDtos;
	}
	
}
