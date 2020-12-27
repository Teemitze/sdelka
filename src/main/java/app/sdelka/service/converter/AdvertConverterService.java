package app.sdelka.service.converter;

import app.sdelka.dto.AdvertDto;
import app.sdelka.model.Advert;
import app.sdelka.model.AdvertElastic;
import app.sdelka.service.CategoryService;
import app.sdelka.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdvertConverterService {

    private final CategoryService categoryService;
    private final UserService userService;

    public Advert convertAdvertDtoInAvertEntity(AdvertDto advertDto) {
        final Advert advert = new Advert();
        advert.setName(advertDto.getName());
        advert.setDescription(advertDto.getDescription());
        advert.setPrice(advertDto.getPrice());
        advert.setCity(advertDto.getCity());
        advert.setCreateData(advertDto.getCreateData());
        advert.setActive(advertDto.isActive());
        advert.setNew(advertDto.isNew());
        advert.setCategory(categoryService.findByName(advertDto.getCategory()).orElseThrow());
        advert.setUser(userService.findById(advertDto.getUser_id()).orElseThrow());
        return advert;
    }


    public AdvertElastic convertAdvertDtoInAdvertElastic(AdvertDto advertDto) {
        final AdvertElastic advertElastic = new AdvertElastic();
        advertElastic.setName(advertDto.getName());
        advertElastic.setDescription(advertDto.getDescription());
        return advertElastic;
    }
}
