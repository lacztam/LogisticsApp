package hu.lacztam.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.lacztam.logistic.model.Address;
import hu.lacztam.logistic.model.Milestone;

public interface MilestoneRepository extends JpaRepository<Milestone, Long>, JpaSpecificationExecutor<Milestone> {

}
