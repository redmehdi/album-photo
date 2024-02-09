package eu.red1.album.core.service;

import eu.red1.album.adapters.AlbumApiClientPort;
import eu.red1.album.adapters.AlbumRepositoryPort;
import eu.red1.album.core.EnriquecerSinAlmacenarUseCase;
import eu.red1.album.core.EnriquecerYAlmacenarUseCase;
import eu.red1.album.core.GetAllAlbumUseCase;
import eu.red1.album.core.model.Album;
import eu.red1.album.core.model.Photo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
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

  @Override
  public List<Album> enrichAlbums() {
    List<Album> albums = apiClient.getAlbums();
    List<Photo> photos = apiClient.getPhotos();

    AddPhotosInAlbum(albums, photos);

    return saveAlbum(albums);
  }

  @Override
  public List<Album> enrichSinAlbums() {
    List<Album> albums = apiClient.getAlbums();
    List<Photo> photos = apiClient.getPhotos();

    AddPhotosInAlbum(albums, photos);

    return AddPhotosInAlbum(albums, photos);
  }

  private static ArrayList<Album> AddPhotosInAlbum(List<Album> albums, List<Photo> photos) {
    Map<Long, Album> albumMap = albums.stream()
        .collect(Collectors.toMap(Album::getId, Function.identity()));

    for (Photo photo : photos) {
      Long albumId = photo.getAlbumId();
      Album album = albumMap.get(albumId);
      if (album != null) {
        album.getPhotos().add(photo);
        photo.setAlbum(album);
      }
    }   // Create a map to store albums by ID
    return new ArrayList<>(albumMap.values());
  }

  private List<Album> saveAlbum(List<Album> albums) {
    return repositoryPort.saveAll(albums);
  }
}