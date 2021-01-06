package app.sdelka.service.conversion;

import app.sdelka.controller.dto.AdvertDto;
import app.sdelka.model.entity.Advert;
import app.sdelka.model.entity.AdvertElastic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdvertConversion {

    private final CategoryConversion categoryConversion;
    private final UserConversion userConversion;

    public AdvertElastic advertDtoToAdvertElastic(AdvertDto advertDto) {
        final AdvertElastic advertElastic = new AdvertElastic();
        advertElastic.setUuid(advertDto.getUuid());
        advertElastic.setName(advertDto.getName());
        advertElastic.setDescription(advertDto.getDescription());
        return advertElastic;
    }

    public AdvertDto advertElasticToAdvertDto(AdvertElastic advertElastic) {
        final AdvertDto advertDto = new AdvertDto();
        advertDto.setUuid(advertElastic.getUuid());
        advertDto.setName(advertElastic.getName());
        advertDto.setDescription(advertElastic.getDescription());
        return advertDto;
    }

    public AdvertDto advertToAdvertDto(Advert advert) {
        final AdvertDto advertDto = new AdvertDto();
        advertDto.setUuid(advert.getUuid());
        advertDto.setName(advert.getName());
        advertDto.setDescription(advert.getDescription());
        advertDto.setPrice(advert.getPrice());
        advertDto.setCity(advert.getCity());
        advertDto.setCreateData(advert.getCreateData());
        advertDto.setActive(advert.isActive());
        advertDto.setNew(advert.isNew());
        advertDto.setCategoryName(advert.getCategory().getName());
        advertDto.setUser(userConversion.userToUserDto(advert.getUser()));
        return advertDto;
    }

    public Advert advertDtoToAdvert(AdvertDto advertDto) {
        final Advert advert = new Advert();
        advert.setUuid(advertDto.getUuid());
        advert.setName(advertDto.getName());
        advert.setDescription(advertDto.getDescription());
        advert.setPrice(advertDto.getPrice());
        advert.setCity(advertDto.getCity());
        advert.setCreateData(advertDto.getCreateData());
        advert.setActive(advertDto.isActive());
        advert.setNew(advertDto.isNew());
        advert.setCategory(categoryConversion.categoryNameToCategoryConversionRecursive(advertDto.getCategoryName()));
        advert.setUser(userConversion.userDtoToUser(advertDto.getUser()));
        return advert;
    }
}