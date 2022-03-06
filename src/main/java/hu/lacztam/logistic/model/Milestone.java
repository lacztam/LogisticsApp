package hu.lacztam.logistic.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

	@ManyToOne
	TransportPlan transportPlan;
	
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

	public TransportPlan getTransportPlan() {
		return transportPlan;
	}

	public void setTransportPlan(TransportPlan transportPlan) {
		this.transportPlan = transportPlan;
	}

}
