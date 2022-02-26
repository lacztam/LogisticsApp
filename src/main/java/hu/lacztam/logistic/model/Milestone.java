package hu.lacztam.logistic.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Milestone {
	
	@Id
	@GeneratedValue
	private long milestoneId;
	
	@OneToOne
	private Address address;
	
	@ManyToOne(targetEntity = Section.class)
	Section section;
	
	private LocalDateTime plannedTime;

	public Milestone() {
	}

	public void addDelay(long minutes) {
		this.plannedTime = this.plannedTime.plusMinutes(minutes);
	}
	
	public long getMilestoneId() {
		return milestoneId;
	}

	public Address getAddress() {
		return address;
	}

	public Section getSection() {
		return section;
	}

	public LocalDateTime getPlannedTime() {
		return plannedTime;
	}

	public void setMilestoneId(long milestoneId) {
		this.milestoneId = milestoneId;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

	@Override
	public String toString() {
		return "\n\nMilestone\n[milestoneId=" + milestoneId + ", address=" + address + ", section=" + section
				+ ", plannedTime=" + plannedTime + "]";
	}
}
