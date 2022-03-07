package hu.lacztam.logistic.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.lacztam.logistic.dto.TransportDelayDto;
import hu.lacztam.logistic.mapper.AddressMapper;
import hu.lacztam.logistic.model.Address;
import hu.lacztam.logistic.model.Milestone;
import hu.lacztam.logistic.model.Section;
import hu.lacztam.logistic.repository.MilestoneRepository;

@Service
public class MilestoneService {

	@Autowired MilestoneRepository milestoneRepository;
	@Autowired AddressMapper addressMapper;
	@Autowired AddressService addressService;
	@Autowired SectionService sectionService;

	@Transactional
	public Milestone createMilestone(Milestone milestone) {
		
		Optional<Milestone> checkIfMilestoneIsExist 
				= milestoneRepository.findById(milestone.getMilestoneId());
		
		if(checkIfMilestoneIsExist.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST); 
		
		return saveMilestone(milestone);
	}
	
	@Transactional
	public List<Milestone> findAllMilestones(){
		return milestoneRepository.findAll();
	}
	
	@Transactional
	public List<Milestone> getAllMilestonesWithAddresses(){
		return milestoneRepository.getAllMileStones();
	}
	
	@Transactional
	public Milestone findById(long id) {
		return milestoneRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	public Milestone findByAddressId(long addressId) {
		return milestoneRepository.getMilestoneFromAddress(addressId);
	}
	
	@Transactional
	public List<Milestone> getAllMilestonesQuery() {
		return milestoneRepository.getAllMileStones();
	}

	@Transactional
	public Milestone addDelay(TransportDelayDto transportDelayDto) {
		Milestone milestone = findById(transportDelayDto.getMilestoneDtoId());
		milestone.addDelay(transportDelayDto.getDelayInMinutes());
		return saveMilestone(milestone);
	}

	@Transactional
	public Milestone saveMilestone(Milestone milestone) {
		return milestoneRepository.save(milestone);
	}
	
	@Transactional
	public Milestone getFromMilestoneById(long milestoneId) {
		return milestoneRepository.getFromMilestoneById(milestoneId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	public Optional<Milestone> getFromMilestoneByIdOptional(long milestoneId) {
		return milestoneRepository.getFromMilestoneById(milestoneId);
	}
	
	@Transactional
	public Milestone getToMilestoneFromMilestone(Milestone fromMilestone) {
		long fromMilestoneId = fromMilestone.getMilestoneId();
		
		return milestoneRepository.getToMilestoneByFromMilestoneId(fromMilestoneId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	public Milestone fromMilestoneByPreviousSectionToMilestone(long toMilestoneId, long transportId) {
		long id = milestoneRepository.getFromMilestoneByPreviousSectionToMilestone(toMilestoneId, transportId);
		return findById(id);
	}
	
	@Transactional
	public Milestone getToMilestoneById(long milestoneId) {
		return milestoneRepository.getToMilestoneById(milestoneId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	public Optional<Milestone> getToMilestoneByIdOptional(long milestoneId) {
		return milestoneRepository.getToMilestoneById(milestoneId);
	}
	
	@Transactional
	public Milestone addAddressToMilestone(long milestoneId, long addressId) {
		
		Milestone milestone = findById(milestoneId);
		Address address = addressService.findById(addressId);
		
		addressService.addMilestoneToAddress(address.getAddressId(), milestoneId);
		milestone.setAddress(address);

		return saveMilestone(milestone);
	}
	
	@Transactional
	public Milestone addSectionToMilestone(long milestoneId, long sectionId) {
		
		Milestone milestone = findById(milestoneId);
		Section section = sectionService.findSectionById(sectionId);
		
		if(section.getFromMilestone() == null) {
			section.setFromMilestone(milestone);
			section = sectionService.saveSection(section);
//			milestone.setSection(section);
		}
		else if(section.getToMilestone() == null) {
			section.setToMilestone(milestone);
			section = sectionService.saveSection(section);	
//			milestone.setSection(section);
		}else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		return saveMilestone(milestone);
	}
	
}
