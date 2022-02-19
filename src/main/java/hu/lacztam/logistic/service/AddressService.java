package hu.lacztam.logistic.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import hu.lacztam.logistic.dto.AddressFilterDto;
import hu.lacztam.logistic.model.Address;
import hu.lacztam.logistic.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired AddressRepository addressRepository;
	
	public List<Address> getAllAddresses(){
		return addressRepository.findAll();
	}
	
	@Transactional
	public Address createAddress(Address address) {
		return addressRepository.save(address);
	}
	
	public Address findById(long id) {
		return addressRepository.findById(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@Transactional
	public void deleteAddressById(long id) {
		Optional<Address> address = addressRepository.findById(id);

		if(address.isPresent()) {
			addressRepository.deleteById(id);
		}
	}

	@Transactional
	public void modifyAddress(Address address) {
		
		Optional<Address> optionalAddress = addressRepository.findById(address.getAddressId());
		
		if(optionalAddress.isPresent()) {
			addressRepository.save(address);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}


	public Page<Address> searchAddresses(@Valid AddressFilterDto addressFilterDto, Pageable paging) {
		
		Integer page = 0; 
		Integer size = Integer.MAX_VALUE;
		String sortValue = "addressId";
		Sort sort = Sort.by(sortValue).ascending();
		
		if(paging.getPageSize() == 1)
			paging = PageRequest.of(paging.getPageNumber(), size);
		
		if(paging.isUnpaged()) 
			paging = PageRequest.of(page, paging.getPageSize());
		
		if(paging.getSort().isUnsorted()) 
			paging = PageRequest.of(paging.getPageNumber(), paging.getPageSize(), sort);
		
		String countryISO = addressFilterDto.getCountryISO();
		String city=addressFilterDto.getCity();
		String street = addressFilterDto.getStreet();
		Integer zipCode = addressFilterDto.getZipCode();
		
		Specification<Address> spec = Specification.where(null);
		
		if(StringUtils.hasText(countryISO))
			spec = spec.and(AddressSpecification.hasCountry(countryISO));
		if(StringUtils.hasText(city))
			spec = spec.and(AddressSpecification.hasCity(city));
		if(StringUtils.hasText(street)) 
			spec = spec.and(AddressSpecification.hasStreet(street));
		if(zipCode != null && zipCode > 0)
			spec = spec.and(AddressSpecification.hasZipCode(zipCode));
		
		Page<Address> addresses = addressRepository.findAll(spec, paging);
		
		return addresses;
	}
	
	public Page<Address> searchAddressesWithFields(
			AddressFilterDto addressFilterDto, 
			Integer page, Integer size, String sortValue, String order){
		
		Pageable paging = null;
		Sort sort = Sort.by("addressId").ascending();
		
		if(page == null)
			page = 0;
		
		if(StringUtils.hasText(sortValue))
			sort = Sort.by(sortValue);
		
		boolean descOrder = order.equals("descending") || order.equals("desc");
		if(order != null && descOrder) 
			sort = sort.descending();
		
		if(size == null)
			paging = Pageable.unpaged();
		else
			paging = PageRequest.of(page, size, sort);
		
		String countryISO = addressFilterDto.getCountryISO();
		String city=addressFilterDto.getCity();
		String street = addressFilterDto.getStreet();
		Integer zipCode = addressFilterDto.getZipCode();
		
		Specification<Address> spec = Specification.where(null);
		
		if(StringUtils.hasText(countryISO))
			spec = spec.and(AddressSpecification.hasCity(countryISO));
		if(StringUtils.hasText(city))
			spec = spec.and(AddressSpecification.hasCity(city));
		if(StringUtils.hasText(street)) 
			spec = spec.and(AddressSpecification.hasStreet(street));
		if(zipCode != null && zipCode > 0)
			spec = spec.and(AddressSpecification.hasZipCode(zipCode));
		
		Page<Address> addresses = addressRepository.findAll(spec, paging);
		
		return addresses;
	}
}
