package hu.lacztam.logistic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.lacztam.logistic.model.Address;
import hu.lacztam.logistic.model.Milestone;

public interface MilestoneRepository extends JpaRepository<Milestone, Long>, JpaSpecificationExecutor<Milestone> {
	
	@Query("SELECT m FROM Milestone m JOIN FETCH m.address")
	List<Milestone> getAllMileStonesWithAddress();
	
	@Query("SELECT s.fromMilestone FROM Section s "
			+ "WHERE s.fromMilestone.milestoneId = :id ")
	Milestone getSectionsFromMilestoneByMilestoneId(long id);
	
	@Query("SELECT s.toMilestone FROM Section s "
			+ "WHERE s.toMilestone.milestoneId = :id ")
	Milestone getSectionsToMilestoneByMilestoneId(long id);
}
