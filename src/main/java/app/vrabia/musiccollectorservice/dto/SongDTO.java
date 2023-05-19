package app.vrabia.musiccollectorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SongDTO {
    private String id;
    private String title;
    private String artist;
    private String genre;
}
