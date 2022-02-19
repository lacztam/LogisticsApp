package hu.lacztam.logistic.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping
	public List<AddressDto> addresses(){
		return addressMapper.addressesToDtos(addressService.getAllAddresses());
	}
	
	@PostMapping
	public ResponseEntity<Address> addAddress(@RequestBody @Valid AddressDto addressDto){
		
		if(addressDto.getAddressId() != null || addressDto == null) {
			return ResponseEntity.badRequest().build();
		}
		
		Address address = addressService.addAddress(addressMapper.dtoToAddress(addressDto));
		
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
		
		if(addressDto.getAddressId() != null)
			if(addressDto.getAddressId() != addressId)
				return ResponseEntity.badRequest().build();
		
		addressDto.setAddressId(addressId);
		addressService.modifyAddress(addressMapper.dtoToAddress(addressDto));		
		
		return ResponseEntity.ok(addressDto);
	}
	
	@PostMapping("/search")
	public List<AddressDto> searchFilteredAddresses(
			@Valid AddressFilterDto addressFilterDto, 
			@PageableDefault(size = 1) Pageable pageable) {
		
		Page<Address> addresses = addressService.searchAddresses(addressFilterDto, pageable);
			
		return addressMapper.addressesToDtos(addresses.getContent());
	}
	
//	@PostMapping("/search")
//	public List<AddressDto> searchDto(
//			@Valid AddressFilterDto addressFilterDto, 
//			@RequestParam(required = false) Integer page,
//			@RequestParam(required = false) Integer size,
//			@RequestParam(required = false) String sort,
//			@RequestParam(required = false) String order) {
//		
//		Page<Address> addresses = addressService.searchAddresses2(addressFilterDto, page, size, sort, order);
//			
//		return addressMapper.addressesToDtos(addresses.getContent());
//	}
	
}
