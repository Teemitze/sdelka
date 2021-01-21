package app.sdelka.controller.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class AdvertDto {
    private UUID uuid;
    private String name;
    private String description;
    private long price;
    private String city;
    private Date createData;
    private boolean active;
    private String categoryName;
    private boolean isNew;
    private UserDto user;
    private Long userId;
    private String youtubeUrl;
}
