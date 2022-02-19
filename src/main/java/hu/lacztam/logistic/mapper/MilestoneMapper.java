package hu.lacztam.logistic.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import hu.lacztam.logistic.dto.MilestoneDto;
import hu.lacztam.logistic.model.Milestone;

@Mapper(componentModel = "spring")
public interface MilestoneMapper {

	@Mapping(target = "addressDto", source = "address")
	@Mapping(target = "sectionDtos", ignore = true)
	MilestoneDto milestoneToDto(Milestone milestone);
	
	@Mapping(target = "address", ignore = true)
	@Mapping(target = "sections", ignore = true)
	Milestone dtoTomilestone(MilestoneDto dto);
	
//	@Named("noaddress")
//	@Mapping(ignore = true, target = "address")
//	Milestone dtoTomilestoneNoAddress(MilestoneDto dto);
//	@Mapping(ignore = true, target = "addressDto")
//	MilestoneDto milestoneToDtoNoAddress(Milestone milestone);
	
	List<MilestoneDto> milestonesToDtos(List<Milestone> milestones);
	List<Milestone> dtoToMilestone(List<MilestoneDto> dtos);
}
