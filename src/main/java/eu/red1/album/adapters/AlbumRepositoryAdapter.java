package eu.red1.album.adapters;

import eu.red1.album.adapters.databases.h2.data.AlbumEntity;
import eu.red1.album.adapters.databases.h2.repository.AlbumRepository;
import eu.red1.album.core.mappers.AlbumMapper;
import eu.red1.album.core.model.Album;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;

@Component
public class AlbumRepositoryAdapter implements AlbumRepositoryPort {

  private final AlbumRepository repository;

  @Autowired
  public AlbumRepositoryAdapter(AlbumRepository repository) {
    this.repository = repository;
  }

  @Override
  public List<Album> findAll() {
    Iterable<AlbumEntity> all = repository.findAll();
    return Streamable.of(all).stream().map(AlbumMapper::mapToDomainAlbum).toList();
  }

  @Override
  public List<Album> saveAll(final List<Album> albums) {
    final List<AlbumEntity> list = albums.stream().map(AlbumMapper::mapToDomainAlbum).toList();
    final Iterable<AlbumEntity> albumEntities = repository.saveAll(list);
    return Streamable.of(albumEntities).stream().map(AlbumMapper::mapToDomainAlbum).toList();
  }
}