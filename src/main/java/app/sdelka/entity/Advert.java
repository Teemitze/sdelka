package app.sdelka.entity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "adverts")
@Data
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class Advert {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID uuid;
    private String name;
    private String description;
    private Long price;
    private String city;
    @Column(name = "create_data")
    private Date createData;
    private boolean isActive;
    private boolean isNew;


    @Type(type = "list-array")
    @Column(
            name = "photo_path",
            columnDefinition = "varchar[]"
    )
    private List<String> photoPath;

    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;

    private String youtubeUrl;
}
