package app.vrabia.musiccollectorservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SONGS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SongIDGenerator")
    @GenericGenerator(
            name = "SongIDGenerator",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "ARTIST")
    private String artist;

    @Column(name = "GENRE")
    private String genre;
}
