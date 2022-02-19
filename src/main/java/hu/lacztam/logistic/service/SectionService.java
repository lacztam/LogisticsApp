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
	
	public List<Section> getAllSections(){
		return sectionRepository.findAll();
	}
}
