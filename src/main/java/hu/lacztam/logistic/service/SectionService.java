package hu.lacztam.logistic.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return sectionRepository.sectionByFromMilestone(milestoneId);
	}
	
	@Transactional
	public Section sectionByTomilestoneId(long milestoneId) {
		return sectionRepository.sectionByToMilestone(milestoneId);
	}
	
	@Transactional
	public Section saveSection(long sectionId) {
		return sectionRepository.save(sectionById(sectionId));
	}
	
	@Transactional
	public Section sectionBySectionNumber(int sectionNumber) {
		return sectionRepository.sectionByNumber(sectionNumber);
	}
}
