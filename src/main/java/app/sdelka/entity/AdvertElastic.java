package app.sdelka.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.UUID;

@Document(indexName = "advert")
@Data
public class AdvertElastic {
    @Id
    private UUID uuid;
    private String name;
    private String description;
}
