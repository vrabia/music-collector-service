package app.vrabia.musiccollectorservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PagedListenedSongsResponseDTO {
    List<SongResponse> songs;
    Long totalSongs;
    Integer totalPages;
    Integer currentPage;
}
