package eu.red1.album.adapters;

import eu.red1.album.adapters.api.ApiClient;
import eu.red1.album.core.model.Album;
import eu.red1.album.core.model.Photo;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ApiClientAdapter implements AlbumApiClientPort {

  private final ApiClient apiClient;

  public ApiClientAdapter(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  @Override
  public List<Album> getAlbums() {
    return apiClient.getAlbums().stream().map(AlbumDtoMapper::mapToDTOAlbum).toList();
  }

  @Override
  public List<Photo> getPhotos() {
    return apiClient.getPhotos().stream().map(PhotoDtoMapper::mapToDTOPhoto).toList();
  }
}
