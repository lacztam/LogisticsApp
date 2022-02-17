package hu.lacztam.logistic.dto;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class TransportPlanDto {

	private long transportId;
	private long expectedIncome;
	private List<SectionDto> sectionDtos;

	public TransportPlanDto() {
	}

	public long getTransportId() {
		return transportId;
	}

	public long getExpectedIncome() {
		return expectedIncome;
	}

	public List<SectionDto> getSectionDtos() {
		return sectionDtos;
	}

	public void setTransportId(long transportId) {
		this.transportId = transportId;
	}

	public void setExpectedIncome(long expectedIncome) {
		this.expectedIncome = expectedIncome;
	}

	public void setSectionDtos(List<SectionDto> sectionDtos) {
		this.sectionDtos = sectionDtos;
	}

}
