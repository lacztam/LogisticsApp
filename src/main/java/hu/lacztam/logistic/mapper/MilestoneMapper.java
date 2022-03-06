package hu.lacztam.logistic.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.lacztam.logistic.dto.MilestoneDto;
import hu.lacztam.logistic.model.Milestone;

@Mapper(componentModel = "spring")
public interface MilestoneMapper {

	@Mapping(target = "transportPlanDto", ignore = true)
	@Mapping(target = "addressDto.milestoneDto", ignore = true)
	@Mapping(target = "addressDto", source = "address")
	MilestoneDto milestoneToDto(Milestone milestone);
	
	@InheritInverseConfiguration
	Milestone dtoTomilestone(MilestoneDto dto);
	
	List<MilestoneDto> milestonesToDtos(List<Milestone> milestones);
	List<Milestone> dtoToMilestone(List<MilestoneDto> dtos);
}
