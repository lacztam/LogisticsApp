package hu.lacztam.logistic.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.lacztam.logistic.model.Milestone;
import hu.lacztam.logistic.repository.MilestoneRepository;

@Service
public class MilestoneService {

	@Autowired MilestoneRepository milestoneRepository;

	@Transactional
	public Milestone createMilestone(Milestone milestone1) {
		return milestoneRepository.save(milestone1);
	}
	
	public List<Milestone> getAllMilestones(){
		return milestoneRepository.findAll();
	}
	
}
