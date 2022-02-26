package hu.lacztam.logistic.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.lacztam.logistic.model.Milestone;

public interface MilestoneRepository extends JpaRepository<Milestone, Long>, JpaSpecificationExecutor<Milestone> {
	
	@Query("SELECT m FROM Milestone m")
	public List<Milestone> getAllMileStones();
	
	@Query("SELECT m FROM Milestone m "
			+ "WHERE m.address.addressId = :addressId")
	public Milestone getMilestoneFromAddress(long addressId);
	
	@Query("SELECT s.fromMilestone FROM Section s "
			+ "WHERE s.fromMilestone.milestoneId = :milestoneId ")
	public Optional<Milestone> getFromMilestoneById(long milestoneId);
	
	@Query("SELECT s.toMilestone FROM Section s "
			+ "WHERE s.toMilestone.milestoneId = :milestoneId ")
	public Optional<Milestone> getToMilestoneById(long milestoneId);

}
