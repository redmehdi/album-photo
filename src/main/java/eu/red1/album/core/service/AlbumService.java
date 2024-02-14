package eu.red1.album.core.service;

import eu.red1.album.adapters.AlbumApiClientPort;
import eu.red1.album.adapters.AlbumRepositoryPort;
import eu.red1.album.core.EnriquecerSinAlmacenarUseCase;
import eu.red1.album.core.EnriquecerYAlmacenarUseCase;
import eu.red1.album.core.GetAllAlbumUseCase;
import eu.red1.album.core.model.Album;
import eu.red1.album.core.model.Photo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class AlbumService implements EnriquecerYAlmacenarUseCase, EnriquecerSinAlmacenarUseCase,
    GetAllAlbumUseCase {

  private final AlbumRepositoryPort repositoryPort;

  private final AlbumApiClientPort apiClient;

  public AlbumService(AlbumRepositoryPort repositoryPort, AlbumApiClientPort apiClient) {
    this.repositoryPort = repositoryPort;
    this.apiClient = apiClient;
  }

  @Override
  public List<Album> getAlbums() {
    return repositoryPort.findAll();
  }

  /**
   * Enriches the existing albums with photos obtained from the API client.
   * <p>
   * This method fetches the list of albums and photos from the API client. If no albums are
   * retrieved or the list is empty, an empty list is returned. The method then aggregates the
   * photos to their corresponding albums using AlbumHelper.aggregatePhotoToAlbum. Finally, the
   * enriched albums are saved, and the resulting list is returned.
   *
   * @return The list of enriched albums with associated photos.
   */
  @Override
  public List<Album> enrichAlbums() {
    List<Album> albums = apiClient.getAlbums();
    if (albums == null || albums.isEmpty()) {
      return new ArrayList<>();
    }
    List<Photo> photos = apiClient.getPhotos();

    AlbumHelper.aggregatePhotoToAlbum(albums, photos);

    return saveAlbum(albums);
  }

  /**
   * Enriches albums without saving them.
   * <p>
   * This method fetches the list of albums and photos from the API client. The photos are then
   * aggregated to their corresponding albums using AlbumHelper.aggregatePhotoToAlbum, and the
   * resulting list of enriched albums is returned without saving them.
   *
   * @return The list of enriched albums with associated photos.
   */
  @Override
  public List<Album> enrichSinAlbums() {
    List<Album> albums = apiClient.getAlbums();
    List<Photo> photos = apiClient.getPhotos();
    if (albums == null || albums.isEmpty()) {
      return new ArrayList<>();
    }

    return AlbumHelper.aggregatePhotoToAlbum(albums, photos);
  }

  private List<Album> saveAlbum(List<Album> albums) {
    return repositoryPort.saveAll(albums);
  }
}