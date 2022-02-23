package hu.lacztam.logistic.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
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

import hu.lacztam.logistic.dto.AddressDto;
import hu.lacztam.logistic.dto.AddressFilterDto;
import hu.lacztam.logistic.mapper.AddressMapper;
import hu.lacztam.logistic.model.Address;
import hu.lacztam.logistic.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired AddressRepository addressRepository;
	@Autowired AddressMapper addressMapper;
	
	public List<Address> getAllAddresses(){
		return addressRepository.findAll();
	}
	
	@Transactional
	public Address createAddress(Address address) {
		return addressRepository.save(address);
	}
	
	@Transactional
	public Address findById(long id) {
		return addressRepository.findById(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@Transactional
	public void deleteAddressById(long id) {
		Optional<Address> address = addressRepository.findById(id);

		if(address.isPresent()) 
			addressRepository.deleteById(id);
		
	}

	@Transactional
	public Address modifyAddress(Address address) {

		Optional<Address> optionalAddress = addressRepository.findById(address.getAddressId());
		Address modifiedAddress;
		if(optionalAddress.isPresent()) 
			return modifiedAddress = addressRepository.save(address);
		else 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@Transactional
	public List<AddressDto> searchAddresses(@Valid AddressFilterDto addressFilterDto, Pageable paging, HttpServletResponse response) {
	
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
		List<Address> addressPageList = addresses.getContent();		
		response.addHeader("X-Total-Count", String.valueOf(addressPageList.size()));
		return addressMapper.addressesToDtos(addressPageList);
	}
	
}
