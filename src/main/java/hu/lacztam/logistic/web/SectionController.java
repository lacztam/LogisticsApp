package hu.lacztam.logistic.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.lacztam.logistic.dto.SectionDto;
import hu.lacztam.logistic.mapper.SectionMapper;
import hu.lacztam.logistic.model.Section;
import hu.lacztam.logistic.service.SectionService;

@RestController
@RequestMapping("/api/sections")
public class SectionController {
	
	@Autowired SectionService sectionService;
	@Autowired SectionMapper sectionMapper;
	
	@GetMapping
	public List<SectionDto> getAllSections(){
		return sectionMapper.sectionsToDtos(sectionService.getAllSections());
	}
	
	@GetMapping("/withMilestones")
	public List<SectionDto> getAllSectionsWithMilestones(){
		return sectionMapper.sectionsToDtos(sectionService.getEverySectionWithMilestones());
	}
	
	@GetMapping("/withMilestones/{id}")
	public SectionDto getSectionById(@PathVariable long id){
		Section sectionById = sectionService.sectionById(id);
		return sectionMapper.sectionToDto(sectionById);
	}
}
