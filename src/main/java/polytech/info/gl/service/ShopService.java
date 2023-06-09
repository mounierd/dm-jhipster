package polytech.info.gl.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import polytech.info.gl.domain.Shop;
import polytech.info.gl.repository.ShopRepository;
import polytech.info.gl.service.dto.ShopDTO;
import polytech.info.gl.service.mapper.ShopMapper;

/**
 * Service Implementation for managing {@link Shop}.
 */
@Service
@Transactional
public class ShopService {

    private final Logger log = LoggerFactory.getLogger(ShopService.class);

    private final ShopRepository shopRepository;

    private final ShopMapper shopMapper;

    public ShopService(ShopRepository shopRepository, ShopMapper shopMapper) {
        this.shopRepository = shopRepository;
        this.shopMapper = shopMapper;
    }

    /**
     * Save a shop.
     *
     * @param shopDTO the entity to save.
     * @return the persisted entity.
     */
    public ShopDTO save(ShopDTO shopDTO) {
        log.debug("Request to save Shop : {}", shopDTO);
        Shop shop = shopMapper.toEntity(shopDTO);
        shop = shopRepository.save(shop);
        return shopMapper.toDto(shop);
    }

    /**
     * Update a shop.
     *
     * @param shopDTO the entity to save.
     * @return the persisted entity.
     */
    public ShopDTO update(ShopDTO shopDTO) {
        log.debug("Request to update Shop : {}", shopDTO);
        Shop shop = shopMapper.toEntity(shopDTO);
        shop = shopRepository.save(shop);
        return shopMapper.toDto(shop);
    }

    /**
     * Partially update a shop.
     *
     * @param shopDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ShopDTO> partialUpdate(ShopDTO shopDTO) {
        log.debug("Request to partially update Shop : {}", shopDTO);

        return shopRepository
            .findById(shopDTO.getId())
            .map(existingShop -> {
                shopMapper.partialUpdate(existingShop, shopDTO);

                return existingShop;
            })
            .map(shopRepository::save)
            .map(shopMapper::toDto);
    }

    /**
     * Get all the shops.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ShopDTO> findAll() {
        log.debug("Request to get all Shops");
        return shopRepository.findAll().stream().map(shopMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one shop by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ShopDTO> findOne(Long id) {
        log.debug("Request to get Shop : {}", id);
        return shopRepository.findById(id).map(shopMapper::toDto);
    }

    /**
     * Delete the shop by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Shop : {}", id);
        shopRepository.deleteById(id);
    }
}
