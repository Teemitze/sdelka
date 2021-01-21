package app.sdelka.controller;

import app.sdelka.controller.dto.AdvertDto;
import app.sdelka.controller.dto.SearchDto;
import app.sdelka.service.UploadFileService;
import app.sdelka.service.advert.AdvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private final UploadFileService uploadFileService;

    @PostMapping("/photo")
    public void savePhoto(@RequestParam("file") MultipartFile[] files, UUID uuid) {
        uploadFileService.saveFiles(files, uuid);
    }

    @PostMapping
    public void save(@RequestBody AdvertDto advertDto, @RequestParam("file") MultipartFile[] files) {
        final UUID uuid = advertServiceMapping.save(advertDto);
        advertDto.setUuid(uuid);
        advertServiceElasticSearchMapping.save(advertDto);
        uploadFileService.saveFiles(files, uuid);
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
