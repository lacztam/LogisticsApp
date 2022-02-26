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

	@Mapping(target = "income", source = "income")
	@Mapping(target = "sectionDtos.transportPlanDto", ignore = true)
	@Mapping(target = "sectionDtos", source = "sections")
	TransportPlanDto transportToDto(TransportPlan plan);
	
	@Mapping(target = "sections", ignore = true)
	@Mapping(target = "income", source = "income")
	TransportPlan dtoToTransport(TransportPlanDto dto);
	
	List<TransportPlan> dtosToTransportPlans(List<TransportPlanDto> dtos);
	
	List<TransportPlanDto> transportPlansToDtos(List<TransportPlan> transportPlans);
	
	@Mapping(target = "sectionDtoId", source = "sectionId")
	@Mapping(target = "fromMilestoneDto", source = "fromMilestone")
	@Mapping(target = "toMilestoneDto", source = "toMilestone")
	@Mapping(target = "toMilestoneDto.sectionDto", ignore = true)
	@Mapping(target = "fromMilestoneDto.sectionDto", ignore = true)
	@Mapping(target = "transportPlanDto", ignore = true)
	@Mapping(target = "toMilestoneDto.addressDto", source = "toMilestone.address")
	@Mapping(target = "fromMilestoneDto.addressDto", source = "fromMilestone.address")
	@Mapping(target = "toMilestoneDto.addressDto.milestoneDto", ignore = true)
	@Mapping(target = "fromMilestoneDto.addressDto.milestoneDto", ignore = true)
	SectionDto sectionToDto(Section section);

	@InheritInverseConfiguration
	Section dtoToSection(SectionDto dto);
	
	@Mapping(target = "addressDto", source = "address")
	@Mapping(target = "sectionDto", ignore = true)
	MilestoneDto milestoneToDto(Milestone milestone);
	
	@Mapping(target = "address", ignore = true)
	@Mapping(target = "section", ignore = true)
	Milestone dtoTomilestone(MilestoneDto dto);
}
