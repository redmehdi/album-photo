package eu.red1.album.persistence.restclient;

import eu.red1.album.controllers.AlbumDTO;
import eu.red1.album.controllers.PhotoDTO;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiClient {

  private final RestTemplate restTemplate;

  @Value("${api.albums.url}")
  private String albumsUrl;

  @Value("${api.photos.url}")
  private String photosUrl;

  @Autowired
  public ApiClient(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public List<AlbumDTO> getAlbums() {
    ResponseEntity<AlbumDTO[]> response = restTemplate.getForEntity(albumsUrl, AlbumDTO[].class);
    return Arrays.asList(Objects.requireNonNull(response.getBody()));
  }

  public List<PhotoDTO> getPhotos() {
    ResponseEntity<PhotoDTO[]> response = restTemplate.getForEntity(photosUrl, PhotoDTO[].class);
    return Arrays.asList(Objects.requireNonNull(response.getBody()));
  }
}

