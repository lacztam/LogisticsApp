package hu.lacztam.logistic.web;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import hu.lacztam.logistic.config.ConfigProperties;
import hu.lacztam.logistic.dto.AddressDto;
import hu.lacztam.logistic.dto.AddressFilterDto;
import hu.lacztam.logistic.mapper.AddressMapper;
import hu.lacztam.logistic.model.Address;
import hu.lacztam.logistic.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

	@Autowired AddressService addressService;
	@Autowired AddressMapper addressMapper;
	@Autowired ConfigProperties configProperties;
	
	@GetMapping
	public List<AddressDto> addresses(){
		return addressMapper.addressesToDtos(addressService.getAllAddresses());
	}
	
	@PostMapping
	public ResponseEntity<Address> createAddress(@RequestBody @Valid AddressDto addressDto){
		if(addressDto.getAddressId() != null || addressDto == null)
			return ResponseEntity.badRequest().build();
		
		Address address 
				= addressService.createAddress(addressMapper.dtoToAddress(addressDto));
		
		return ResponseEntity.ok(address);
	}
	
	@GetMapping("/{addressId}")
	public AddressDto getAddressById(@PathVariable long addressId) {
		Address address = addressService.findById(addressId);
		
		return addressMapper.addressToDto(address);
	}
	
	@DeleteMapping("/{addressId}")
	public void deleteAddress(@PathVariable long addressId){
		addressService.deleteAddressById(addressId);
	}
	
	@PutMapping("/{addressId}")
	public ResponseEntity<AddressDto> modifyAddress(
			@PathVariable long addressId, 
			@RequestBody @Valid AddressDto addressDto) {

		Address modifiedadd = addressService.modifyAddress(addressId, addressDto);		
		
		return ResponseEntity.ok(addressMapper.addressToDto(modifiedadd));
	}
	
	@PostMapping("/search")
	public List<AddressDto> searchFilteredAddressesWithPageable(
			@RequestBody AddressFilterDto addressFilterDto, 
			@PageableDefault(size = 999) Pageable pageable,
			HttpServletResponse response) {
		
		Page<Address> addressPage 
				= addressService.searchAddresses(addressFilterDto, pageable);
		
		List<AddressDto> addressDtoPage = addressMapper.addressesToDtos(addressPage.getContent());
				
		response.addHeader("X-Total-Count", String.valueOf(addressDtoPage.size()));
		
		return addressDtoPage;
	}
	
}
