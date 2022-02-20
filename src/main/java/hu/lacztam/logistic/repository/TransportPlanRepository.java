package hu.lacztam.logistic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import hu.lacztam.logistic.model.Address;
import hu.lacztam.logistic.model.Milestone;
import hu.lacztam.logistic.model.Section;
import hu.lacztam.logistic.model.TransportPlan;


public interface TransportPlanRepository extends JpaRepository<TransportPlan, Long>, JpaSpecificationExecutor<TransportPlan> {

	@Query("SELECT t FROM TransportPlan t")
	List<TransportPlan> getEveryTransportPlans();
	
	@Query("SELECT t FROM TransportPlan t WHERE t.transportId = :id")
	TransportPlan getTransByIdJustBody(long id);

//	@Query("SELECT t FROM TransportPlan t JOIN FETCH t.sections")
//	@EntityGraph(attributePaths = 
//		{ "sections", "sections.fromMilestone", "sections.toMilestone" })

	@EntityGraph(attributePaths = 
		{ "sections", "sections.fromMilestone", "sections.toMilestone",
				"sections.fromMilestone.address", "sections.toMilestone.address"})
	@Query("SELECT t FROM TransportPlan t JOIN FETCH t.sections")
	List<TransportPlan> getEveryTransportWithSections();
}
