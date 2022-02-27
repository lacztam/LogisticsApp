package hu.lacztam.logistic.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.lacztam.logistic.dto.MilestoneDto;
import hu.lacztam.logistic.model.Milestone;

@Mapper(componentModel = "spring")
public interface MilestoneMapper {

	@Mapping(target = "addressDto", source = "address")
	@Mapping(target = "sectionDto", ignore = true)
	@Mapping(target = "addressDto.milestoneDto", ignore = true)
	MilestoneDto milestoneToDto(Milestone milestone);
	
	@Mapping(target = "address", ignore = true)
	@Mapping(target = "section", ignore = true)
	Milestone dtoTomilestone(MilestoneDto dto);
	
	List<MilestoneDto> milestonesToDtos(List<Milestone> milestones);
	List<Milestone> dtoToMilestone(List<MilestoneDto> dtos);
}
