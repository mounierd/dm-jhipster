package polytech.info.gl.service.mapper;

import org.mapstruct.*;
import polytech.info.gl.domain.Client;
import polytech.info.gl.service.dto.ClientDTO;

/**
 * Mapper for the entity {@link Client} and its DTO {@link ClientDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClientMapper extends EntityMapper<ClientDTO, Client> {}
