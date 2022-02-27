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
	
	private static final String BASE_URI = "/api/transportPlans";
	
	@Test
	void testThatIncomeIsValid() throws Exception {
		List<TransportPlan> everyTransports = transportPlanService.getAllWithSections();
		TransportPlan transport = everyTransports.get(0);
		TransportPlanDto transportDto = null;
		
		long originalIncome = transport.getIncome();
		
		if(transport != null) {
			List<Milestone> milestones = milestoneService.getAllMilestonesQuery();
			Milestone milestone = milestones.get(1);
			
			TransportDelayDto transportDelayDto = new TransportDelayDto();
			transportDelayDto.setDelayInMinutes(40L);
			transportDelayDto.setMilestoneDtoId(milestone.getMilestoneId());
			
			transportDto = addDelayToTransportPlan(transport.getTransportId(), transportDelayDto);
		}
		
		double penalty = configProperties.getPenalty().getPercent1();
		
		long delayFunctionCalculation = transportDto.getIncome();
		long manualCalculation = (long)(originalIncome * ((100.0 - penalty) / 100.0 ));

		assertThat(delayFunctionCalculation).isEqualTo(manualCalculation);
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
	
	@BeforeEach
	public void init() {
		Address address1 = new Address();
		address1.setCity("Szeged");
		address1.setStreet("Gogol utca");
		address1.setHouseNumber(20);
		address1.setCountryISO("HU");
		address1.setZipCode(6724);
		address1.setLatitude(28.123456);
		address1.setLongitude(45.678910);
		address1 = addressService.createAddress(address1);
		
		Address address2 = new Address();
		address2.setCity("Budapest");
		address2.setStreet("Parlament utca");
		address2.setHouseNumber(123);
		address2.setCountryISO("HU");
		address2.setZipCode(1105);
		address2.setLatitude(73.123456);
		address2.setLongitude(99.678910);
		address2 = addressService.createAddress(address2);
		
		Address address3 = new Address();
		address3.setCity("Pécs");
		address3.setStreet("Templom utca");
		address3.setHouseNumber(46);
		address3.setCountryISO("HU");
		address3.setZipCode(1234);
		address3.setLatitude(38.123456);
		address3.setLongitude(45.678910);
		address3 = addressService.createAddress(address3);
		
		Address address4 = new Address();
		address4.setCity("Székesfehérvár");
		address4.setStreet("Király utca ");
		address4.setHouseNumber(50);
		address4.setCountryISO("HU");
		address4.setZipCode(7777);
		address4.setLatitude(63.123456);
		address4.setLongitude(54.678910);
		address4 = addressService.createAddress(address4);
		
		
		Milestone milestone1 = new Milestone();
		milestone1.setPlannedTime(LocalDateTime.now());
		milestone1 = milestoneService.createMilestone(milestone1);
		System.out.println("\n\nmilestone1.getMilestoneId():" + milestone1.getMilestoneId() + "\n");
		milestoneService.addAddressToMilestone(milestone1.getMilestoneId(), address1.getAddressId());
		
		Milestone milestone2 = new Milestone();
		milestone2.setPlannedTime(milestone1.getPlannedTime().plusDays(1).plusHours(2).plusMinutes(23));
		milestone2 = milestoneService.createMilestone(milestone2);
		milestoneService.addAddressToMilestone(milestone2.getMilestoneId(), address2.getAddressId());
	
		Milestone milestone3 = new Milestone();
		milestone3.setPlannedTime(milestone2.getPlannedTime().plusDays(1).plusHours(3).plusMinutes(42));
		milestone3 = milestoneService.createMilestone(milestone3);
		milestoneService.addAddressToMilestone(milestone3.getMilestoneId(), address3.getAddressId());
		
		Milestone milestone4 = new Milestone();
		milestone4.setPlannedTime(milestone3.getPlannedTime().plusDays(1).plusHours(4).plusMinutes(18));
		milestone4 = milestoneService.createMilestone(milestone4);
		milestoneService.addAddressToMilestone(milestone4.getMilestoneId(), address4.getAddressId());
		
		
		Section section1 = new Section();
		section1.setNumber(0);
		section1 = sectionService.crateNormalSection(section1);
		sectionService.addFromMilestone(section1.getSectionId(), milestone1.getMilestoneId());
		section1 = sectionService.addToMilestone(section1.getSectionId(), milestone2.getMilestoneId());
		
		Section section2 = new Section();
		section2.setNumber(1);
		sectionService.crateNormalSection(section2);
		sectionService.addFromMilestone(section2.getSectionId(), milestone3.getMilestoneId());
		section2 = sectionService.addToMilestone(section2.getSectionId(), milestone4.getMilestoneId());
		
		
		TransportPlan transportPlan = new TransportPlan();
		transportPlan.addSection(section1);
		transportPlan.addSection(section2);
		transportPlan.setIncome(100000);
		transportPlan = transportPlanService.saveTransportPlan(transportPlan);
		section1.setTransportPlan(transportPlan);
		section2.setTransportPlan(transportPlan);
		sectionService.saveSection(section1);
		sectionService.saveSection(section2);
	}
}
