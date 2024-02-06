package eu.red1.album.service;

import eu.red1.album.controllers.AlbumDTO;
import eu.red1.album.controllers.PhotoDTO;
import eu.red1.album.persistence.data.AlbumEntity;
import eu.red1.album.persistence.data.PhotoEntity;
import eu.red1.album.persistence.repository.AlbumRepository;
import eu.red1.album.persistence.restclient.ApiClient;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

@Service
public class AlbumService {

  @Autowired
  private AlbumRepository albumRepository;

  @Autowired
  private ApiClient apiClient;

  public List<AlbumDTO> getAlbums() {
    Iterable<AlbumEntity> albums = albumRepository.findAll();
    return Streamable.of(albums).stream()
        .map(this::mapToAlbumDTO)
        .collect(Collectors.toList());
  }

  public List<AlbumDTO> enrichAlbums() {
    List<AlbumDTO> albums = apiClient.getAlbums();
    List<PhotoDTO> photos = apiClient.getPhotos();

    Map<Long, AlbumDTO> albumMap = albums.stream()
        .collect(Collectors.toMap(AlbumDTO::getId, Function.identity()));

    for (PhotoDTO photo : photos) {
      Long albumId = photo.getAlbumId();
      AlbumDTO album = albumMap.get(albumId);
      if (album != null) {
        album.getPhotos().add(photo);
        photo.setAlbum(album);
      }
    }

    List<AlbumEntity> albumsEntitis = albums.stream()
        .map(this::mapToDTOAlbum).toList();
    return Streamable.of(albumRepository.saveAll(albumsEntitis)).stream()
        .map(this::mapToAlbumDTO)
        .collect(Collectors.toList());
  }

  private AlbumDTO mapToAlbumDTO(AlbumEntity album) {
    AlbumDTO albumDTO = new AlbumDTO();
    albumDTO.setUserId(album.getUserId());
    albumDTO.setId(album.getId());
    albumDTO.setTitle(album.getTitle());

    List<PhotoDTO> photoDTOs = album.getPhotos().stream()
        .map(this::mapToDTOPhoto)
        .collect(Collectors.toList());

    albumDTO.setPhotos(photoDTOs);
    return albumDTO;
  }

  private PhotoDTO mapToDTOPhoto(PhotoEntity photo) {
    PhotoDTO photoDTO = new PhotoDTO();
    photoDTO.setId(photo.getId());
    photoDTO.setTitle(photo.getTitle());
    photoDTO.setUrl(photo.getUrl());
    photoDTO.setThumbnailUrl(photo.getThumbnailUrl());
    return photoDTO;
  }

  private AlbumEntity mapToDTOAlbum(AlbumDTO album) {
    AlbumEntity entity = new AlbumEntity();
    entity.setUserId(album.getUserId());
    entity.setId(album.getId());
    entity.setTitle(album.getTitle());

    List<PhotoEntity> photoEntityList = album.getPhotos().stream()
        .map(this::mapToDTOPhoto)
        .collect(Collectors.toList());

    entity.setPhotos(photoEntityList);
    return entity;
  }

  private PhotoEntity mapToDTOPhoto(PhotoDTO photo) {
    PhotoEntity entity = new PhotoEntity();
    entity.setId(photo.getId());
    entity.setTitle(photo.getTitle());
    entity.setUrl(photo.getUrl());
    entity.setThumbnailUrl(photo.getThumbnailUrl());
    return entity;
  }

}
