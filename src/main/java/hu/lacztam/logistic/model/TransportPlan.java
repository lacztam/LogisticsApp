package hu.lacztam.logistic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class TransportPlan {

	@Id
	@GeneratedValue
	private long transportId;
	private long expectedIncome;
	
	@OneToMany
	private List<Section> sections;

	public TransportPlan() {
	}

	public void addSection(Section section) {
		if(this.sections == null)
			this.sections = new ArrayList<>();
		sections.add(section);
		section.setTransportPlan(this);
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

	@Override
	public String toString() {
		return "TransportPlan [transportId=" + transportId + ", expectedIncome=" + expectedIncome + ", sections="
				+ sections + "]";
	}
}
