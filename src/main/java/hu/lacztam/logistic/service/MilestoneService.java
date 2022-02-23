package hu.lacztam.logistic.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.lacztam.logistic.dto.TransportDelayDto;
import hu.lacztam.logistic.model.Milestone;
import hu.lacztam.logistic.repository.MilestoneRepository;

@Service
public class MilestoneService {

	@Autowired MilestoneRepository milestoneRepository;

	@Transactional
	public Milestone createMilestone(Milestone milestone1) {
		return milestoneRepository.save(milestone1);
	}
	
	@Transactional
	public List<Milestone> getAllMilestones(){
		return milestoneRepository.findAll();
	}
	
	@Transactional
	public List<Milestone> getAllMilestonesWithAddresses(){
		return milestoneRepository.getAllMileStonesWithAddress();
	}
	
	@Transactional
	public Milestone findById(long id) {
		Optional<Milestone> milestone = milestoneRepository.findById(id);
		if(milestone.isPresent())
			return milestone.get();
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@Transactional
	public Milestone addDelay(TransportDelayDto transportDelayDto) {
		Milestone milestone = findById(transportDelayDto.getMilestoneDtoId());
		milestone.addDelay(transportDelayDto.getDelayInMinutes());
		return milestoneRepository.save(milestone);
	}

	@Transactional
	public Milestone saveMilestone(Milestone milestone) {
		return milestoneRepository.save(milestone);
	}
	
	@Transactional
	public Optional<Milestone> getFromMilestoneById(long milestoneId) {
		return milestoneRepository.getFromMilestoneById(milestoneId);
	}
	
	@Transactional
	public Optional<Milestone> getToMilestoneById(long milestoneId) {
		return milestoneRepository.getToMilestoneById(milestoneId);
	}
}
