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
}
