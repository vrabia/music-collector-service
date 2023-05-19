package app.vrabia.musiccollectorservice.repository;

import app.vrabia.musiccollectorservice.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, String> {
    Optional<Song> findByArtistAndTitle(String artist, String title);
}
