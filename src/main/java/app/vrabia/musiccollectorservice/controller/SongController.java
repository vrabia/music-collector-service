package app.vrabia.musiccollectorservice.controller;

import app.vrabia.musiccollectorservice.dto.PagedListenedSongsResponseDTO;
import app.vrabia.musiccollectorservice.dto.SongDTO;
import app.vrabia.musiccollectorservice.service.SongService;
import app.vrabia.vrcommon.service.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/song")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private final JWTService jwtService;

    @PostMapping
    public ResponseEntity<Void> addSong(@RequestBody SongDTO song, @RequestHeader(AUTHORIZATION_HEADER) String authorizationHeader) {
        log.info("Adding song");
        String userId = getUserIdFromAuthorizationHeader(authorizationHeader);
        songService.listenSong(userId, song);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/user-history")
    public ResponseEntity<PagedListenedSongsResponseDTO> getSongsHistoryForUser(@RequestParam(value = "page", required = false) Integer page,
                                                                                @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                                                @RequestHeader(AUTHORIZATION_HEADER) String authorizationHeader) {
        log.info("Get songs history for user");
        String userId = getUserIdFromAuthorizationHeader(authorizationHeader);
        PagedListenedSongsResponseDTO resp = songService.getSongsHistoryForUser(userId, page, pageSize);
        return ResponseEntity.ok().body(resp);
    }

    @GetMapping("/all-history")
    public ResponseEntity<PagedListenedSongsResponseDTO> getAllSongsHistory(@RequestParam(value = "page", required = false) Integer page,
                                                                            @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        log.info("Get all songs history");
        PagedListenedSongsResponseDTO resp = songService.getAllSongsHistory(page, pageSize);
        return ResponseEntity.ok().body(resp);
    }


    @PutMapping("/genre")
    public ResponseEntity<SongDTO> updateGenre(@RequestBody SongDTO songRequest) {
        log.info("Update genre for song with id {}", songRequest.getId());
        SongDTO resp = songService.updateGenre(songRequest);
        return ResponseEntity.ok().body(resp);
    }

    String getUserIdFromAuthorizationHeader(String authorizationHeader) {
        String token = authorizationHeader.substring(BEARER_PREFIX.length());
        return jwtService.decodeJWT(token).getClaim("userId").asString();
    }
}
