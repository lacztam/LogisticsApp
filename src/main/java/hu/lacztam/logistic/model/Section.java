package hu.lacztam.logistic.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Section {

	@Id
	@GeneratedValue
	private long sectionId;
	private Milestone fromMilestone;
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
