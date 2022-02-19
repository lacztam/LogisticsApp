package hu.lacztam.logistic.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.lacztam.logistic.dto.SectionDto;
import hu.lacztam.logistic.model.Section;

@Mapper(componentModel = "spring")
public interface SectionMapper {

	@Mapping(source = "sectionId", target = "sectionDtoId")
	@Mapping(source = "fromMilestone", target = "fromMilestoneDto")
	@Mapping(source = "toMilestone", target = "toMilestoneDto")
	@Mapping(target = "toMilestoneDto.addressDto", ignore = true)
	@Mapping(target = "fromMilestoneDto.addressDto", ignore = true)
	SectionDto sectionToDto(Section section);

	@InheritInverseConfiguration
	Section dtoToSection(SectionDto dto);

	List<Section> dtosToSections(List<SectionDto> sectionDto);
	
	List<SectionDto> sectionsToDtos(List<Section> sections);
	
}


