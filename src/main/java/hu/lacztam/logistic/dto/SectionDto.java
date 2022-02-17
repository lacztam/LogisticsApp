package hu.lacztam.logistic.dto;


public class SectionDto {

	private long sectionDtoId;
	private MilestoneDto fromMilestoneDto;
	private MilestoneDto toMilestoneDto;
	private int number;
	
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

}
