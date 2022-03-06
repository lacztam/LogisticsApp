package hu.lacztam.logistic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class TransportPlan {
	
	@Id
	@GeneratedValue
	private long transportId;
	private long income;

	@OneToMany(mappedBy = "transportPlan")
	List<Section> sections;
	
	@OneToMany(mappedBy = "transportPlan")
	List<Milestone> milestones;

	public TransportPlan() {
	}

	public void addMilestone(Milestone milestone) {
		if(this.milestones == null) 
			this.milestones = new ArrayList<>();
		
		this.milestones.add(milestone);
		milestone.setTransportPlan(this);
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

	public List<Milestone> getMilestones() {
		return milestones;
	}

	public void setMilestones(List<Milestone> milestones) {
		this.milestones = milestones;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public void addSection(Section section) {
		if(this.sections == null)
			this.sections = new ArrayList<>();
		sections.add(section);
		section.setTransportPlan(this);
		
	}

}
