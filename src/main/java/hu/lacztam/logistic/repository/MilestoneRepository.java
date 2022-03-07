package hu.lacztam.logistic.repository;

import java.util.List;
import java.util.Optional;

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
	
	@Query("SELECT s.toMilestone FROM Section s "
			+ "WHERE s.fromMilestone.milestoneId = :milestoneId ")
	public Optional<Milestone> getToMilestoneByFromMilestoneId(long milestoneId);

//	@EntityGraph(attributePaths = 
//		{ "sections", "sections.fromMilestone", 
//				"sections.toMilestone", "sections.toMilestone.milestoneId" })
//	@Query("SELECT s.fromMilestone FROM TransportPlan t JOIN FETCH t.sections s "
//			+ "WHERE s.number = ( "
//				+ 	"SELECT st.number + 1 "
//				+ 	"FROM Section st "
//				+ 	"WHERE st.toMilestone.milestoneId = :toMilestoneId "
//				+ 	"AND st.transportPlan.transportId = :transportId )" )
//	public Optional<Milestone> getFromMilestoneByPreviousSectionToMilestone(long toMilestoneId, long transportId);


	@Query(value = "SELECT s.from_milestone_milestone_id "
			+ "FROM transport_plan t "
			+ "LEFT join section s on s.transport_plan_transport_id = t.transport_id "
			+ "WHERE s.number IN "
			+ "	( "
			+ "	SELECT st.number + 1 "
			+ "		FROM section st "
			+ "		WHERE to_milestone_milestone_id = :toMilestoneId "
			+ " 	AND t.transport_id = :transportId "
			+ "	) ", nativeQuery = true)
	public long getFromMilestoneByPreviousSectionToMilestone(long toMilestoneId, long transportId);
}
