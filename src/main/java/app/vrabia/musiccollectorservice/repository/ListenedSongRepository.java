package app.vrabia.musiccollectorservice.repository;

import app.vrabia.musiccollectorservice.model.ListenedSong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListenedSongRepository extends JpaRepository<ListenedSong, String> {
    Optional<ListenedSong> findByUserIdAndSongId(String userId, String songId);
    Page<ListenedSong> findByUserId(String userId, Pageable pageable);
}
