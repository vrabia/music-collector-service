package app.vrabia.musiccollectorservice.repository;

import app.vrabia.musiccollectorservice.model.LastListenedSongAtLocationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LastListenedSongAtLocationRepository extends JpaRepository<LastListenedSongAtLocationModel, String> {
    Optional<LastListenedSongAtLocationModel> findByUserId(String userId);
}
