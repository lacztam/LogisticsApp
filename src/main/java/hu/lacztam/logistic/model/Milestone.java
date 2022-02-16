package hu.lacztam.logistic.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Milestone {
	
	@Id
	@GeneratedValue
	private long milestoneId;
	private Address address;
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
	
}
