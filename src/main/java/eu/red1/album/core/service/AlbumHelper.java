package eu.red1.album.core.service;

import eu.red1.album.core.model.Album;
import eu.red1.album.core.model.Photo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 */
public class AlbumHelper {

  /**
   * Aggregates the given list of photos into the corresponding albums.
   * <p>
   * This method takes a list of albums and a list of photos, and associates each photo with its
   * corresponding album using the album's ID. The photos are added to the 'photos' list within
   * their respective albums, and the 'album' reference in each photo is set accordingly. The
   * resulting albums are returned as a new ArrayList.
   *
   * @param albums The list of albums to which photos will be aggregated.
   * @param photos The list of photos to be associated with the albums.
   * @return A new ArrayList containing the albums with aggregated photos.
   */
  static ArrayList<Album> aggregatePhotoToAlbum(List<Album> albums, List<Photo> photos) {
    Map<Long, Album> albumMap = albums.stream()
        .collect(Collectors.toMap(Album::getId, Function.identity()));

    photos.forEach(photo -> {
      final Long albumId = photo.getAlbumId();
      final Album album = albumMap.get(albumId);
      if (album != null) {
        album.getPhotos().add(photo);
        photo.setAlbum(album);
      }
    });
    return new ArrayList<>(albumMap.values());
  }
}