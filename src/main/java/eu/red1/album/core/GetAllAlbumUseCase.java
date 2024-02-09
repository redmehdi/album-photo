package eu.red1.album.core;

import eu.red1.album.core.model.Album;
import java.util.List;

public interface GetAllAlbumUseCase {

  List<Album> getAlbums();
}