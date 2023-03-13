package polytech.info.gl.service.mapper;

import org.mapstruct.*;
import polytech.info.gl.domain.Cooperative;
import polytech.info.gl.domain.Driver;
import polytech.info.gl.service.dto.CooperativeDTO;
import polytech.info.gl.service.dto.DriverDTO;

/**
 * Mapper for the entity {@link Driver} and its DTO {@link DriverDTO}.
 */
@Mapper(componentModel = "spring")
public interface DriverMapper extends EntityMapper<DriverDTO, Driver> {
    @Mapping(target = "cooperative", source = "cooperative", qualifiedByName = "cooperativeId")
    DriverDTO toDto(Driver s);

    @Named("cooperativeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CooperativeDTO toDtoCooperativeId(Cooperative cooperative);
}
