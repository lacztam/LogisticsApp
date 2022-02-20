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

	@Mapping(target = "finalIncome", ignore = true)
	@Mapping(target = "expectedArrivalTime", ignore = true)
	@Mapping(target = "delayedArrivalTime", ignore = true)
	@Mapping(target = "sectionDtos.transportPlanDto", ignore = true)
//	@Mapping(target = "sectionDtos.fromMilestoneDto", source = "sections.fromMilestone")
	@Mapping(target = "sectionDtos", source = "sections")
	TransportPlanDto transportToDto(TransportPlan plan);
	
	@Mapping(target = "sections", ignore = true)
	TransportPlan dtoToTransport(TransportPlanDto dto);
	
	List<TransportPlan> dtosToTransportPlans(List<TransportPlanDto> dtos);
	
	List<TransportPlanDto> transportPlansToDtos(List<TransportPlan> transportPlans);
	
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
	
	@Mapping(target = "addressDto", source = "address")
	@Mapping(target = "sectionDtos", ignore = true)
	MilestoneDto milestoneToDto(Milestone milestone);
	
	@Mapping(target = "address", ignore = true)
	@Mapping(target = "sections", ignore = true)
	Milestone dtoTomilestone(MilestoneDto dto);
}
