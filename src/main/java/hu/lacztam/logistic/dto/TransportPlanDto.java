package hu.lacztam.logistic.dto;

import java.util.List;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@NotNull
public class TransportPlanDto {
	
	private long transportId;
	private long income;
	
	@OneToMany
	private List<SectionDto> sectionDtos;
	
	public TransportPlanDto() {
	}

	public long getTransportId() {
		return transportId;
	}

	public long getIncome() {
		return income;
	}

	public List<SectionDto> getSectionDtos() {
		return sectionDtos;
	}

	public void setTransportId(long transportId) {
		this.transportId = transportId;
	}

	public void setIncome(long income) {
		this.income = income;
	}

	public void setSectionDtos(List<SectionDto> sectionDtos) {
		this.sectionDtos = sectionDtos;
	}

	@Override
	public String toString() {
		return "\n\nTransportPlanDto\n[transportId=" + transportId + ", income=" + income + ", sectionDtos=" + sectionDtos
				+ "]";
	}
}
