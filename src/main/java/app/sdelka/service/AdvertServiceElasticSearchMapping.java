package app.sdelka.service;

import app.sdelka.controller.dto.AdvertDto;
import app.sdelka.controller.dto.SearchDto;
import app.sdelka.model.entity.AdvertElastic;
import app.sdelka.repository.AdvertElasticSearchRepository;
import app.sdelka.service.conversion.AdvertConversion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertServiceElasticSearchMapping implements AdvertService {

    private final AdvertElasticSearchRepository advertElasticSearchRepository;

    private final AdvertConversion advertConversion;

    @Override
    public UUID save(AdvertDto advertDto) {
        return advertElasticSearchRepository.save(advertConversion.advertDtoToAdvertElastic(advertDto)).getUuid();
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        advertElasticSearchRepository.deleteById(uuid);
    }

    @Override
    public AdvertDto findByUuid(UUID uuid) {
        return advertElasticSearchRepository.findByUuid(uuid).map(advertConversion::advertElasticToAdvertDto).orElse(null);
    }

    @Override
    public List<AdvertDto> findByNameOrDescription(SearchDto searchDto) {
        return advertElasticSearchRepository.findByNameOrDescription(searchDto.getSearch(), searchDto.getSearch())
                .stream()
                .map(AdvertElastic::getUuid)
                .map(this::findByUuid)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
