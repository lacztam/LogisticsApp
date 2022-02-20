package hu.lacztam.logistic.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.lacztam.logistic.dto.TransportDelayDto;
import hu.lacztam.logistic.model.TransportPlan;
import hu.lacztam.logistic.repository.TransportPlanRepository;

@Service
public class TransportPlanService {

	@Autowired TransportPlanRepository transportRepository;
	
	public TransportPlan addDelay(long transportId, TransportDelayDto transportDelayDto) {
		return null;
	}

	@Transactional
	public TransportPlan crateTransportPlan(TransportPlan transportPlan) {
		return transportRepository.save(transportPlan);
	}

	@Transactional
	public List<TransportPlan> findAll(){
		return transportRepository.findAll();
	}
	
	@Transactional
	public List<TransportPlan> getAllTransportPlansWithOutSections(){
		return transportRepository.getEveryTransportPlans();
	}
	
	@Transactional
	public List<TransportPlan> getAllTransportPlansWithSections(){
		return transportRepository.getEveryTransportWithSections();
	}
	
	@Transactional
	public TransportPlan getById(long id) {
		return transportRepository.getTransByIdJustBody(id);
	}
	
}
