package hu.lacztam.logistic.dto;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@NotNull
public class SectionDto {

	private long sectionDtoId;
	@ManyToOne
	private MilestoneDto fromMilestoneDto;
	@ManyToOne
	private MilestoneDto toMilestoneDto;
	private int number;
	
	@ManyToOne
	TransportPlanDto transportPlanDto;
	
	public SectionDto() {
	}

	public long getSectionDtoId() {
		return sectionDtoId;
	}

	public MilestoneDto getFromMilestoneDto() {
		return fromMilestoneDto;
	}

	public MilestoneDto getToMilestoneDto() {
		return toMilestoneDto;
	}

	public int getNumber() {
		return number;
	}

	public void setSectionDtoId(long sectionDtoId) {
		this.sectionDtoId = sectionDtoId;
	}

	public void setFromMilestoneDto(MilestoneDto fromMilestoneDto) {
		this.fromMilestoneDto = fromMilestoneDto;
	}

	public void setToMilestoneDto(MilestoneDto toMilestoneDto) {
		this.toMilestoneDto = toMilestoneDto;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public TransportPlanDto getTransportPlanDto() {
		return transportPlanDto;
	}

	public void setTransportPlanDto(TransportPlanDto transportPlanDto) {
		this.transportPlanDto = transportPlanDto;
	}

}
