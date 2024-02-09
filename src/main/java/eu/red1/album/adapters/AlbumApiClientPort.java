package eu.red1.album.adapters;

import eu.red1.album.core.model.Album;
import eu.red1.album.core.model.Photo;
import java.util.List;

public interface AlbumApiClientPort {

  List<Album> getAlbums();

  List<Photo> getPhotos();
}