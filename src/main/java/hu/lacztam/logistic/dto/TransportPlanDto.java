package hu.lacztam.logistic.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import hu.lacztam.logistic.model.Milestone;

@NotNull
public class TransportPlanDto {
	
	private long transportId;
	private long income;
	
	@OneToMany(mappedBy = "transportPlanDto")
	List<MilestoneDto> milestoneDtos;
	
	@OneToMany(mappedBy = "transportPlanDto")
	List<SectionDto> sectionDtos;
	
	public TransportPlanDto() {
	}

	public void addMilestone(MilestoneDto milestoneDto) {
		if(this.milestoneDtos == null) 
			this.milestoneDtos = new ArrayList<>();
		this.milestoneDtos.add(milestoneDto);
		milestoneDto.setTransportPlanDto(this);
	}
	
	public long getTransportId() {
		return transportId;
	}

	public long getIncome() {
		return income;
	}

	public void setTransportId(long transportId) {
		this.transportId = transportId;
	}

	public void setIncome(long income) {
		this.income = income;
	}

	public List<MilestoneDto> getMilestoneDtos() {
		return milestoneDtos;
	}

	public void setMilestoneDtos(List<MilestoneDto> milestoneDtos) {
		this.milestoneDtos = milestoneDtos;
	}

	public List<SectionDto> getSectionDtos() {
		return sectionDtos;
	}

	public void setSectionDtos(List<SectionDto> sectionDtos) {
		this.sectionDtos = sectionDtos;
	}

}
