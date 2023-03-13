package polytech.info.gl.service.mapper;

import org.mapstruct.*;
import polytech.info.gl.domain.Car;
import polytech.info.gl.service.dto.CarDTO;

/**
 * Mapper for the entity {@link Car} and its DTO {@link CarDTO}.
 */
@Mapper(componentModel = "spring")
public interface CarMapper extends EntityMapper<CarDTO, Car> {}
