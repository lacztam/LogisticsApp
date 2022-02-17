package hu.lacztam.logistic.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.lacztam.logistic.dto.MilestoneDto;
import hu.lacztam.logistic.model.Milestone;

@Mapper(componentModel = "spring")
public interface MilestoneMapper {

	@Mapping(target = "addressDto", source = "address")
	MilestoneDto milestoneToDto(Milestone milestone);
	@Mapping(target = "address", source = "addressDto")
	Milestone dtoTomilestone(MilestoneDto dto);
	
	@Mapping(ignore = true, target = "address")
	Milestone dtoTomilestoneNoAddress(MilestoneDto dto);
	@Mapping(ignore = true, target = "addressDto")
	MilestoneDto milestoneToDtoNoAddress(Milestone milestone);
	
}
