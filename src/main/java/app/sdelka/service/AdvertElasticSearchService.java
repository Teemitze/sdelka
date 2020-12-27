package app.sdelka.service;

import app.sdelka.model.AdvertElastic;
import app.sdelka.repository.AdvertElasticSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdvertElasticSearchService {
    private final AdvertElasticSearchRepository advertElasticSearchRepository;

    public Optional<AdvertElastic> findByUuid(UUID uuid) {
        return advertElasticSearchRepository.findByUuid(uuid);
    }

    public void saveAdvert(AdvertElastic advertElastic) {
        advertElasticSearchRepository.save(advertElastic);
    }

    public List<AdvertElastic> findByNameOrDescription(String name, String description) {
        return advertElasticSearchRepository.findByNameOrDescription(name, description);
    }
}
