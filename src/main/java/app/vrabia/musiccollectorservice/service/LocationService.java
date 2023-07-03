package app.vrabia.musiccollectorservice.service;

import app.vrabia.musiccollectorservice.dto.LocationDTO;

public interface LocationService {
    LocationDTO getLocation(String ip);
}
