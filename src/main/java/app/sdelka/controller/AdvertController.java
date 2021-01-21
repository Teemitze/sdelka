package app.sdelka.controller;

import app.sdelka.controller.dto.AdvertDto;
import app.sdelka.controller.dto.SearchDto;
import app.sdelka.service.advert.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/advert")
@RequiredArgsConstructor
@Validated
public class AdvertController {

    private final AdvertService advertServiceMapping;
    private final AdvertService advertServiceElasticSearchMapping;

    @PostMapping
    public void save(@RequestBody AdvertDto advertDto) {
        final UUID uuid = advertServiceMapping.save(advertDto);
        advertDto.setUuid(uuid);
        advertServiceElasticSearchMapping.save(advertDto);
    }

    @DeleteMapping("/{uuid}")
    @Transactional
    public void deleteByUuid(@PathVariable UUID uuid) {
        advertServiceMapping.deleteByUuid(uuid);
    }

    @GetMapping("/{uuid}")
    public AdvertDto findByUuid(@PathVariable UUID uuid) {
        return advertServiceElasticSearchMapping.findByUuid(uuid);
    }

    @GetMapping("/search")
    public List<AdvertDto> search(@RequestBody SearchDto searchDto) {
        return advertServiceElasticSearchMapping.findByNameOrDescription(searchDto);
    }
}
