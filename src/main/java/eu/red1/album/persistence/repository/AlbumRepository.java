package eu.red1.album.persistence.repository;

import eu.red1.album.persistence.data.AlbumEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Persistence adapter: Stores products in postgres.
 *
 * @author Redouane Mehdi
 */
public interface AlbumRepository extends CrudRepository<AlbumEntity, Long> {}
