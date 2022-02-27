package hu.lacztam.logistic.web;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import hu.lacztam.logistic.config.ConfigProperties;
import hu.lacztam.logistic.dto.TransportDelayDto;
import hu.lacztam.logistic.dto.TransportPlanDto;
import hu.lacztam.logistic.model.Address;
import hu.lacztam.logistic.model.Milestone;
import hu.lacztam.logistic.model.Section;
import hu.lacztam.logistic.model.TransportPlan;
import hu.lacztam.logistic.service.AddressService;
import hu.lacztam.logistic.service.InitDbService;
import hu.lacztam.logistic.service.MilestoneService;
import hu.lacztam.logistic.service.SectionService;
import hu.lacztam.logistic.service.TransportPlanService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class TransportControllerUT {
	
	@Autowired AddressService addressService;
	@Autowired MilestoneService milestoneService;
	@Autowired SectionService sectionService;
	@Autowired TransportPlanService transportPlanService;
	@Autowired ConfigProperties configProperties;
	@Autowired WebTestClient webTestClient;
	@Autowired InitDbService dbService;
	
	
	private static final String BASE_URI = "/api/transportPlans";
	
	@BeforeEach
	public void init() {
		dbService.initDb();
	}
	
	@Test
	void testThatMilestonePlannedTimeIsValid() throws Exception {
		long delay = 40L;

		List<TransportPlan> everyTransports = transportPlanService.getAllWithSections();
		TransportPlan transport = everyTransports.get(0);
		
		List<Milestone> milestones = milestoneService.getAllMilestonesQuery();
		Milestone milestone = milestones.get(0);
		long milestoneId = milestone.getMilestoneId();
		LocalDateTime originalPlannedTimeofMilestone = milestone.getPlannedTime();
		
		
		if(transport != null) {
			TransportDelayDto transportDelayDto = new TransportDelayDto();
			transportDelayDto.setDelayInMinutes(delay);
			transportDelayDto.setMilestoneDtoId(milestone.getMilestoneId());
			
			addDelayToTransportPlan(transport.getTransportId(), transportDelayDto);
		}
		Milestone updatedMilestone = milestoneService.findById(milestoneId);
		LocalDateTime afterDelayMilestonePlannedTime = updatedMilestone.getPlannedTime();
		
		assertThat(originalPlannedTimeofMilestone.plusMinutes(delay)).isEqualTo(afterDelayMilestonePlannedTime);
	}
	
	
	@Test
	void testThatIncomeIsValid() throws Exception {
		long delay = 140L;
		double penalty = 0;
		
		if(delay >= 120) 
			penalty = configProperties.getPenalty().getPercent3();
		else if(delay >= 60) 
			penalty = configProperties.getPenalty().getPercent2();
		else if(delay >= 30) 
			penalty = configProperties.getPenalty().getPercent1();
		
		List<TransportPlan> everyTransports = transportPlanService.getAllWithSections();
		TransportPlan transport = everyTransports.get(0);
		TransportPlanDto transportDto = null;
		
		long originalIncome = transport.getIncome();
		
		if(transport != null) {
			List<Milestone> milestones = milestoneService.getAllMilestonesQuery();
			Milestone milestone = milestones.get(1);
			
			TransportDelayDto transportDelayDto = new TransportDelayDto();
			transportDelayDto.setDelayInMinutes(delay);
			transportDelayDto.setMilestoneDtoId(milestone.getMilestoneId());
			
			transportDto = addDelayToTransportPlan(transport.getTransportId(), transportDelayDto);
		}
		
		long afterDelayIncomeValue = transportDto.getIncome();
		long manualCalculation = (long)(originalIncome * ((100.0 - penalty) / 100.0 ));

		assertThat(afterDelayIncomeValue).isEqualTo(manualCalculation);
	}
	
	private TransportPlanDto addDelayToTransportPlan(
			long transportId, 
			TransportDelayDto transportDelayDto) {
		
		return webTestClient
				.post()
				.uri(BASE_URI + "/"+ transportId +"/delay")
				.headers(headers -> headers.setBasicAuth("user2", "pass"))
				.bodyValue(transportDelayDto)
				.exchange().expectStatus().isOk()
				.expectBody(TransportPlanDto.class)
				.returnResult()
				.getResponseBody();
	}
	
}
