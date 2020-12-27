package app.sdelka.repository;

import app.sdelka.model.AdvertElastic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdvertElasticSearchRepository extends CrudRepository<AdvertElastic, UUID> {
    List<AdvertElastic> findByNameOrDescription(String name, String description);
    Optional<AdvertElastic> findByUuid(UUID uuid);
}
