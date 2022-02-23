package hu.lacztam.logistic.web;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.lacztam.logistic.dto.TransportDelayDto;
import hu.lacztam.logistic.dto.TransportPlanDto;
import hu.lacztam.logistic.mapper.TransportMapper;
import hu.lacztam.logistic.model.Section;
import hu.lacztam.logistic.model.TransportPlan;
import hu.lacztam.logistic.service.MilestoneService;
import hu.lacztam.logistic.service.TransportPlanService;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportController {

	@Autowired TransportPlanService transportPlanService;
	@Autowired TransportMapper transportMapper;
	@Autowired MilestoneService milestoneService;
	

	@GetMapping
	public List<TransportPlanDto> getAllTransportPlansWithSections(){
		
		List<TransportPlan> transports = transportPlanService.getAllTransportPlansWithSections();
		List<TransportPlanDto> transportDtos = transportMapper.transportPlansToDtos(transports);
	
		return transportDtos;
	}

	@GetMapping("/{id}")
	public TransportPlanDto transportPlanDtoByQuery(@PathVariable long id) {
		return transportMapper.transportToDto(transportPlanService.getWithSectionsById(id));
	}
	
	@PostMapping("/{transportId}/delay")
	public TransportPlanDto transportPlanDto(
			@PathVariable long transportId,
			@RequestBody TransportDelayDto transportDelayDto) {
		
		TransportPlan transport = transportPlanService.addDelay(transportId, transportDelayDto);
		TransportPlanDto transportDto = transportMapper.transportToDto(transport);
		transportDto.setExpectedArrivalTime(LocalDateTime.now());
		
		transportDto.addDelay(transportDelayDto.getDelayInMinutes());
		
		return transportDto;
	}
	
}
