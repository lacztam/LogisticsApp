package hu.lacztam.logistic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.lacztam.logistic.dto.TransportDelayDto;
import hu.lacztam.logistic.model.Milestone;
import hu.lacztam.logistic.model.Section;
import hu.lacztam.logistic.model.TransportPlan;
import hu.lacztam.logistic.repository.MilestoneRepository;
import hu.lacztam.logistic.repository.TransportPlanRepository;

@Service
public class TransportPlanService {

	@Autowired TransportPlanRepository transportRepository;
	@Autowired MilestoneService milestoneService;
	@Autowired SectionService sectionService;

	@Transactional
	public TransportPlan addDelay(long transportId, TransportDelayDto transportDelayDto) {
		Long milestoneId = transportDelayDto.getMilestoneDtoId();
		Long delayInMinutes = transportDelayDto.getDelayInMinutes();
		
		TransportPlan transport = getWithSectionsById(transportId);
		checkingExistingMilestone(transport, milestoneId);
		
		Optional<Milestone> fromMilestone = milestoneService.getFromMilestoneById(milestoneId);
		Optional<Milestone> toMilestone = milestoneService.getToMilestoneById(milestoneId);
		
		if(toMilestone.isEmpty()) {
			Section section = sectionService.sectionByFromMilestoneId(milestoneId);
			section.getFromMilestone().addDelay(delayInMinutes);
			section.getToMilestone().addDelay(delayInMinutes);
			sectionService.saveSection(section.getSectionId());
			
		}else if(fromMilestone.isEmpty()) {
			int nextSectionNumber 
				= sectionService.sectionByToMilestoneId(milestoneId).getNumber() + 1;
			Section section = sectionService.sectionBySectionNumber(nextSectionNumber);
			section.getFromMilestone().addDelay(delayInMinutes);
			sectionService.saveSection(section.getSectionId());
		}
		
//		transportRepository.save(transport);
		return transport;
	}
	
	private void checkingExistingMilestone(TransportPlan transportPlan, long milestoneId) {
		boolean isPresentFromMilestone = isPresentFromMilestone(transportPlan, milestoneId);
		boolean isPresentToMilestone = isPresentToMilestone(transportPlan, milestoneId);
		
		if(!isPresentFromMilestone && !isPresentToMilestone)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST); 
	}
	
	private boolean isPresentFromMilestone(TransportPlan transportPlan, long milestoneId){
		return transportPlan.getSections()
			.stream()
			.anyMatch(m -> m.getFromMilestone().getMilestoneId() == milestoneId);
	}
	
	private boolean isPresentToMilestone(TransportPlan transportPlan, long milestoneId){
		return transportPlan.getSections()
			.stream()
			.anyMatch(m -> m.getToMilestone().getMilestoneId() == milestoneId);
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
	public List<TransportPlan> getAllTransportPlansWithSections(){
		return transportRepository.getEveryTransportWithSections();
	}
	
	@Transactional
	public TransportPlan getById(long id) {
		return transportRepository.getTransByIdJustBody(id);
	}

	@Transactional
	public TransportPlan getWithSectionsById(long id) {
		TransportPlan transport = transportRepository.getTransportWithSectionsById(id);
		if(transport != null)
			return transport;
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@Transactional
	public TransportPlan findById(long id) {
		Optional<TransportPlan> transportPlan = transportRepository.findById(id);
		if(transportPlan.isPresent())
			return transportPlan.get();
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
}
