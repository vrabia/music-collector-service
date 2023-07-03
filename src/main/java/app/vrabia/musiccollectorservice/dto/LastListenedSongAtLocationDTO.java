package app.vrabia.musiccollectorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LastListenedSongAtLocationDTO {
    private String userId;
    private SongDTO song;
    private Float latitude;
    private Float longitude;
    private String country;
    private String city;
    private String region;
    private LocalDateTime listenedAt;
}
