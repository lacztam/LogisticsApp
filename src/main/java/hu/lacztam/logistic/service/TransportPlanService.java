package hu.lacztam.logistic.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.lacztam.logistic.config.ConfigProperties;
import hu.lacztam.logistic.dto.TransportDelayDto;
import hu.lacztam.logistic.dto.TransportPlanDto;
import hu.lacztam.logistic.mapper.TransportMapper;
import hu.lacztam.logistic.model.Milestone;
import hu.lacztam.logistic.model.Section;
import hu.lacztam.logistic.model.TransportPlan;
import hu.lacztam.logistic.repository.TransportPlanRepository;

@Service
public class TransportPlanService {

	@Autowired TransportPlanRepository transportRepository;
	@Autowired MilestoneService milestoneService;
	@Autowired SectionService sectionService;
	@Autowired ConfigProperties config;
	@Autowired TransportMapper transportMapper;

	
	/*----------------------------Vizsgafeladat-részeihez-tartozó-függvények----------------------------*/
	
//	@Transactional
//	public TransportPlan addDelay(long transportId, TransportDelayDto transportDelayDto) {
//		Long milestoneId = transportDelayDto.getMilestoneDtoId();
//		Long delayInMinutes = transportDelayDto.getDelayInMinutes();
//		
//		TransportPlan transport = getWithSectionsById(transportId);
//		checkingExistingMilestones(transport, milestoneId);
//		
//		Optional<Section> sectionWithFromMilestone 
//			= transport.getSections()
//				.stream()
//				.filter(m -> m.getFromMilestone().getMilestoneId() == milestoneId)
//				.findFirst();
//		Optional<Section> sectionWithToMilestone 
//			= transport.getSections()
//				.stream()
//				.filter(m -> m.getToMilestone().getMilestoneId() == milestoneId)
//				.findFirst();
//		
//		if(sectionWithToMilestone.isEmpty()) {
//			int sectionNumber = sectionWithFromMilestone.get().getNumber();
//			
//			transport.getSections()
//				.stream()
//				.filter(s -> s.getNumber() == sectionNumber)
//				.findFirst().get()
//				.getFromMilestone()
//				.addDelay(delayInMinutes);
//			
//			transport.getSections()
//				.stream()
//				.filter(s -> s.getNumber() == sectionNumber)
//				.findFirst().get()
//				.getToMilestone()
//				.addDelay(delayInMinutes);
//			
//		}else if(sectionWithFromMilestone.isEmpty()) {
//			Long nextSectionNumber = (long)sectionWithToMilestone.get().getNumber() + 1;
//			if(nextSectionNumber != null){
//				transport.getSections()
//				.stream()
//				.filter(s -> s.getNumber() == nextSectionNumber)
//				.findFirst().get()
//				.getFromMilestone().addDelay(delayInMinutes);
//			}
//		}
//		
//		transport.setIncome(calculateIncomeByDelay(transport.getIncome(), delayInMinutes));
//		
//		return transportRepository.save(transport);
//	}
	
//	private void checkingExistingMilestones(TransportPlan transportPlan, long milestoneId) {
//	boolean isPresentFromMilestone = isPresentFromMilestone(transportPlan, milestoneId);
//	boolean isPresentToMilestone = isPresentToMilestone(transportPlan, milestoneId);
//	
//	if(!isPresentFromMilestone && !isPresentToMilestone)
//		throw new ResponseStatusException(HttpStatus.BAD_REQUEST); 
//}
//
//private boolean isPresentFromMilestone(TransportPlan transportPlan, long milestoneId){
//	return transportPlan.getSections()
//		.stream()
//		.anyMatch(m -> m.getFromMilestone().getMilestoneId() == milestoneId);
//}
//
//private boolean isPresentToMilestone(TransportPlan transportPlan, long milestoneId){
//	return transportPlan.getSections()
//		.stream()
//		.anyMatch(m -> m.getToMilestone().getMilestoneId() == milestoneId);
//}
	
	
	@Transactional
	public TransportPlan addDelay(long transportId, TransportDelayDto transportDelayDto) {
		Long milestoneId = transportDelayDto.getMilestoneDtoId();
		Long delayInMinutes = transportDelayDto.getDelayInMinutes();

		TransportPlan transport = getWithSectionsById(transportId);
		checkingExistingMilestones(transport, milestoneId);

		Optional<Milestone> fromMilestone = milestoneService.getFromMilestoneByIdOptional(milestoneId);
		Optional<Milestone> toMilestone = milestoneService.getToMilestoneByIdOptional(milestoneId);
		
		if(toMilestone.isEmpty()) {
			
			Milestone thisSectionFromMilestone = fromMilestone.get();
			thisSectionFromMilestone.addDelay(delayInMinutes);
			
			Milestone thisSectionToMilestone 
				= milestoneService.getToMilestoneFromMilestone(thisSectionFromMilestone);
			thisSectionToMilestone.addDelay(delayInMinutes);
			
			milestoneService.saveMilestone(thisSectionFromMilestone);
			milestoneService.saveMilestone(thisSectionToMilestone);
			
		}else if(fromMilestone.isEmpty()) {
			
			Milestone previousSectionToMilestone = toMilestone.get();
			Milestone nextSectionFromMilestone 
				= milestoneService.fromMilestoneByPreviousSectionToMilestone(previousSectionToMilestone.getMilestoneId());
			nextSectionFromMilestone.addDelay(delayInMinutes);
			
			milestoneService.saveMilestone(nextSectionFromMilestone);
		
		}
		
		transport.setIncome(calculateIncomeByDelay(transport.getIncome(), delayInMinutes));
		
		return transportRepository.save(transport);
	}
	

