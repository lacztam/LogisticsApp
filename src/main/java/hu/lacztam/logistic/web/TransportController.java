package hu.lacztam.logistic.web;

import java.util.List;

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
import hu.lacztam.logistic.model.TransportPlan;
import hu.lacztam.logistic.service.TransportPlanService;

@RestController
@RequestMapping("/api/transportPlans")
public class TransportController {

	@Autowired TransportPlanService transportPlanService;
	@Autowired TransportMapper transportMapper;
	

	@PostMapping("/{transportId}/delay")
	public TransportPlanDto transportPlanDto(
			@PathVariable long transportId,
			@RequestBody TransportDelayDto transportDelayDto) {
		
		transportPlanService.addDelay(transportId, transportDelayDto);
		
		return null;
	}
	
	@GetMapping
	public List<TransportPlanDto> findAll(){
		List<TransportPlan> everyTransports = transportPlanService.findAll();
		System.out.println("Every transports:" + everyTransports.get(0).getSections().toString());
		return transportMapper.transportPlansToDtos(everyTransports);
	}
	
	@GetMapping("/withoutSections")
	public List<TransportPlanDto> getAllTransportPlans(){
		System.out.println("withoutSections called.");
		List<TransportPlan> transports = transportPlanService.getAllTransportPlansWithOutSections();
		if(transports.isEmpty())
			System.out.println("transport list is empty");
		System.out.println("transport.toString():" + transports.toString());
		List<TransportPlanDto> transportPlans = transportMapper.transportPlansToDtos(
				transports
				);
	
		return transportPlans;
	}
	
	@GetMapping("/withSections")
	public List<TransportPlanDto> getAllTransportPlansWithSections(){
		
		List<TransportPlan> transports 
			= transportPlanService.getAllTransportPlansWithSections();

		List<TransportPlanDto> transportPlans 
			= transportMapper.transportPlansToDtos(transports);
	
		return transportPlans;
	}
	
	@GetMapping("/{id}")
	public TransportPlanDto transportPlanDto(@PathVariable long id) {
		return transportMapper.transportToDto(transportPlanService.getById(id));
	}
	
}
