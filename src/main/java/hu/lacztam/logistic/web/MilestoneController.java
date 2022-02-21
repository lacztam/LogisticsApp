package hu.lacztam.logistic.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping
	public List<MilestoneDto> getAllMilestones(){
		return milestoneMapper.milestonesToDtos(milestoneService.getAllMilestones());
	}
	
	@GetMapping("/withAddresses")
	public List<MilestoneDto> getAllMilestonesWithAddresses(){
		return milestoneMapper.milestonesToDtos(milestoneService.getAllMilestonesWithAddresses());
	}
	
	@GetMapping("/{milestoneId}")
	public MilestoneDto findById(@PathVariable long milestoneId) {
		return milestoneMapper.milestoneToDto(milestoneService.findById(milestoneId));
	}
	
	@PostMapping("/delay")
	public MilestoneDto addDelay(@RequestBody TransportDelayDto transportDelayDto) {
		Milestone milestone = milestoneService.addDelay(transportDelayDto);
		return milestoneMapper.milestoneToDto(milestone);
	}
	
}
