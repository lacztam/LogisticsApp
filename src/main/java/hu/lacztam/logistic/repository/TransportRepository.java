package hu.lacztam.logistic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import hu.lacztam.logistic.model.Address;
import hu.lacztam.logistic.model.Milestone;
import hu.lacztam.logistic.model.Section;
import hu.lacztam.logistic.model.TransportPlan;


public interface TransportRepository extends JpaRepository<TransportPlan, Long>, JpaSpecificationExecutor<TransportPlan> {

}
