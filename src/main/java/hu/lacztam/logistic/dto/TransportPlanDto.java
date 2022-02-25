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