	private void checkingExistingMilestones(TransportPlan transportPlan, long milestoneId) {
		Milestone milestone = milestoneService.findById(milestoneId);
		
		long transportPlanIdByMilestone = milestone.getTransportPlan().getTransportId(); 
		long transportPlanId = transportPlan.getTransportId();
		
		if(transportPlanId != transportPlanIdByMilestone)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	}
	
	
	private long calculateIncomeByDelay(long income, long minutes) {
		
		if(minutes >= 120) 
			income = (long)(((100.0 - (double)config.getPenalty().getPercent3()) / 100.0) * (double)income);
		else if(minutes >= 60) 
			income = (long)(((100.0 - (double)config.getPenalty().getPercent2()) / 100.0) * (double)income);
		else if(minutes >= 30) 
			income = (long)(((100.0 - (double)config.getPenalty().getPercent1()) / 100.0) * (double)income);
		
		return income;
	}


	/*----------------------------Egyéb-függvények----------------------------*/
	
	@Transactional
	public TransportPlan saveTransportPlan(TransportPlan transportPlan) {
		Optional<TransportPlan> transportPlanOptional = transportRepository.findById(transportPlan.getTransportId());
		if(transportPlanOptional.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST); 
		else
			return transportRepository.save(transportPlan);
	}

	@Transactional
	public List<TransportPlan> getAllWithSections(){
		return transportRepository.everyTransportPlansWithSections();
	}
	
	@Transactional
	public TransportPlan getWithSectionsById(long id) {
		TransportPlan transport = transportRepository.getTransportPlanWithSectionsById(id);
		if(transport != null)
			return transport;
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@Transactional
	public TransportPlan createTransportPlan(TransportPlanDto transPlanDto) {
		Optional<TransportPlan> transport 
			= transportRepository.findById(transPlanDto.getTransportId());
		if(transport.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST); 
		return transportRepository.save(transportMapper.dtoToTransport(transPlanDto));
	}

	@Transactional
	public TransportPlan addSection(long transportId, long sectionId) {
		TransportPlan transport 
			= transportRepository.findById(transportId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		Section section = sectionService.findSectionById(sectionId);
		section.setTransportPlan(transport);
		sectionService.saveSection(section);
		
		transport.addSection(section);
		
		return transportRepository.save(transport);
	}
}
