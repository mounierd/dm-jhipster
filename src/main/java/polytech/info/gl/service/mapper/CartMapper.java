package polytech.info.gl.service.mapper;

import org.mapstruct.*;
import polytech.info.gl.domain.Cart;
import polytech.info.gl.domain.Client;
import polytech.info.gl.domain.Command;
import polytech.info.gl.domain.Shop;
import polytech.info.gl.service.dto.CartDTO;
import polytech.info.gl.service.dto.ClientDTO;
import polytech.info.gl.service.dto.CommandDTO;
import polytech.info.gl.service.dto.ShopDTO;

/**
 * Mapper for the entity {@link Cart} and its DTO {@link CartDTO}.
 */
@Mapper(componentModel = "spring")
public interface CartMapper extends EntityMapper<CartDTO, Cart> {
    @Mapping(target = "command", source = "command", qualifiedByName = "commandId")
    @Mapping(target = "client", source = "client", qualifiedByName = "clientId")
    @Mapping(target = "shop", source = "shop", qualifiedByName = "shopId")
    CartDTO toDto(Cart s);

    @Named("commandId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CommandDTO toDtoCommandId(Command command);

    @Named("clientId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ClientDTO toDtoClientId(Client client);

    @Named("shopId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ShopDTO toDtoShopId(Shop shop);
}
