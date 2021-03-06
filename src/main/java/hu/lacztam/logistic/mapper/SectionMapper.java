package hu.lacztam.logistic.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.lacztam.logistic.dto.MilestoneDto;
import hu.lacztam.logistic.dto.SectionDto;
import hu.lacztam.logistic.model.Milestone;
import hu.lacztam.logistic.model.Section;

@Mapper(componentModel = "spring")
public interface SectionMapper {

	@Mapping(target = "sectionDtoId", source = "sectionId")
	@Mapping(target = "transportPlanDto", ignore = true)
	
	@Mapping(target = "toMilestoneDto", source = "toMilestone")
	@Mapping(target = "toMilestoneDto.addressDto", source = "toMilestone.address")
	@Mapping(target = "toMilestoneDto.addressDto.milestoneDto", ignore = true)
	@Mapping(target = "toMilestoneDto.transportPlanDto", ignore = true)
	
	@Mapping(target = "fromMilestoneDto", source = "fromMilestone")
	@Mapping(target = "fromMilestoneDto.addressDto", source = "fromMilestone.address")
	@Mapping(target = "fromMilestoneDto.addressDto.milestoneDto", ignore = true)
	@Mapping(target = "fromMilestoneDto.transportPlanDto", ignore = true)
	SectionDto sectionToDto(Section section);

	@InheritInverseConfiguration
	Section dtoToSection(SectionDto dto);

	List<Section> dtosToSections(List<SectionDto> sectionDto);
	
	List<SectionDto> sectionsToDtos(List<Section> sections);
	
	@Mapping(target = "addressDto", source = "address")
	@Mapping(target = "addressDto.milestoneDto.transportPlanDto", ignore = true)
	@Mapping(target = "transportPlanDto", source = "transportPlan")
	@Mapping(target = "transportPlanDto.milestoneDtos", ignore = true)
	MilestoneDto milestoneToDto(Milestone milestone);
	
	List<MilestoneDto> milestonesToDtos(List<Milestone> milestones);
	List<Milestone> dtoToMilestone(List<MilestoneDto> dtos);
}


