package eu.red1.album.core.mappers;

import eu.red1.album.adapters.databases.h2.data.AlbumEntity;
import eu.red1.album.adapters.databases.h2.data.PhotoEntity;
import eu.red1.album.core.model.Album;
import eu.red1.album.core.model.Photo;
import java.util.List;
import java.util.stream.Collectors;

public final class AlbumMapper {

  public static AlbumEntity mapToDomainAlbum(final Album album) {
    AlbumEntity entity = new AlbumEntity();
    entity.setUserId(album.getUserId());
    entity.setId(album.getId());
    entity.setTitle(album.getTitle());

    List<PhotoEntity> photoEntityList = album.getPhotos().stream()
        .map(PhotoMapper::mapToDomainPhoto)
        .collect(Collectors.toList());

    entity.setPhotos(photoEntityList);
    return entity;
  }

  public static Album mapToDomainAlbum(final AlbumEntity album) {
    Album domain = new Album();
    domain.setUserId(album.getUserId());
    domain.setId(album.getId());
    domain.setTitle(album.getTitle());

    List<Photo> photoEntityList = album.getPhotos().stream()
        .map(PhotoMapper::mapToDomainPhoto)
        .collect(Collectors.toList());

    domain.setPhotos(photoEntityList);
    return domain;
  }
}