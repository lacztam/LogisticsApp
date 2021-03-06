package hu.lacztam.logistic.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.lacztam.logistic.config.ConfigProperties;
import hu.lacztam.logistic.dto.MilestoneDto;
import hu.lacztam.logistic.dto.TransportDelayDto;
import hu.lacztam.logistic.mapper.MilestoneMapper;
import hu.lacztam.logistic.model.Milestone;
import hu.lacztam.logistic.service.MilestoneService;

@RestController
@RequestMapping("/api/milestones")
public class MilestoneController {

	@Autowired MilestoneService milestoneService;
	@Autowired MilestoneMapper milestoneMapper;
	@Autowired ConfigProperties configProperties;
	
	
	@GetMapping
	public List<MilestoneDto> getAllMilestones(){
		return milestoneMapper.milestonesToDtos(milestoneService.getAllMilestonesWithAddresses());
	}
	
	@GetMapping("/{milestoneId}")
	public MilestoneDto findById(@PathVariable long milestoneId) {
		return milestoneMapper.milestoneToDto(milestoneService.findById(milestoneId));
	}
	
	@GetMapping("/getAll")
	public List<MilestoneDto> getAll(){
		List<Milestone> everyMilestones = milestoneService.getAllMilestonesQuery();
		return milestoneMapper.milestonesToDtos(everyMilestones);
	}
	
	@PostMapping("/delay")
	public MilestoneDto addDelay(@RequestBody TransportDelayDto transportDelayDto) {
		Milestone milestone = milestoneService.addDelay(transportDelayDto);
		return milestoneMapper.milestoneToDto(milestone);
	}
	
	@GetMapping("/getFromMilestone/{milestoneId}")
	public MilestoneDto getFromMilestoneById(@PathVariable long milestoneId) {
		Milestone milestone = milestoneService.getFromMilestoneById(milestoneId);
		return milestoneMapper.milestoneToDto(milestone);
	}
	
	@GetMapping("/getToMilestone/{milestoneId}")
	public MilestoneDto getToMilestoneById(@PathVariable long milestoneId) {
		Milestone milestone = milestoneService.getToMilestoneById(milestoneId);
		return milestoneMapper.milestoneToDto(milestone);
	}
	
	@PostMapping
	public MilestoneDto createMilestone(@RequestBody MilestoneDto milestoneDto) {
		Milestone milestone = milestoneService.createMilestone(milestoneMapper.dtoTomilestone(milestoneDto));
		return milestoneMapper.milestoneToDto(milestone);
	}
	
	@PutMapping("/{milestoneId}/addAddress/{addressId}")
	public MilestoneDto addAddressToMilestone(@PathVariable long milestoneId, @PathVariable long addressId) {
		Milestone milestone = milestoneService.addAddressToMilestone(milestoneId, addressId);
		return milestoneMapper.milestoneToDto(milestone);
	}
	
	@PutMapping("/{milestoneId}/addSectionToMilestone/{sectionId}")
	public MilestoneDto addSectionToMilestone(
			@PathVariable long milestoneId,
			@PathVariable long sectionId) {
			Milestone milestone = milestoneService.addSectionToMilestone(milestoneId, sectionId);
		return milestoneMapper.milestoneToDto(milestone);
	}
	
	@GetMapping("/getFromMilestone/{toMilestoneId}/toMilestoneId/{transportId}/transportId")
	public MilestoneDto spec1(@PathVariable long toMilestoneId, @PathVariable long transportId) {
		Milestone milestone = milestoneService.fromMilestoneByPreviousSectionToMilestone(toMilestoneId, transportId);
		return milestoneMapper.milestoneToDto(milestone);
	}
}
