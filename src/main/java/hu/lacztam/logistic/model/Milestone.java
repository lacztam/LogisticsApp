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
	
	@ManyToMany
	Set<Section> sections = new HashSet<>();
	
	private LocalDateTime plannedTime;

	public Milestone() {
	}

	public long getMilestoneId() {
		return milestoneId;
	}

	public Address getAddress() {
		return address;
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

	public void setPlannedTime(LocalDateTime plannedTime) {
		this.plannedTime = plannedTime;
	}

	public Set<Section> getSections() {
		return sections;
	}

	public void setSections(Set<Section> sections) {
		this.sections = sections;
	}
	
}
