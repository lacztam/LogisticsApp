package hu.lacztam.logistic.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class TransportPlan {

	@Id
	@GeneratedValue
	private long transportId;
	private long expectedIncome;
	private List<Section> sections;

	public TransportPlan() {
	}

	public long getTransportId() {
		return transportId;
	}

	public long getExpectedIncome() {
		return expectedIncome;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setTransportId(long transportId) {
		this.transportId = transportId;
	}

	public void setExpectedIncome(long expectedIncome) {
		this.expectedIncome = expectedIncome;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	
}
