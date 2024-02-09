package eu.red1.album.adapters;

import eu.red1.album.adapters.web.dtos.PhotoDTO;
import eu.red1.album.core.model.Photo;

public final class PhotoDtoMapper {

  public static PhotoDTO mapToDTOPhoto(Photo photo) {
    PhotoDTO photoDTO = new PhotoDTO();
    photoDTO.setId(photo.getId());
    photoDTO.setTitle(photo.getTitle());
    photoDTO.setUrl(photo.getUrl());
    photoDTO.setThumbnailUrl(photo.getThumbnailUrl());
    return photoDTO;
  }

  public static Photo mapToDTOPhoto(PhotoDTO photo) {
    Photo domain = new Photo();
    domain.setId(photo.getId());
    domain.setTitle(photo.getTitle());
    domain.setUrl(photo.getUrl());
    domain.setAlbumId(photo.getAlbumId());
    domain.setThumbnailUrl(photo.getThumbnailUrl());
    return domain;
  }

}
