package hu.lacztam.logistic.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hu.lacztam.logistic.model.Section;
import hu.lacztam.logistic.repository.SectionRepository;

@Service
public class SectionService {

	@Autowired SectionRepository sectionRepository;

	@Transactional
	public Section crateSection(Section section1) {
		return sectionRepository.save(section1);
	}
	
	@Transactional
	public List<Section> getAllSections(){
		return sectionRepository.findAll();
	}
	
	@Transactional
	public List<Section> getEverySectionWithMilestones(){
		return sectionRepository.getEverySectionsWithMilestones();
	}
	
	@Transactional
	public Section sectionById(long id) {
		return sectionRepository.sectionById(id);
	}
	
//	@Transactional
//	public Section sectionByMilestoneId(long id) {
//		return sectionRepository.findByMilestoneId(id);
//	}
	
	@Transactional
	public Section sectionByFromMilestoneId(long milestoneId) {
		Optional<Section> section = sectionRepository.sectionByFromMilestone(milestoneId);
		if(section.isPresent())
			return section.get();
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@Transactional
	public Section sectionByToMilestoneId(long milestoneId) {
		Optional<Section> section =  sectionRepository.sectionByToMilestone(milestoneId);
		if(section.isPresent())
			return section.get();
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	@Transactional
	public Section saveSection(long sectionId) {
		return sectionRepository.save(sectionById(sectionId));
	}
	
	@Transactional
	public Section sectionBySectionNumber(int sectionNumber) {
		Optional<Section> section = sectionRepository.sectionByNumber(sectionNumber);
		if(section.isPresent())
			return section.get();
		else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
}
