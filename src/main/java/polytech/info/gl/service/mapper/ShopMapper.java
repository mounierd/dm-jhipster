package polytech.info.gl.service.mapper;

import org.mapstruct.*;
import polytech.info.gl.domain.Shop;
import polytech.info.gl.service.dto.ShopDTO;

/**
 * Mapper for the entity {@link Shop} and its DTO {@link ShopDTO}.
 */
@Mapper(componentModel = "spring")
public interface ShopMapper extends EntityMapper<ShopDTO, Shop> {}
