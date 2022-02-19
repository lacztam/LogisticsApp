//package hu.lacztam.logistic.model;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//
//@Entity
//public class TransportPlan {
//
//	@Id
//	@GeneratedValue
//	private long transportId;
//	private long expectedIncome;
//	
//	@OneToMany
//	private Set<Section> sections = new HashSet<>();
//
//	public TransportPlan() {
//	}
//
//	public long getTransportId() {
//		return transportId;
//	}
//
//	public long getExpectedIncome() {
//		return expectedIncome;
//	}
//
//	public Set<Section> getSections() {
//		return sections;
//	}
//
//	public void setTransportId(long transportId) {
//		this.transportId = transportId;
//	}
//
//	public void setExpectedIncome(long expectedIncome) {
//		this.expectedIncome = expectedIncome;
//	}
//
//	public void setSections(Set<Section> sections) {
//		this.sections = sections;
//	}
//	
//	public void addSection(Section section) {
//		sections.add(section);
//		section.setTransportPlan(this);
//	}
//	
//}
