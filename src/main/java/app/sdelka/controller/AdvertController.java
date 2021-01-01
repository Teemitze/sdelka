package app.sdelka.controller;

import app.sdelka.dto.AdvertDto;
import app.sdelka.model.SearchDto;
import app.sdelka.model.entity.Advert;
import app.sdelka.model.entity.AdvertElastic;
import app.sdelka.repository.AdvertElasticSearchRepository;
import app.sdelka.service.AdvertService;
import app.sdelka.service.converter.AdvertConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/advert")
@RequiredArgsConstructor
@Validated
public class AdvertController {
    private final AdvertService advertService;
    private final AdvertElasticSearchRepository advertElasticSearchRepository;
    private final AdvertConverterService advertConverterService;

    @PostMapping("/save")
    public void save(@RequestBody AdvertDto advertDto) {
        UUID uuid = advertService.save(advertDto);
        AdvertElastic advertElastic = advertConverterService.convertAdvertDtoInAdvertElastic(advertDto);
        advertElastic.setUuid(uuid);

        advertElasticSearchRepository.save(advertElastic);
    }

    @DeleteMapping("/delete/{uuid}")
    @Transactional
    public void deleteByUuid(@PathVariable UUID uuid) {
        advertService.deleteByUuid(uuid);
    }

    @GetMapping("/find/{uuid}")
    public Advert findByUuid(@PathVariable UUID uuid) {
        Optional<Advert> optAdvert = advertService.findByUuid(uuid);
        return optAdvert.orElse(null);
    }

    @GetMapping("/search")
    public List<Advert> search(@RequestBody SearchDto searchDto) {
        return advertElasticSearchRepository.findByNameOrDescription(searchDto.getSearch(), searchDto.getSearch()).stream()
                .map(AdvertElastic::getUuid)
                .map(advertService::findByUuid)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
