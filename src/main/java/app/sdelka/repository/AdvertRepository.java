package app.sdelka.repository;

import app.sdelka.model.Advert;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdvertRepository extends CrudRepository<Advert, Long> {
    Optional<Advert> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}