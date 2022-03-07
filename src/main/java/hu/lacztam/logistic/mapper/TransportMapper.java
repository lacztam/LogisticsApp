package hu.lacztam.logistic.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import hu.lacztam.logistic.dto.MilestoneDto;
import hu.lacztam.logistic.dto.SectionDto;
import hu.lacztam.logistic.dto.TransportPlanDto;
import hu.lacztam.logistic.model.Milestone;
import hu.lacztam.logistic.model.Section;
import hu.lacztam.logistic.model.TransportPlan;

@Mapper(componentModel = "spring")
public interface TransportMapper {
    
	@Mapping(target = "milestoneDtos", ignore = true)
	@Mapping(target = "sectionDtos", source = "sections")
	@Mapping(target = "sectionDtos.transportPlanDto", ignore = true)
	TransportPlanDto transportToDto(TransportPlan plan);
	
	TransportPlan dtoToTransport(TransportPlanDto dto);
	
	List<TransportPlan> dtosToTransportPlans(List<TransportPlanDto> dtos);
	
	List<TransportPlanDto> transportPlansToDtos(List<TransportPlan> transportPlans);
	
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
	
	@Mapping(target = "transportPlanDto", ignore = true)
	@Mapping(target = "addressDto.milestoneDto", ignore = true)
	@Mapping(target = "addressDto", source = "address")
	MilestoneDto milestoneToDto(Milestone milestone);
	
	@InheritInverseConfiguration
	Milestone dtoTomilestone(MilestoneDto dto);
	
	List<MilestoneDto> milestonesToDtos(List<Milestone> milestones);
	List<Milestone> dtoToMilestone(List<MilestoneDto> dtos);
}
