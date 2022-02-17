package hu.lacztam.logistic.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.lacztam.logistic.dto.AddressDto;
import hu.lacztam.logistic.mapper.AddressMapper;
import hu.lacztam.logistic.service.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	@Autowired AddressService addressService;
	@Autowired AddressMapper addressMapper;
	
	@GetMapping
	public List<AddressDto> addresses(){
		return addressMapper.addressesToDtos(addressService.getAllAddresses());
	}
	
}
