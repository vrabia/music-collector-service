package app.vrabia.musiccollectorservice.mapper;

import app.vrabia.musiccollectorservice.dto.SongDTO;
import app.vrabia.musiccollectorservice.dto.SongResponse;
import app.vrabia.musiccollectorservice.model.ListenedSong;
import app.vrabia.musiccollectorservice.model.Song;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface SongMapper {
    Song songRequestToSong(SongDTO songDTO);

    SongResponse listenedSongToSongResponse(ListenedSong song);

    List<SongResponse> listenedSongsToSongResponses(List<ListenedSong> songs);

    SongDTO songToSongResponse(Song savedSong);
}
