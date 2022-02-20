package hu.lacztam.logistic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import hu.lacztam.logistic.model.Address;
import hu.lacztam.logistic.model.Milestone;
import hu.lacztam.logistic.model.Section;

public interface SectionRepository extends JpaRepository<Section, Long>, JpaSpecificationExecutor<Section> {

	@Query("SELECT s FROM Section s")
	public List<Section> getEverySectionsWithMilestones();
	
	@Query("SELECT s FROM Section s WHERE s.sectionId = :id")
	public Section sectionById(long id);
}
