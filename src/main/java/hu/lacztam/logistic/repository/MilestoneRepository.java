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
}
