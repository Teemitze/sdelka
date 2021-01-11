package app.sdelka.service.advert;

import app.sdelka.controller.dto.AdvertDto;
import app.sdelka.controller.dto.SearchDto;

import java.util.List;
import java.util.UUID;

public interface AdvertService {
    UUID save(AdvertDto advertDto);

    void deleteByUuid(UUID uuid);

    AdvertDto findByUuid(UUID uuid);

    List<AdvertDto> findByNameOrDescription(SearchDto searchDto);
}
