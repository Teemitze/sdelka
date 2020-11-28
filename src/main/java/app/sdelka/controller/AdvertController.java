package app.sdelka.controller;

import app.sdelka.dto.AdvertDto;
import app.sdelka.model.Advert;
import app.sdelka.service.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/advert")
@RequiredArgsConstructor
@Validated
public class AdvertController {
    private final AdvertService advertService;

    @PostMapping("/save")
    public void save(@RequestBody AdvertDto advertDto) {
        advertService.save(advertDto);
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
}
