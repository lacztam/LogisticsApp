package hu.lacztam.logistic.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hu.lacztam.logistic.dto.AddressDto;
import hu.lacztam.logistic.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	Address dtoToAddress(AddressDto addressDto);
	
	AddressDto addressToDto(Address address);
	
	List<Address> dtosToAddresses(List<AddressDto> dtos);

	List<AddressDto> addressesToDtos(List<Address> addresses);
}
