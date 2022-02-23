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
	
	@Query("SELECT m FROM Milestone m JOIN FETCH m.address")
	public List<Milestone> getAllMileStonesWithAddress();
	
	@Query("SELECT s.fromMilestone FROM Section s "
			+ "WHERE s.fromMilestone.milestoneId = :milestoneId ")
	public Optional<Milestone> getFromMilestoneById(long milestoneId);
	
	@Query("SELECT s.toMilestone FROM Section s "
			+ "WHERE s.toMilestone.milestoneId = :milestoneId ")
	public Optional<Milestone> getToMilestoneById(long milestoneId);

}
