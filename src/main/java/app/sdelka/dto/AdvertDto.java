package app.sdelka.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AdvertDto {
    private String name;
    private String description;
    private long price;
    private String city;
    private Date createData;
    private boolean active;
    private String category;
    private boolean isNew;
    private long user_id;
}
