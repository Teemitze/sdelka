package app.sdelka.service;

import app.sdelka.dto.AdvertDto;
import app.sdelka.model.Advert;
import app.sdelka.repository.AdvertRepository;
import app.sdelka.service.converter.AdvertConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdvertService {

    private final AdvertRepository advertRepository;
    private final AdvertConverterService advertConverterService;

    public void save(AdvertDto advertDto) {
        advertRepository.save(advertConverterService.convertAdvertDtoInAvertEntity(advertDto));
    }

    public void deleteByUuid(UUID uuid) {
        advertRepository.deleteByUuid(uuid);
    }

    public Optional<Advert> findByUuid(UUID uuid) {
        return advertRepository.findByUuid(uuid);
    }
}
