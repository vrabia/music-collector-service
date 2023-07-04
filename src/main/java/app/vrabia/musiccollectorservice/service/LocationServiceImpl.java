package app.vrabia.musiccollectorservice.service;

import app.vrabia.musiccollectorservice.dto.LocationDTO;
import app.vrabia.vrcommon.exception.ErrorCodes;
import app.vrabia.vrcommon.exception.VrabiaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    @Value("${geolocation.url}")
    private String geolocationUrl;

    @Value("${geolocation.api-key}")
    private String geolocationApiKey;

    WebClient webClient = WebClient.create();

    @Override
    public LocationDTO getLocation(String ip) {
        log.info("Get location for ip: {}", ip);
        String url = geolocationUrl + "?api_key=" + geolocationApiKey;
        if (ip != null) {
            ip = ip.split(",")[0].trim();
            url += "&ip_address=" + ip;
        }

        try {
            return webClient.get()
                    .uri(url)
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(LocationDTO.class)
                    .block();
        } catch (WebClientResponseException ex) {
            log.error("Error getting location for ip: {}", ip, ex);
            ex.getResponseBodyAsString();
            List<String> errorMessages = ex.getResponseBodyAsString().lines().toList();
            throw new VrabiaException(ErrorCodes.INTERNAL_SERVER_ERROR, errorMessages);
        }
    }
}
