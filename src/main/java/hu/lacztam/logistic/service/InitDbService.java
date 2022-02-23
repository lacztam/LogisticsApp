package hu.lacztam.logistic.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.lacztam.logistic.model.Address;
import hu.lacztam.logistic.model.Milestone;
import hu.lacztam.logistic.model.Section;
import hu.lacztam.logistic.model.TransportPlan;

@Service
public class InitDbService {

	@Autowired AddressService addressService;
	@Autowired MilestoneService milestoneService;
	@Autowired SectionService sectionService;
	@Autowired TransportPlanService transportPlanService;
	
	public void initDb() {
		Address address1 = new Address();
		address1.setCity("Szeged");
		address1.setStreet("Gogol utca");
		address1.setHouseNumber(20);
		address1.setCountryISO("HU");
		address1.setZipCode(6724);
		address1.setLatitude(28.123456);
		address1.setLongitude(45.678910);
		addressService.createAddress(address1);
		
		Address address2 = new Address();
		address2.setCity("Budapest");
		address2.setStreet("Parlament utca");
		address2.setHouseNumber(123);
		address2.setCountryISO("HU");
		address2.setZipCode(1105);
		address2.setLatitude(73.123456);
		address2.setLongitude(99.678910);
		addressService.createAddress(address2);
		
		Address address3 = new Address();
		address3.setCity("Pécs");
		address3.setStreet("Templom utca");
		address3.setHouseNumber(46);
		address3.setCountryISO("HU");
		address3.setZipCode(1234);
		address3.setLatitude(38.123456);
		address3.setLongitude(45.678910);
		addressService.createAddress(address3);
		
		Address address4 = new Address();
		address4.setCity("Székesfehérvár");
		address4.setStreet("Király utca ");
		address4.setHouseNumber(50);
		address4.setCountryISO("HU");
		address4.setZipCode(7777);
		address4.setLatitude(63.123456);
		address4.setLongitude(54.678910);
		addressService.createAddress(address4);
		
		
		Milestone milestone1 = new Milestone();
		milestone1.setAddress(address1);
		milestone1.setPlannedTime(LocalDateTime.now());
		milestoneService.createMilestone(milestone1);
		
		Milestone milestone2 = new Milestone();
		milestone2.setAddress(address2);
		milestone2.setPlannedTime(milestone1.getPlannedTime().plusDays(1).plusHours(2).plusMinutes(23));
		milestoneService.createMilestone(milestone2);
	
		Milestone milestone3 = new Milestone();
		milestone3.setAddress(address3);
		milestone3.setPlannedTime(milestone2.getPlannedTime().plusDays(1).plusHours(3).plusMinutes(42));
		milestoneService.createMilestone(milestone3);
		
		Milestone milestone4 = new Milestone();
		milestone4.setAddress(address4);
		milestone4.setPlannedTime(milestone3.getPlannedTime().plusDays(1).plusHours(4).plusMinutes(18));
		milestoneService.createMilestone(milestone4);
		
		
		Section section1 = new Section();
		section1.setNumber(0);
		section1.setFromMilestone(milestone1);
		section1.setToMilestone(milestone2);
		sectionService.crateSection(section1);
		
		Section section2 = new Section();
		section2.setNumber(1);
		section2.setFromMilestone(milestone3);
		section2.setToMilestone(milestone4);
		sectionService.crateSection(section2);
		
		
		TransportPlan transportPlan = new TransportPlan();
		transportPlan.addSection(sectionService.sectionById(9));
		transportPlan.addSection(sectionService.sectionById(10));
		transportPlan.setExpectedIncome(100000);
		transportPlanService.crateTransportPlan(transportPlan);
	}
}
