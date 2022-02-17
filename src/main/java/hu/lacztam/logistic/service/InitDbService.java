package hu.lacztam.logistic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.lacztam.logistic.model.Address;

@Service
public class InitDbService {

	@Autowired AddressService addressService;
	
	public void initDb() {
		Address address = new Address();
		address.setCity("Szeged");
		address.setStreet("Gogol utca");
		address.setHouseNumber(20);
		address.setISO("S2");
		address.setZipCode(6724);
		address.setLatitude(28.123456);
		address.setLongitude(45.678910);
		
		addressService.saveAddress(address);
	}
	
}
