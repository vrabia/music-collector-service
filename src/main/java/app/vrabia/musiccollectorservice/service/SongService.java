package app.vrabia.musiccollectorservice.service;

import app.vrabia.musiccollectorservice.dto.LastListenedSongAtLocationDTO;
import app.vrabia.musiccollectorservice.dto.PagedListenedSongsResponseDTO;
import app.vrabia.musiccollectorservice.dto.SongDTO;
import app.vrabia.musiccollectorservice.model.ListenedSong;

import java.util.List;

public interface SongService {
    String addSong(SongDTO songDTO);

    ListenedSong addListenedSong(String songId, String userId);

    void listenSong(String userId, SongDTO songDTO, String ip);

    PagedListenedSongsResponseDTO getSongsHistoryForUser(String userId, Integer page, Integer PageSize);

    PagedListenedSongsResponseDTO getAllSongsHistory(Integer page, Integer PageSize);

    SongDTO updateGenre(SongDTO songRequest);

    List<LastListenedSongAtLocationDTO> getLastListenedSongsAtLocation();
}
