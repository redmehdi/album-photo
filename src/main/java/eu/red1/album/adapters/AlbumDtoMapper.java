package eu.red1.album.adapters;

import eu.red1.album.adapters.web.dtos.AlbumDTO;
import eu.red1.album.adapters.web.dtos.PhotoDTO;
import eu.red1.album.core.model.Album;
import eu.red1.album.core.model.Photo;
import java.util.List;
import java.util.stream.Collectors;

public class AlbumDtoMapper {

  public static AlbumDTO mapToAlbumDTO(Album album) {
    AlbumDTO albumDTO = new AlbumDTO();
    albumDTO.setUserId(album.getUserId());
    albumDTO.setId(album.getId());
    albumDTO.setTitle(album.getTitle());

    List<PhotoDTO> photoDTOs = album.getPhotos().stream()
        .map(PhotoDtoMapper::mapToDTOPhoto)
        .collect(Collectors.toList());

    albumDTO.setPhotos(photoDTOs);
    return albumDTO;
  }

  public static Album mapToDTOAlbum(AlbumDTO dto) {
    Album domain = new Album();
    domain.setUserId(dto.getUserId());
    domain.setId(dto.getId());
    domain.setTitle(dto.getTitle());

    List<Photo> photoEntityList = dto.getPhotos().stream()
        .map(PhotoDtoMapper::mapToDTOPhoto)
        .collect(Collectors.toList());

    domain.setPhotos(photoEntityList);
    return domain;
  }

}
