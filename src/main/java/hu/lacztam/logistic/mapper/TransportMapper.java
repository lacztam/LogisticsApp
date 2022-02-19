//package hu.lacztam.logistic.mapper;
//
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import hu.lacztam.logistic.dto.TransportPlanDto;
//import hu.lacztam.logistic.model.TransportPlan;
//
//@Mapper(componentModel = "spring")
//public interface TransportMapper {
//
//	@Mapping(target = "sectionDtos", source = "sections")
//	TransportPlanDto transportToDto(TransportPlan plan);
//	
//	@Mapping(target = "sections", ignore = true)
//	TransportPlan dtoToTransport(TransportPlanDto dto);
//}
