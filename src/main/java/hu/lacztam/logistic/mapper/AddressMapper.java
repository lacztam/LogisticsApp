package hu.lacztam.logistic.mapper;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.lacztam.logistic.dto.AddressDto;
import hu.lacztam.logistic.model.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	@Mapping(target = "milestoneDto", ignore = true)
	AddressDto addressToDto(Address address);
	
	@Mapping(target = "milestone", ignore = true)
	Address dtoToAddress(@Valid AddressDto addressDto);
	
	List<Address> dtosToAddresses(@Valid List<AddressDto> dtos);

	List<AddressDto> addressesToDtos(List<Address> addresses);
//
//	@Named(value = "withMilestone")
//	@Mapping(target = "milestoneDto.addressDto", ignore = true)
//	@Mapping(target = "milestoneDto.sectionDto", ignore = true)
//	@Mapping(target = "milestoneDto", source = "milestone")
//	@Mapping(target = "addressId", source = "addressId")
//	AddressDto addressWithMilestoneToDto(Address address);
	
	@Named(value = "withTransport")
	@Mapping(target = "milestoneDto.addressDto", ignore = true)
	@Mapping(target = "milestoneDto.sectionDto", source = "milestone.section")
	@Mapping(target = "milestoneDto.sectionDto.fromMilestoneDto", ignore = true)
	@Mapping(target = "milestoneDto.sectionDto.toMilestoneDto", ignore = true)
	@Mapping(target = "milestoneDto.sectionDto.sectionDtoId", source = "milestone.section.sectionId")
	@Mapping(target = "milestoneDto.sectionDto.transportPlanDto", source = "milestone.section.transportPlan")
	@Mapping(target = "milestoneDto.sectionDto.transportPlanDto.sectionDtos", ignore = true)
	@Mapping(target = "milestoneDto", source = "milestone")
	@Mapping(target = "addressId", source = "addressId")
	AddressDto addressWithMilestoneToDto(Address address);
}
