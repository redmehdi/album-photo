package eu.red1.album.adapters;

import eu.red1.album.core.model.Album;
import java.util.List;

public interface AlbumRepositoryPort {

  List<Album> findAll();

  List<Album> saveAll(List<Album> albums);
}