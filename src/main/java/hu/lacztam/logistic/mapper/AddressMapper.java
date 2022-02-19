package hu.lacztam.logistic.mapper;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.lacztam.logistic.dto.AddressDto;
import hu.lacztam.logistic.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	@Mapping(target = "milestones", ignore = true)
	Address dtoToAddress(@Valid AddressDto addressDto);
	
	AddressDto addressToDto(Address address);
	
	List<Address> dtosToAddresses(@Valid List<AddressDto> dtos);

	List<AddressDto> addressesToDtos(List<Address> addresses);
}
