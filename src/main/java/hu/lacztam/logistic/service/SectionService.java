package hu.lacztam.logistic.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.lacztam.logistic.dto.SectionDto;
import hu.lacztam.logistic.mapper.SectionMapper;
import hu.lacztam.logistic.model.Milestone;
import hu.lacztam.logistic.model.Section;
import hu.lacztam.logistic.repository.SectionRepository;

@Service
public class SectionService {

	@Autowired SectionRepository sectionRepository;
	@Autowired SectionMapper sectionMapper;
	@Autowired MilestoneService milestoneService;

	@Transactional
	public Section crateNormalSection(Section normalSection) {
		Optional<Section> section = sectionRepository.findById(normalSection.getSectionId());
		if(section.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		return sectionRepository.save(normalSection);
	}

	@Transactional
	public Section createSection(SectionDto sectionDto) {
		Optional<Section> section = sectionRepository.findById(sectionDto.getSectionDtoId());
		if(section.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		
		return sectionRepository.save(sectionMapper.dtoToSection(sectionDto));
	}
	@Transactional
	public List<Section> getAllSections(){
		return sectionRepository.findAll();
	}
	
	@Transactional
	public List<Section> getEverySectionWithQuery(){
		return sectionRepository.getEverySectionsQuery();
	}
	
	@Transactional
	public Section findSectionById(long id) {
		return sectionRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	public Section sectionByFromMilestoneId(long milestoneId) {
		return sectionRepository.sectionByFromMilestone(milestoneId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	public Section sectionByToMilestoneId(long milestoneId) {
		return sectionRepository.sectionByToMilestone(milestoneId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	public Section sectionBySectionNumber(int sectionNumber) {
		return sectionRepository.sectionByNumber(sectionNumber)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	public Section addToMilestone(long sectionId, long milestoneId) {
		Section section = findSectionById(sectionId);
		Milestone milestone = milestoneService.findById(milestoneId);
		milestoneService.addSectionToMilestone(milestone.getMilestoneId(), section.getSectionId());
		section.setToMilestone(milestone);
		return saveSection(section);
	}

	@Transactional
	public Section addFromMilestone(long sectionId, long milestoneId) {
		Section section = findSectionById(sectionId);
		Milestone milestone = milestoneService.findById(milestoneId);
		milestoneService.addSectionToMilestone(milestone.getMilestoneId(), section.getSectionId());
		section.setFromMilestone(milestone);
		return saveSection(section);
	}	
	
	@Transactional
	public Section saveSection(Section section) {
		return sectionRepository.save(section);
	}
}
