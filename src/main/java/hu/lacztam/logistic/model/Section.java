package hu.lacztam.logistic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Section {

	@Id
	@GeneratedValue
	private long sectionId;
	@ManyToOne
	private Milestone fromMilestone;
	@ManyToOne
	private Milestone toMilestone;
	private int number;
	
	public Section() {
	}

	public long getSectionId() {
		return sectionId;
	}

	public Milestone getFromMilestone() {
		return fromMilestone;
	}

	public Milestone getToMilestone() {
		return toMilestone;
	}

	public int getNumber() {
		return number;
	}

	public void setSectionId(long sectionId) {
		this.sectionId = sectionId;
	}

	public void setFromMilestone(Milestone fromMilestone) {
		this.fromMilestone = fromMilestone;
	}

	public void setToMilestone(Milestone toMilestone) {
		this.toMilestone = toMilestone;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}
