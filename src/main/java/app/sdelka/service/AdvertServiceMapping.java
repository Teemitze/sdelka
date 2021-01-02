package app.sdelka.service;

import app.sdelka.controller.dto.AdvertDto;
import app.sdelka.controller.dto.SearchDto;
import app.sdelka.model.entity.Advert;
import app.sdelka.repository.AdvertRepository;
import app.sdelka.service.conversion.AdvertConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertServiceMapping implements AdvertService {

    private final AdvertRepository advertRepository;
    private final AdvertConversion advertConversion;

    @Override
    public UUID save(AdvertDto advertDto) {
        Advert advert = advertRepository.save(advertConversion.advertDtoToAdvert(advertDto));
        return advert.getUuid();
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        advertRepository.deleteByUuid(uuid);
    }

    @Override
    public AdvertDto findByUuid(UUID uuid) {
        return advertRepository.findByUuid(uuid).map(advertConversion::advertToAdvertDto).orElse(null);
    }

    @Override
    public List<AdvertDto> findByNameOrDescription(SearchDto searchDto) {
        return advertRepository.findByNameOrDescription(searchDto.getSearch(), searchDto.getSearch()).stream()
                .map(advertConversion::advertToAdvertDto).collect(Collectors.toList());
    }
}
