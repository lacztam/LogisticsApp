package hu.lacztam.logistic.mapper;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.boot.context.properties.bind.Name;

import hu.lacztam.logistic.dto.AddressDto;
import hu.lacztam.logistic.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	@Mapping(target = "milestoneDto", ignore = true)
	AddressDto addressToDto(Address address);
	
//	@Mapping(target = "milestone", ignore = true)
	@InheritInverseConfiguration(name = "addressToDto")
	Address dtoToAddress(@Valid AddressDto addressDto);
	
	List<Address> dtosToAddresses(@Valid List<AddressDto> dtos);

	List<AddressDto> addressesToDtos(List<Address> addresses);
	
	@Named(value = "withTransport")
	@Mapping(target = "milestoneDto", source = "milestone")
	@Mapping(target = "addressId", source = "addressId")
	AddressDto addressWithMilestoneToDto(Address address);
}
