package app.vrabia.musiccollectorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SongResponse {
    private SongDTO song;
    private String id;
    private LocalDateTime lastListenDate;
    private Integer timesListened;
    private String userId;
}
