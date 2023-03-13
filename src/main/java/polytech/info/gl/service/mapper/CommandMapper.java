package polytech.info.gl.service.mapper;

import org.mapstruct.*;
import polytech.info.gl.domain.Client;
import polytech.info.gl.domain.Command;
import polytech.info.gl.domain.Driver;
import polytech.info.gl.service.dto.ClientDTO;
import polytech.info.gl.service.dto.CommandDTO;
import polytech.info.gl.service.dto.DriverDTO;

/**
 * Mapper for the entity {@link Command} and its DTO {@link CommandDTO}.
 */
@Mapper(componentModel = "spring")
public interface CommandMapper extends EntityMapper<CommandDTO, Command> {
    @Mapping(target = "client", source = "client", qualifiedByName = "clientId")
    @Mapping(target = "driver", source = "driver", qualifiedByName = "driverId")
    CommandDTO toDto(Command s);

    @Named("clientId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ClientDTO toDtoClientId(Client client);

    @Named("driverId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DriverDTO toDtoDriverId(Driver driver);
}
