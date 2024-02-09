package eu.red1.album.core.mappers;

import eu.red1.album.adapters.web.dtos.PhotoDTO;
import eu.red1.album.adapters.databases.h2.data.PhotoEntity;
import eu.red1.album.core.model.Photo;

public final class PhotoMapper {

  public static PhotoDTO mapToDTOPhoto(PhotoEntity photo) {
    PhotoDTO photoDTO = new PhotoDTO();
    photoDTO.setId(photo.getId());
    photoDTO.setTitle(photo.getTitle());
    photoDTO.setUrl(photo.getUrl());
    photoDTO.setThumbnailUrl(photo.getThumbnailUrl());
    return photoDTO;
  }

  public static PhotoEntity mapToDTOPhoto(PhotoDTO photo) {
    PhotoEntity entity = new PhotoEntity();
    entity.setId(photo.getId());
    entity.setTitle(photo.getTitle());
    entity.setUrl(photo.getUrl());
    entity.setThumbnailUrl(photo.getThumbnailUrl());
    return entity;
  }

  public static PhotoEntity mapToDomainPhoto(Photo photo) {
    PhotoEntity entity = new PhotoEntity();
    entity.setId(photo.getId());
    entity.setTitle(photo.getTitle());
    entity.setUrl(photo.getUrl());
    entity.setThumbnailUrl(photo.getThumbnailUrl());
    return entity;
  }

  public static Photo mapToDomainPhoto(PhotoEntity photo) {
    Photo domain = new Photo();
    domain.setId(photo.getId());
    domain.setTitle(photo.getTitle());
    domain.setUrl(photo.getUrl());
    domain.setThumbnailUrl(photo.getThumbnailUrl());
    return domain;
  }
}