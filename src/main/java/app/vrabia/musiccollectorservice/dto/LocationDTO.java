package app.vrabia.musiccollectorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDTO {
    private Float latitude;
    private Float longitude;
    private String country;
    private String city;
    private String region;
}
