package app.vrabia.musiccollectorservice.service;

import app.vrabia.musiccollectorservice.dto.PagedListenedSongsResponseDTO;
import app.vrabia.musiccollectorservice.dto.SongDTO;
import app.vrabia.musiccollectorservice.mapper.SongMapper;
import app.vrabia.musiccollectorservice.model.ListenedSong;
import app.vrabia.musiccollectorservice.model.Song;
import app.vrabia.musiccollectorservice.repository.ListenedSongRepository;
import app.vrabia.musiccollectorservice.repository.SongRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SongServiceImpl implements SongService {
    private final ListenedSongRepository listenedSongRepository;
    private final SongRepository songRepository;
    private final SongMapper songMapper;
    private static final Integer DEFAULT_PAGE_SIZE = 6;
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final String DEFAULT_SORT_PROPERTY = "lastListenDate";

    @Override
    public String addSong(SongDTO songDTO) {
        // check if song exists, if not add it and return id

        Optional<Song> song = songRepository.findByArtistAndTitle(songDTO.getArtist(), songDTO.getTitle());
        if (song.isPresent()) {
            return song.get().getId();
        }

        Song songToSave = songMapper.songRequestToSong(songDTO);
        Song savedSong = songRepository.save(songToSave);
        return savedSong.getId();
    }

    @Override
    public String addListenedSong(String songId, String userId) {
        Optional<ListenedSong> listenedSong = listenedSongRepository.findByUserIdAndSongId(userId, songId);
        if (listenedSong.isPresent()) {
            // update listened song
            ListenedSong listenedSongToUpdate = listenedSong.get();
            listenedSongToUpdate.setTimesListened(listenedSongToUpdate.getTimesListened() + 1);
            listenedSongToUpdate.setLastListenDate(LocalDateTime.now());
            listenedSongRepository.save(listenedSongToUpdate);
            return listenedSongToUpdate.getId();
        }

        Optional<Song> song = songRepository.findById(songId);
        if (song.isPresent()) {
            // add listened song
            ListenedSong listenedSongToSave = new ListenedSong();
            listenedSongToSave.setSong(song.get());
            listenedSongToSave.setUserId(userId);
            listenedSongToSave.setTimesListened(1);
            listenedSongToSave.setLastListenDate(LocalDateTime.now());
            ListenedSong savedListenedSong = listenedSongRepository.save(listenedSongToSave);
            return savedListenedSong.getId();
        }
        return null;
    }

    @Override
    public void listenSong(String userId, SongDTO songDTO) {
        String songId = addSong(songDTO);
        addListenedSong(songId, userId);
    }

    @Override
    public PagedListenedSongsResponseDTO getSongsHistoryForUser(String userId, Integer page, Integer PageSize) {
        Page<ListenedSong> pagedResult = listenedSongRepository.findByUserId(userId, buildPageRequest(page, PageSize));
        return getPagedListenedSongsResponseDTO(pagedResult);
    }

    @Override
    public PagedListenedSongsResponseDTO getAllSongsHistory(Integer page, Integer PageSize) {
        Page<ListenedSong> pagedResult = listenedSongRepository.findAll(buildPageRequest(page, PageSize));
        return getPagedListenedSongsResponseDTO(pagedResult);
    }

    @Override
    public SongDTO updateGenre(SongDTO songRequest) {
        Optional<Song> song = songRepository.findById(songRequest.getId());
        if (song.isPresent()) {
            Song songToUpdate = song.get();
            songToUpdate.setGenre(songRequest.getGenre());
            Song savedSong = songRepository.save(songToUpdate);
            return songMapper.songToSongResponse(savedSong);
        }
        return null;
    }

    private Pageable buildPageRequest(Integer page, Integer pageSize) {
        Integer actualPage = page == null ? DEFAULT_PAGE_NUMBER : page;
        Integer actualPageSize = pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
        return PageRequest.of(actualPage, actualPageSize, Sort.by(DEFAULT_SORT_PROPERTY).descending());
    }

    private PagedListenedSongsResponseDTO getPagedListenedSongsResponseDTO(Page<ListenedSong> pagedResult) {
        PagedListenedSongsResponseDTO pagedListenedSongsResponseDTO = new PagedListenedSongsResponseDTO();
        List<ListenedSong> listenedSongs = pagedResult.getContent();
        pagedListenedSongsResponseDTO.setSongs(songMapper.listenedSongsToSongResponses(listenedSongs));
        pagedListenedSongsResponseDTO.setTotalPages(pagedResult.getTotalPages());
        pagedListenedSongsResponseDTO.setTotalSongs(pagedResult.getTotalElements());
        pagedListenedSongsResponseDTO.setCurrentPage(pagedResult.getNumber());
        return pagedListenedSongsResponseDTO;
    }
}
