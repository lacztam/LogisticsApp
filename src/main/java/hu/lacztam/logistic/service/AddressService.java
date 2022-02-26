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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import hu.lacztam.logistic.dto.AddressDto;
import hu.lacztam.logistic.dto.AddressFilterDto;
import hu.lacztam.logistic.mapper.AddressMapper;
import hu.lacztam.logistic.model.Address;
import hu.lacztam.logistic.model.Milestone;
import hu.lacztam.logistic.repository.AddressRepository;
import hu.lacztam.logistic.repository.MilestoneRepository;

@Service
public class AddressService {

	@Autowired AddressRepository addressRepository;
	@Autowired AddressMapper addressMapper;
	@Autowired MilestoneService milestoneService;
	
	public List<Address> getAllAddresses(){
		return addressRepository.findAll();
	}
	
	@Transactional
	public Address createAddress(Address address) {
		Address saveAddress = addressRepository.save(address);
		return saveAddress;
	}
	
	@Transactional
	public Address findById(long id) {
		return addressRepository.findById(id)
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public Optional<Address> findByIdOptional(Long id) {
		Optional<Address> address = null;
		if(id != null) 
			address = addressRepository.findById(id);
		return address;			
	}
	
	@Transactional
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}
	
	@Transactional
	public void deleteAddressById(long addressId) {
		Address address 
			= addressRepository.findById(addressId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		Milestone milestone = address.getMilestone();
		if(milestone != null) {
			milestone.setAddress(null);
			milestoneService.saveMilestone(milestone);
		}
		addressRepository.deleteById(addressId);
	}

	@Transactional
	public Address addMilestoneToAddress(long addressId, long milestoneId) {
		Milestone milestone = milestoneService.findById(milestoneId);
		
		Address address = findById(addressId);
		address.setMilestone(milestone);
		if(milestone.getAddress() == null) {
			milestone.setAddress(address);
			milestoneService.saveMilestone(milestone);
		}
		return addressRepository.save(address);
	}
	
	@Transactional
	public Address modifyAddress(long addressId, AddressDto addressDto) {
		if(addressDto == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		if(addressDto.getAddressId() != null) {
			if(addressDto.getAddressId() != addressId)
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		addressDto.setAddressId(addressId);
		
		Optional<Address> optionalAddress = addressRepository.findById(addressDto.getAddressId());
		
		if(optionalAddress.isPresent()) 
			return addressRepository.save(addressMapper.dtoToAddress(addressDto));
		else 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}

	@Transactional
	public Page<Address> searchAddresses(AddressFilterDto addressFilterDto, Pageable paging) {
		if(addressFilterDto.getCountryISO() == null 
			&& addressFilterDto.getCity() == null 
			&& addressFilterDto.getStreet() == null 
			&& addressFilterDto.getZipCode() == null) { 
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		Integer defaultPageValue = 0;
		Integer defaultSizeValue = Integer.MAX_VALUE;
		Sort defaultSortValue = Sort.by("addressId").ascending();

		if(paging.isUnpaged())
			paging = PageRequest.of(defaultPageValue, paging.getPageSize(), paging.getSort());
		
		if(paging.getPageSize() == 999)
			paging = PageRequest.of(paging.getPageNumber(), defaultSizeValue, paging.getSort());
		
		if(paging.getSort().isUnsorted())
			paging = PageRequest.of(paging.getPageNumber(), paging.getPageSize(), defaultSortValue);

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
		
		Page<Address> addressPage = addressRepository.findAll(spec, paging);
		
		return addressPage;
	}
}
