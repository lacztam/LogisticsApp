//package hu.lacztam.logistic.web;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import hu.lacztam.logistic.dto.TransportDelayDto;
//import hu.lacztam.logistic.dto.TransportPlanDto;
//import hu.lacztam.logistic.service.TransportPlanService;
//
//@RestController
//@RequestMapping("/api/transportPlans")
//public class TransportController {
//
//	@Autowired TransportPlanService transportPlanService;
//	
//	@PostMapping("/{transportId}/delay")
//	public TransportPlanDto transportPlanDto(
//			@PathVariable long transportId,
//			@RequestBody TransportDelayDto transportDelayDto) {
//		
////		transportPlanService.addDelay(transportId, transportDelayDto);
//		
//		return null;
//	}
//	
//}
