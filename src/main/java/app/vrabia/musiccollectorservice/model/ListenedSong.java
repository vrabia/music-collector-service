package app.vrabia.musiccollectorservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "LISTENED_SONGS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListenedSong {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "ListenedSongIDGenerator")
    @GenericGenerator(
            name = "ListenedSongIDGenerator",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SONG_ID")
    private Song song;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "LAST_LISTEN_DATE")
    private LocalDateTime lastListenDate;

    @Column(name = "TIMES_LISTENED")
    private Integer timesListened;
}
