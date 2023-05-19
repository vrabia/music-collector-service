package app.vrabia.musiccollectorservice.service;

import app.vrabia.musiccollectorservice.dto.PagedListenedSongsResponseDTO;
import app.vrabia.musiccollectorservice.dto.SongDTO;

public interface SongService {
    String addSong(SongDTO songDTO);

    String addListenedSong(String songId, String userId);

    void listenSong(String userId, SongDTO songDTO);

    PagedListenedSongsResponseDTO getSongsHistoryForUser(String userId, Integer page, Integer PageSize);

    PagedListenedSongsResponseDTO getAllSongsHistory(Integer page, Integer PageSize);

    SongDTO updateGenre(SongDTO songRequest);
}
