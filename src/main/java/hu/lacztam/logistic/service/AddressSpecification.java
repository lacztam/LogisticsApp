package hu.lacztam.logistic.service;

import org.springframework.data.jpa.domain.Specification;

import hu.lacztam.logistic.model.Address;
import hu.lacztam.logistic.model.Address_;


public class AddressSpecification {

	public static Specification<Address> hasCountry(String countryISO){
		return (root, cq, cb) -> cb.equal(root.get(Address_.countryISO), countryISO);
	}
	
	public static Specification<Address> hasZipCode(int zipCode){
		return (root, cq, cb) -> cb.equal(root.get(Address_.zipCode), zipCode);
	}
	
	public static Specification<Address> hasCity(String city){
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.city)), (city + "%").toLowerCase() );
	}
	
	public static Specification<Address> hasStreet(String street){
		return (root, cq, cb) -> cb.like(cb.lower(root.get(Address_.street)), (street + "%").toLowerCase() );
	}
}
