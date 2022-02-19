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
	@Mapping(target = "fromMilestoneDto", source = "fromMilestone")
	@Mapping(target = "toMilestoneDto", source = "toMilestone")
	@Mapping(target = "toMilestoneDto.sectionDtos", ignore = true)
	@Mapping(target = "fromMilestoneDto.sectionDtos", ignore = true)
	@Mapping(target = "transportPlanDto", ignore = true)
	@Mapping(target = "toMilestoneDto.addressDto", source = "toMilestone.address")
	@Mapping(target = "fromMilestoneDto.addressDto", source = "fromMilestone.address")
	@Mapping(target = "toMilestoneDto.addressDto.milestoneDtos", ignore = true)
	@Mapping(target = "fromMilestoneDto.addressDto.milestoneDtos", ignore = true)
	SectionDto sectionToDto(Section section);

	@InheritInverseConfiguration
	Section dtoToSection(SectionDto dto);

	List<Section> dtosToSections(List<SectionDto> sectionDto);
	
	List<SectionDto> sectionsToDtos(List<Section> sections);
	
	
	@Mapping(target = "addressDto", source = "address")
	@Mapping(target = "sectionDtos", ignore = true)
	MilestoneDto milestoneToDto(Milestone milestone);
}


