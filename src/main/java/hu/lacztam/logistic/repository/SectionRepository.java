package hu.lacztam.logistic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.lacztam.logistic.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long>, JpaSpecificationExecutor<Section> {

	@Query("SELECT s FROM Section s")
	public List<Section> getEverySectionsWithMilestones();
	
	@Query("SELECT s FROM Section s WHERE s.sectionId = :id")
	public Section sectionById(long id);

	@Query("SELECT s FROM Section s "
			+ "WHERE s.fromMilestone.milestoneId = :milestoneId ")
	public Optional<Section> sectionByFromMilestone(long milestoneId);
	
	@Query("SELECT s FROM Section s "
			+ "WHERE s.toMilestone.milestoneId = :milestoneId ")
	public Optional<Section> sectionByToMilestone(long milestoneId);
	
	@Query("SELECT s FROM Section s "
			+ "WHERE s.number = :sectionNumber")
	public Optional<Section> sectionByNumber(int sectionNumber);
	
}
