package hu.lacztam.logistic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.lacztam.logistic.model.Address;
import hu.lacztam.logistic.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired AddressRepository addressRepository;
	
	public List<Address> getAllAddresses(){
		return addressRepository.findAll();
	}
	
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}
	
}
