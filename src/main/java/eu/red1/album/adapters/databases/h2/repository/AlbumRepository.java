package eu.red1.album.adapters.databases.h2.repository;

import eu.red1.album.adapters.databases.h2.data.AlbumEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Persistence adapter: Stores Albums in H2.
 *
 * @author Redouane Mehdi
 */
public interface AlbumRepository extends CrudRepository<AlbumEntity, Long> {}
